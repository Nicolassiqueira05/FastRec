package org.example.recorder;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Capture {
    private final Robot robot;
    private final Rectangle screen;

    public Capture() throws AWTException {
        this.robot = new Robot();
        this.screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    }

    public BufferedImage shot() {
        return robot.createScreenCapture(screen);
    }

    public int getWidth() { return screen.width; }

    public int getHeight() {
        return screen.height;
    }
}
