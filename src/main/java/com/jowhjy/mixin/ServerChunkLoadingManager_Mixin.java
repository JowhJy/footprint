package com.jowhjy.mixin;

import com.jowhjy.config.FootprintConfigs;
import net.minecraft.server.world.ServerChunkLoadingManager;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerChunkLoadingManager.class)
public class ServerChunkLoadingManager_Mixin {

    @Inject(method = "save(Lnet/minecraft/world/chunk/Chunk;)Z", at = @At(value = "HEAD"), cancellable = true)
    public void footprint$chunkSavingConditions(Chunk chunk, CallbackInfoReturnable<Boolean> cir) {

        if (chunk.getInhabitedTime() < FootprintConfigs.MIN_INHABITED_TIME) cir.setReturnValue(false);
    }

}
