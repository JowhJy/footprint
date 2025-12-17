package com.jowhjy.mixin;

import com.jowhjy.ChunkGetter;
import com.jowhjy.config.FootprintConfigs;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerEntity_Mixin extends Player {
    @Shadow public abstract @NotNull ServerLevel level();

    public ServerPlayerEntity_Mixin(Level world, GameProfile gameProfile) {
        super(world, gameProfile);
    }

    @Inject(method = "drop(Lnet/minecraft/world/item/ItemStack;ZZ)Lnet/minecraft/world/entity/item/ItemEntity;", at = @At("HEAD"))
    public void footprint$alwaysSaveChunkOnItemDroppedByPlayer(ItemStack stack, boolean dropAtSelf, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir)
    {
        if (!FootprintConfigs.ALWAYS_SAVE_ITEM_DROP) return;

        ChunkGetter.forceSaveChunksAround(this.level(), this.blockPosition(), FootprintConfigs.ALWAYS_SAVE_ITEM_DROP_RANGE);

    }
}
