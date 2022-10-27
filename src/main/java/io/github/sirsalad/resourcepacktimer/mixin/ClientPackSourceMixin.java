package io.github.sirsalad.resourcepacktimer.mixin;

import io.github.sirsalad.resourcepacktimer.timer.ResourcePackTimer;
import net.minecraft.client.resources.ClientPackSource;
import net.minecraft.server.packs.repository.PackSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.File;
import java.util.concurrent.CompletableFuture;

@Mixin(ClientPackSource.class)
public class ClientPackSourceMixin {

    @Inject(
            method = "setServerPack(Ljava/io/File;Lnet/minecraft/server/packs/repository/PackSource;)Ljava/util/concurrent/CompletableFuture;",
            at = @At("HEAD")
    )
    private void setServerPack(File file, PackSource packSource, CallbackInfoReturnable<CompletableFuture<Void>> info) {
        ResourcePackTimer.setFileSize(file.length());
        ResourcePackTimer.setStartLoading(System.currentTimeMillis());
    }
}
