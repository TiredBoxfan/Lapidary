package com.prestosaur.lapidary.mixin;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.ToolActions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Optional;

@Mixin(AxeItem.class)
public abstract class AxeItemMixinOld extends Item
{
    private static final double MAX_SECONDARY_DISTANCE = Math.sqrt(ServerGamePacketListenerImpl.MAX_INTERACTION_DISTANCE) - 1.0D;

    public AxeItemMixinOld(Properties pProperties) {
        super(pProperties);
    }

    /*@Inject(method="useOn", at = @At("HEAD"), cancellable = true)
    public void useOnMixin(UseOnContext pContext, CallbackInfoReturnable<InteractionResult> info)
    {
        var player = pContext.getPlayer();
        if(pContext.getHand() == InteractionHand.MAIN_HAND && !player.getItemInHand(InteractionHand.OFF_HAND).isEmpty())
            info.setReturnValue(InteractionResult.PASS);
    }*/

    BlockPos testPos;

    /**
     * @author Prestosaur
     * @reason Allowing Stripping Animation
     */
    @Overwrite
    public InteractionResult useOn(UseOnContext pContext)
    {
        // Gather information for easy access.
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        Player player = pContext.getPlayer();

        testPos = blockpos;

        if(pContext.getHand() == InteractionHand.MAIN_HAND && !player.getItemInHand(InteractionHand.OFF_HAND).isEmpty())
            return InteractionResult.PASS;

        if (player != null && blockstate != null)
        {
            player.startUsingItem(pContext.getHand());
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration)
    {
        if (pRemainingUseDuration >= 0 && pLivingEntity instanceof Player player)
        {

            HitResult hitResult = calculateHitResult(pLivingEntity);
            if (hitResult instanceof BlockHitResult blockhitresult)
            {
                BlockPos blockpos = blockhitresult.getBlockPos();
                BlockState blockstate = pLevel.getBlockState(blockpos);
                Block block = blockstate.getBlock();

                System.out.println(blockstate.getDestroyProgress(player, pLevel, blockpos));

                if(pRemainingUseDuration != 1)
                    return;

                if (!blockpos.equals(testPos))
                    return;

                HumanoidArm humanoidarm = pLivingEntity.getUsedItemHand() == InteractionHand.MAIN_HAND ? player.getMainArm() : player.getMainArm().getOpposite();

                UseOnContext context = new UseOnContext(player, player.getUsedItemHand(), blockhitresult);
                ItemStack itemstack = context.getItemInHand();


                Optional<BlockState> optFinal = Optional.empty();

                Optional<BlockState> optStrip = Optional.ofNullable(blockstate.getToolModifiedState(context, ToolActions.AXE_STRIP, false));

                if (optStrip.isPresent())
                {
                    pLevel.playSound(player, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
                    optFinal = optStrip;
                }

                if (optFinal.isPresent())
                {
                    if (player instanceof ServerPlayer serverplayer)
                    {
                        CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverplayer, blockpos, itemstack);
                    }

                    pLevel.setBlock(blockpos, optFinal.get(), 11);
                    pLevel.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, optFinal.get()));
                    if (player != null)
                    {
                        itemstack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(context.getHand()));
                    }

                    //return InteractionResult.sidedSuccess(pLevel.isClientSide);
                }
                else
                {
                    //return InteractionResult.PASS;
                }
            }
        }
        else
        {
            pLivingEntity.releaseUsingItem();
        }
    }

    private HitResult calculateHitResult(LivingEntity pEntity)
    {
        return ProjectileUtil.getHitResultOnViewVector(pEntity, (p) -> !p.isSpectator() && p.isPickable(), MAX_SECONDARY_DISTANCE);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack)
    {
        //System.out.println("HERE");
        return UseAnim.BRUSH;
    }

    @Override
    public int getUseDuration(ItemStack pStack)
    {
        return 32;
    }
}
