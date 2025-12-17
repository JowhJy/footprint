package com.jowhjy.mixin;

import com.jowhjy.config.FootprintConfigs;
import com.jowhjy.mixin_interfaces.IChunkWithForcedSave;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.world.level.chunk.ChunkAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkMap.class)
public class ServerChunkLoadingManager_Mixin {

    @Inject(method = "save(Lnet/minecraft/world/level/chunk/ChunkAccess;)Z", at = @At(value = "HEAD"), cancellable = true)
    public void footprint$chunkSavingConditions(ChunkAccess chunk, CallbackInfoReturnable<Boolean> cir) {

        if (chunk.getInhabitedTime() < FootprintConfigs.MIN_INHABITED_TIME
        && !((IChunkWithForcedSave)chunk).footprint$isForceSave())
            cir.setReturnValue(false);
    }

}
