package com.jowhjy;

import com.jowhjy.mixin_interfaces.IChunkWithForcedSave;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

public class ChunkGetter {

    //marks all chunks around blockPos as force save
    public static void forceSaveChunksAround(World world, BlockPos blockPos, int range)
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
