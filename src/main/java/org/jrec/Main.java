package org.jrec;

import org.jrec.recorder.Recorder;

public class Main {
    public static void main(String[] args) {
        try {
            Recorder recorder = new Recorder("output.mp4", 30.0);

            System.out.println("Iniciando gravação...");
            recorder.start();

            Thread.sleep(5000);

            recorder.stop();
            System.out.println("Gravação concluída: output.mp4");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}