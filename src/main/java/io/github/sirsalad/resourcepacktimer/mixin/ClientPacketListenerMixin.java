package io.github.sirsalad.resourcepacktimer.mixin;

import io.github.sirsalad.resourcepacktimer.timer.ResourcePackTimer;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ServerboundResourcePackPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

    @Inject(
            method = "send(Lnet/minecraft/network/protocol/game/ServerboundResourcePackPacket$Action;)V",
            at = @At("TAIL")
    )
    private void send(ServerboundResourcePackPacket.Action action, CallbackInfo info) {
        switch (action) {
            case ACCEPTED -> ResourcePackTimer.setStartDownloading(System.currentTimeMillis());
            case SUCCESSFULLY_LOADED -> {
                ResourcePackTimer.setFinishLoading(System.currentTimeMillis());
                ResourcePackTimer.log();
            }
        }
    }
}
