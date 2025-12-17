package com.jowhjy;

import com.jowhjy.mixin_interfaces.IChunkWithForcedSave;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;

public class ChunkGetter {

    //marks all chunks around blockPos as force save
    public static void forceSaveChunksAround(Level world, BlockPos blockPos, int range)
    {
        ChunkPos chunkPos = new ChunkPos(blockPos);
        for (int x = chunkPos.x - range; x <= chunkPos.x + range; x++)
            for (int z = chunkPos.z - range; z <= chunkPos.z + range; z++)
            {
                IChunkWithForcedSave chunk = (IChunkWithForcedSave) world.getChunk(x, z);
                chunk.footprint$setForceSave();
            }
    }

}
