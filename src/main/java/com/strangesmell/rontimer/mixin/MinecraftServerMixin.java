package com.strangesmell.rontimer.mixin;

import com.strangesmell.rontimer.RONTimer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;
import java.util.function.BooleanSupplier;

import static com.strangesmell.rontimer.RONTimer.random;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Shadow public abstract void tickServer(BooleanSupplier booleanSupplier);
    @Shadow protected abstract boolean haveTime();

    @Shadow private PlayerList playerList;
    @Unique
    private boolean isAcceleratingTime = false;

    @Inject(method = "tickServer", at = @At("HEAD"))
    private void onTickServerStart(BooleanSupplier hasTimeLeft, CallbackInfo ci) {
        if (!isAcceleratingTime) {
            isAcceleratingTime = true;
            float timer2 = RONTimer.timer-1;
            for (int i = 1; i < RONTimer.timer && RONTimer.timer-i >= 1 && haveTime(); i++) {
                tickServer(hasTimeLeft);
                timer2--;
            }
            if(random.nextFloat(0,1)<timer2) {
                tickServer(hasTimeLeft);
            }
            isAcceleratingTime = false;

        }
    }
}
