package com.jowhjy.mixin;

import com.jowhjy.mixin_interfaces.IChunkWithForcedSave;
import net.minecraft.world.level.chunk.ChunkAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChunkAccess.class)
public class Chunk_Mixin implements IChunkWithForcedSave {

    @Unique
    boolean footprint$forceSave = false;

    @Unique @Override
    public boolean footprint$isForceSave() {
        return footprint$forceSave;
    }

    @Unique @Override
    public void footprint$setForceSave() {
        footprint$forceSave = true;
    }


}
