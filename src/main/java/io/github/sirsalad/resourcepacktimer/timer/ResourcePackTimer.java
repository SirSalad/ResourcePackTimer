package io.github.sirsalad.resourcepacktimer.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourcePackTimer {

    private static final Logger LOGGER = LoggerFactory.getLogger("resourcepacktimer");
    private static long startDownloading;
    private static long startLoading;
    private static long finishLoading;
    private static long fileSize;

    public static void log() {
        LOGGER.info(
                "Downloaded in {}ms, Loaded in {}ms, File size: {}B",
                startLoading - startDownloading,
                finishLoading - startLoading,
                fileSize
        );
    }

    public static void setStartDownloading(long startDownloading) {
        ResourcePackTimer.startDownloading = startDownloading;
    }

    public static void setStartLoading(long startLoading) {
        ResourcePackTimer.startLoading = startLoading;
    }

    public static void setFinishLoading(long finishLoading) {
        ResourcePackTimer.finishLoading = finishLoading;
    }

    public static void setFileSize(long fileSize) {
        ResourcePackTimer.fileSize = fileSize;
    }
}
