package org.jrec;

import org.jrec.recorder.Recorder;
import org.jrec.window.Window;

public class Main {
    public static void main(String[] args) throws Exception {
        Recorder recorder = new Recorder("output.mp4", 30.0);
        Window window = new Window(recorder::start, recorder::stop);
    }
}