package org.example.recorder;

import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.image.BufferedImage;

public class Record {

    private final FFmpegFrameRecorder recorder;
    private final Java2DFrameConverter converter;

    public Record(String filename, int width, int height, double framerate) throws Exception{
        this.recorder = new FFmpegFrameRecorder(filename, width, height);
        recorder.setFrameRate(framerate);
        recorder.start();
        this.converter = new Java2DFrameConverter();
    }

    public void record(BufferedImage image) throws Exception{
        recorder.record(converter.convert(image));
    }
    public void stop() throws Exception{
        recorder.stop();
        recorder.release();
    }
}
