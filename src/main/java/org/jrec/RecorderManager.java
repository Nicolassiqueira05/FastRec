package org.jrec;

public class RecorderManager {
    private static boolean paused = false;

    public static void setPaused(boolean value) {
        paused = value;
    }

    public static boolean isPaused() {
        return paused;
    }
}
