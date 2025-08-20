package org.jrec;

import org.jrec.file.Rename;
import org.jrec.recorder.Recorder;
import org.jrec.window.Window;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {

        File pasta = new File("records");
        if (!pasta.exists()) pasta.mkdirs();

        Recorder recorder = new Recorder("records/output.mp4", 60.0);
        Window window = new Window(recorder::start, recorder::stop, Rename::rename);

    }
}