package org.jrec.recorder;

public class Recorder {

    private final Capture capture;
    private final Record record;

    public Recorder(String fname, double framerate) throws Exception{
        this.capture = new Capture();
        this.record = new Record(fname, capture.getWidth(), capture.getHeight(), framerate);
    }
}
