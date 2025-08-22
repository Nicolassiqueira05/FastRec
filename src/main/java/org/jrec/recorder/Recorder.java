package org.jrec.recorder;

import java.lang.Thread;

import static org.jrec.RecorderManager.*;

public class Recorder {

    private final Capture capture;
    private Record record;
    private final double framerate;
    private String fname;
    private Thread t1;
    private boolean rec = false;
    private boolean paused = false;
    private long pausedTime;

    public Recorder(String fname, double framerate) throws Exception{
        this.framerate = framerate;
        this.capture = new Capture();
        this.fname = fname;
    }
    public void start(){
        try{
            this.record = new Record(fname, capture.getWidth(), capture.getHeight(), framerate);
        }catch (Exception e){
            e.printStackTrace();
        }

        rec = true;
        long delay = (long) (1000/framerate);

        t1 = new Thread(() -> {
            try {
                long startTime = System.currentTimeMillis();
                long pausedTime = 0;
                long pauseStart = 0;

                while (rec) {
                    if (!paused) {
                        record.record(capture.shot());

                        // Timestamp considerando o tempo de pausa
                        long timestamp = (System.currentTimeMillis() - startTime - pausedTime) * 1000;
                        record.setTimestamp(timestamp);

                        if (pauseStart != 0) {
                            pausedTime += System.currentTimeMillis() - pauseStart;
                            pauseStart = 0;
                        }
                    } else {
                        if (pauseStart == 0) pauseStart = System.currentTimeMillis();
                    }

                    Thread.sleep((long)(1000 / framerate));
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
            record.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pause(){
        paused = paused ? false : true;
        setPaused(paused);
    }

    public boolean IsPlaying(){
        return rec;
    }
}
