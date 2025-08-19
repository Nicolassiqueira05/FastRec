package org.jrec.recorder;

import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;

import java.awt.image.BufferedImage;

public class Record {
    private final FFmpegFrameRecorder recorder;
    private final Java2DFrameConverter converter;

    public Record(String filename, int width, int height, double framerate) throws Exception {
        recorder = new FFmpegFrameRecorder(filename, width, height);
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        recorder.setFormat("mp4");
        recorder.setFrameRate(framerate);

        recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);

        recorder.setVideoBitrate(10_000_000);
        recorder.setVideoOption("preset", "ultrafast");
        recorder.setVideoOption("tune", "zerolatency");

        recorder.start();

        converter = new Java2DFrameConverter();
    }

    public void record(BufferedImage image) throws Exception {
        BufferedImage bgrImage = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR
        );
        bgrImage.getGraphics().drawImage(image, 0, 0, null);

        recorder.record(converter.convert(bgrImage));
    }

    public void stop() throws Exception {
        recorder.stop();
        recorder.release();
    }
}
