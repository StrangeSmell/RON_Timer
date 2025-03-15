package com.strangesmell.rontimer.mixin;

import com.strangesmell.rontimer.RONTimer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

import static com.strangesmell.rontimer.RONTimer.random;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract void travel(Vec3 p_21280_) ;

    private boolean isAcceleratingTime = false;

    @Inject(method = "travel", at = @At("HEAD"))
    private void onTravelStart(Vec3 p_21280_, CallbackInfo ci) {
        if (!isAcceleratingTime) {
            isAcceleratingTime = true;
            float timer2 = RONTimer.livingEntityTimer-1;
            for (int i = 1; i < RONTimer.livingEntityTimer && RONTimer.livingEntityTimer-i >= 1; i++) {
                travel(p_21280_);
                timer2--;
            }
            if(random.nextFloat(0,1)<timer2) {
                travel(p_21280_);
            }
            isAcceleratingTime = false;

        }
    }
}
