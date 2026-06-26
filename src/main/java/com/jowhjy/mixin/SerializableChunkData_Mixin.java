package com.jowhjy.mixin;

import com.jowhjy.mixin_interfaces.IChunkWithForcedSave;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ProtoChunk;
import net.minecraft.world.level.chunk.storage.RegionStorageInfo;
import net.minecraft.world.level.chunk.storage.SerializableChunkData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SerializableChunkData.class)
public class SerializableChunkData_Mixin {

    @Inject(method = "read", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/chunk/ChunkAccess;setLightCorrect(Z)V"))
    public void footprint$alwaysSaveIfSavedBefore(ServerLevel level, PoiManager poiManager, RegionStorageInfo regionInfo, ChunkPos pos, CallbackInfoReturnable<ProtoChunk> cir, @Local(name = "chunk") ChunkAccess chunk) {
        ((IChunkWithForcedSave)chunk).footprint$setForceSave();
    }
}
