package com.jowhjy.mixin;

import com.jowhjy.ChunkGetter;
import com.jowhjy.config.FootprintConfigs;
import com.jowhjy.mixin_interfaces.IChunkWithForcedSave;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntity_Mixin extends Entity {

    public ItemEntity_Mixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(method = "playerTouch", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;onItemPickup(Lnet/minecraft/world/entity/item/ItemEntity;)V"))
    public void footprint$forceSaveChunkOnItemPickup(Player player, CallbackInfo ci)
    {
        if (!FootprintConfigs.ALWAYS_SAVE_ITEM_PICKUP) return;

        ChunkGetter.forceSaveChunksAround(this.level(), this.blockPosition(), FootprintConfigs.ALWAYS_SAVE_ITEM_PICKUP_RANGE);
    }
}
