package org.jrec.recorder;

import static org.jrec.RecorderManager.*;

public class Recorder {

    private final Capture capture;
    private Record record;
    private final double framerate;
    private final String fname;
    private Thread t1;

    private boolean rec = false;
    private boolean paused = false;

    private long startTime;
    private long pausedDuration;
    private long pauseStart;

    public Recorder(String fname, double framerate) throws Exception {
        this.framerate = framerate;
        this.capture = new Capture();
        this.fname = fname;
    }

    public void start() {
        try {
            this.record = new Record(fname, capture.getWidth(), capture.getHeight(), framerate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        rec = true;
        paused = false;
        pausedDuration = 0;
        pauseStart = 0;
        startTime = System.currentTimeMillis();

        t1 = new Thread(() -> {
            try {
                while (rec) {
                    if (!paused) {
                        long timestamp = (System.currentTimeMillis() - startTime - pausedDuration) * 1000;
                        record.setTimestamp(timestamp);
                        record.record(capture.shot());
                    }
                    Thread.sleep((long) (1000 / framerate));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t1.start();
    }

    public void stop() {
        rec = false;
        try {
            if (t1 != null) t1.join();
            if (record != null) record.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        if (!paused) {
            paused = true;
            pauseStart = System.currentTimeMillis();
            setPaused(true);
        }
    }

    public void resume() {
        if (paused) {
            paused = false;
            pausedDuration += System.currentTimeMillis() - pauseStart;
            pauseStart = 0;
            setPaused(false);
        }
    }

    public boolean isRecording() {
        return rec && !paused;
    }
}
