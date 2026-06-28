package com.jowhjy.mixin;

import com.jowhjy.ChunkGetter;
import com.jowhjy.config.FootprintConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BaseContainerBlockEntity.class)
public abstract class BaseContainerBlockEntity_Mixin extends BlockEntity {
    public BaseContainerBlockEntity_Mixin(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
        super(type, worldPosition, blockState);
    }

    @Inject(method = "createMenu(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/inventory/AbstractContainerMenu;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/BaseContainerBlockEntity;createMenu(ILnet/minecraft/world/entity/player/Inventory;)Lnet/minecraft/world/inventory/AbstractContainerMenu;"))
    public void footprint$forceSaveOnContainerOpen(int containerId, Inventory inventory, Player player, CallbackInfoReturnable<AbstractContainerMenu> cir) {

        if (!FootprintConfigs.ALWAYS_SAVE_CONTAINER_OPEN) return;

        ChunkGetter.forceSaveChunksAround(this.level, this.worldPosition, FootprintConfigs.ALWAYS_SAVE_CONTAINER_OPEN_RANGE);
    }
}
