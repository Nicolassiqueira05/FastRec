package org.jrec.recorder;

import java.lang.Thread;

public class Recorder {

    private final Capture capture;
    private Record record;
    private final double framerate;
    private String fname;
    private Thread t1;
    private boolean rec = false;
    private boolean paused = false;

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
                while (rec) {
                    if(!paused){
                        record.record(capture.shot());
                        long timestamp = (System.currentTimeMillis() - startTime) * 1000; // em microssegundos
                        record.setTimestamp(timestamp);

                    }
                    Thread.sleep(delay);
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
    }

    public boolean IsPlaying(){
        return rec;
    }
}
