package com.jowhjy.mixin;

import com.google.common.collect.ImmutableList;
import com.jowhjy.config.FootprintConfigs;
import com.jowhjy.mixin_interfaces.IChunkWithForcedSave;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.entity.ChunkEntities;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.CompletableFuture;

@Mixin(ChunkMap.class)
public abstract class ChunkMap_Mixin {

    @Shadow @Final ServerLevel level;

    @Shadow public abstract @Nullable LevelChunk getChunkToSend(long l);

    @Inject(method = "save(Lnet/minecraft/world/level/chunk/ChunkAccess;)Z", at = @At(value = "HEAD"), cancellable = true)
    public void footprint$chunkSavingConditions(ChunkAccess chunk, CallbackInfoReturnable<Boolean> cir) {

        if (chunk.getInhabitedTime() < FootprintConfigs.MIN_INHABITED_TIME
        && !((IChunkWithForcedSave)chunk).footprint$isForceSave()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "method_60440", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;unload(Lnet/minecraft/world/level/chunk/LevelChunk;)V"))
    public void footprint$clearEntitiesWhenUnloadingUnsavedChunk(ChunkHolder chunkHolder, CompletableFuture<?> completableFuture, long l, CallbackInfo ci, @Local LevelChunk levelChunk){

        if (levelChunk.getInhabitedTime() < FootprintConfigs.MIN_INHABITED_TIME
                && !((IChunkWithForcedSave)levelChunk).footprint$isForceSave())
            ((PersistentEntitySectionManager_Accessor)((ServerLevel_Accessor)this.level).getEntityManager()).footprint$getPermanentStorage().storeEntities(new ChunkEntities<>(new ChunkPos(l), ImmutableList.of()));

    }

}
