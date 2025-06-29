package com.jowhjy.mixin;

import com.jowhjy.config.FootprintConfigs;
import com.jowhjy.mixin_interfaces.IChunkWithForcedSave;
import net.minecraft.server.world.ThreadedAnvilChunkStorage;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ThreadedAnvilChunkStorage.class)
public class ThreadedAnvilChunkStorage_Mixin {

    @Inject(method = "save(Lnet/minecraft/world/chunk/Chunk;)Z", at = @At(value = "HEAD"), cancellable = true)
    public void footprint$chunkSavingConditions(Chunk chunk, CallbackInfoReturnable<Boolean> cir) {

        if (chunk.getInhabitedTime() < FootprintConfigs.MIN_INHABITED_TIME
        && !((IChunkWithForcedSave)chunk).footprint$isForceSave())
            cir.setReturnValue(false);
    }

}
