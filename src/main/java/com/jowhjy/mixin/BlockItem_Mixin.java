package com.jowhjy.mixin;

import com.jowhjy.ChunkGetter;
import com.jowhjy.config.FootprintConfigs;
import com.jowhjy.mixin_interfaces.IChunkWithForcedSave;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class BlockItem_Mixin {
    @Inject(method = "place(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/util/ActionResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BlockItem;placeFromNbt(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;Lnet/minecraft/block/BlockState;)Lnet/minecraft/block/BlockState;"))
    public void footprint$forceSaveChunkOnBlockPlacedByPlayer(ItemPlacementContext context, CallbackInfoReturnable<ActionResult> cir)
    {
        if (!FootprintConfigs.ALWAYS_SAVE_BLOCK_PLACE) return;

        ChunkGetter.forceSaveChunksAround(context.getWorld(), context.getBlockPos(), FootprintConfigs.ALWAYS_SAVE_BLOCK_PLACE_RANGE);
    }
}
