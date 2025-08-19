package org.jrec.recorder;

import java.lang.Thread;

public class Recorder {

    private final Capture capture;
    private final Record record;
    private final double framerate;
    private Thread t1;
    private boolean rec = false;

    public Recorder(String fname, double framerate) throws Exception{
        this.framerate = framerate;
        this.capture = new Capture();
        this.record = new Record(fname, capture.getWidth(), capture.getHeight(), framerate);
    }
    public void start(){
        rec = true;
        long delay = (long) (1000/framerate);

        t1 = new Thread(() -> {
            try {
                while (rec) {
                    record.record(capture.shot());
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
}
