package com.jowhjy.mixin;

import com.jowhjy.ChunkGetter;
import com.jowhjy.config.FootprintConfigs;
import com.jowhjy.mixin_interfaces.IChunkWithForcedSave;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntity_Mixin extends Entity {

    public LivingEntity_Mixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(method = "hurtServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isSleeping()Z"))
    public void footprint$alwaysSaveChunkOnEntityDamaged(ServerLevel world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        //this is off by default because it can happen without player intervention (bat flying into lava, foxes/wolves/axolotls murdering mobs, squid suffocating, fall damage, etc...)
        if (!FootprintConfigs.ALWAYS_SAVE_ENTITY_HURT) return;

        ChunkGetter.forceSaveChunksAround(world, this.blockPosition(), FootprintConfigs.ALWAYS_SAVE_ENTITY_HURT_RANGE);
    }
}
