package com.jowhjy.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.server.level.GenerationChunkHolder;
import net.minecraft.util.StaticCache2D;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.status.ChunkStatusTasks;
import net.minecraft.world.level.chunk.status.ChunkStep;
import net.minecraft.world.level.chunk.status.WorldGenContext;
import net.minecraft.world.level.entity.ChunkEntities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.CompletableFuture;

@Mixin(ChunkStatusTasks.class)
public class ChunkStatusTasks_Mixin {



    @Inject(method = "generateSpawn", at = @At("RETURN"))
    private static void footprint$clearEntitiesWhenGeneratingEntities(WorldGenContext worldGenContext, ChunkStep chunkStep, StaticCache2D<GenerationChunkHolder> staticCache2D, ChunkAccess chunkAccess, CallbackInfoReturnable<CompletableFuture<ChunkAccess>> cir)
    {
        ((PersistentEntitySectionManager_Accessor)((ServerLevel_Accessor)worldGenContext.level()).getEntityManager()).footprint$getPermanentStorage().storeEntities(new ChunkEntities<>(chunkAccess.getPos(), ImmutableList.of()));

    }
}
