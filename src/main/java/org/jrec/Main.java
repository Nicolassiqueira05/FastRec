package org.jrec;

import org.jrec.recorder.Recorder;
import org.jrec.window.Window;

public class Main {
    public static void main(String[] args) {
        Window window = new Window();
        try {
            Recorder recorder = new Recorder("output.mp4", 30.0);

            System.out.println("Iniciando gravação...");
            recorder.start();

            recorder.stop();
            System.out.println("Gravação concluída: output.mp4");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}