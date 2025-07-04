package com.jowhjy.mixin;

import com.jowhjy.ChunkGetter;
import com.jowhjy.config.FootprintConfigs;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntity_Mixin extends PlayerEntity {
    public ServerPlayerEntity_Mixin(World world, GameProfile gameProfile) {
        super(world, gameProfile);
    }

    @Inject(method = "dropItem", at = @At("HEAD"))
    public void footprint$alwaysSaveChunkOnItemDroppedByPlayer(ItemStack stack, boolean dropAtSelf, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir)
    {
        if (!FootprintConfigs.ALWAYS_SAVE_ITEM_DROP) return;

        ChunkGetter.forceSaveChunksAround(this.getWorld(), this.getBlockPos(), FootprintConfigs.ALWAYS_SAVE_ITEM_DROP_RANGE);

    }
}
