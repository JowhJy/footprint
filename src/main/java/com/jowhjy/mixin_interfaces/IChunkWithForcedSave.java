package com.jowhjy.mixin_interfaces;

public interface IChunkWithForcedSave {

    default boolean footprint$isForceSave() {
        return false;
    }

    default void footprint$setForceSave()
    {
    }
}
