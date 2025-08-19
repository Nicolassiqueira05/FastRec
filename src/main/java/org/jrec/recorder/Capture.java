package org.jrec.recorder;
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
        BufferedImage image = robot.createScreenCapture(screen);

        Point mousePos = MouseInfo.getPointerInfo().getLocation();

        Graphics2D g = image.createGraphics();
        g.setColor(Color.BLACK);
        int cursorSize = 10;
        g.fillOval(mousePos.x, mousePos.y, cursorSize, cursorSize);
        g.dispose();

        return image;
    }

    public int getWidth() { return screen.width; }

    public int getHeight() {
        return screen.height;
    }
}
