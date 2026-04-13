package com.jowhjy;

import com.jowhjy.mixin_interfaces.IChunkWithForcedSave;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;

public class ChunkGetter {

    //marks all chunks around blockPos as force save
    public static void forceSaveChunksAround(Level world, BlockPos blockPos, int range)
    {
        long pos = ChunkPos.containing(blockPos).pack();
        for (int x = ChunkPos.getX(pos) - range; x <= ChunkPos.getX(pos) + range; x++)
            for (int z = ChunkPos.getZ(pos) - range; z <= ChunkPos.getZ(pos) + range; z++)
            {
                IChunkWithForcedSave chunk = (IChunkWithForcedSave) world.getChunk(x, z);
                chunk.footprint$setForceSave();
            }
    }

}
