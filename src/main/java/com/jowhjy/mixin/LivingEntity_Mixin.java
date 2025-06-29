package com.jowhjy.mixin;

import com.jowhjy.ChunkGetter;
import com.jowhjy.config.FootprintConfigs;
import com.jowhjy.mixin_interfaces.IChunkWithForcedSave;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntity_Mixin extends Entity {

    public LivingEntity_Mixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isSleeping()Z"))
    public void footprint$alwaysSaveChunkOnEntityDamaged(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        //this is off by default because it can happen without player intervention (bat flying into lava, foxes/wolves/axolotls murdering mobs, squid suffocating, fall damage, etc...)
        if (!FootprintConfigs.ALWAYS_SAVE_ENTITY_HURT) return;

        ChunkGetter.forceSaveChunksAround(this.getWorld(), this.getBlockPos(), FootprintConfigs.ALWAYS_SAVE_ENTITY_HURT_RANGE);
    }
}
