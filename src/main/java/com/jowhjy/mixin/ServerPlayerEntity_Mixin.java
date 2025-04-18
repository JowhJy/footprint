package com.jowhjy.mixin;

import com.jowhjy.config.FootprintConfigs;
import com.jowhjy.mixin_interfaces.IChunkWithForcedSave;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntity_Mixin extends PlayerEntity {
    public ServerPlayerEntity_Mixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(method = "dropItem", at = @At("HEAD"))
    public void footprint$alwaysSaveChunkOnItemDroppedByPlayer(ItemStack stack, boolean dropAtSelf, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir)
    {
        if (!FootprintConfigs.ALWAYS_SAVE_ITEM_DROP) return;

        //TODO: also save neighbors of this one
        IChunkWithForcedSave chunk = (IChunkWithForcedSave) this.getWorld().getChunk(this.getBlockPos());
        chunk.footprint$setForceSave();

    }
}
