package org.jrec.window;

import org.jrec.recorder.Settings;

import javax.swing.*;
import java.awt.*;
import static org.jrec.RecorderManager.*;

public class Window extends JFrame {

    private JButton b1, b2, b3, b4, b5;
    private boolean rec = false;

    public Window(Runnable m1, Runnable m2, Runnable m3, Runnable m4, Runnable m5, Runnable m6) {
        Settings settings = new Settings();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Fast Recorder");
        setResizable(false);

        JPanel jpanel = new JPanel(new GridLayout(1, 5, 10, 10));

        b1 = new JButton();
        setButtonStyle(b1, getResizedIcon("/b1.png"));
        b1.addActionListener(e -> {
            if (!rec) {
                m1.run();
                rec = true;
            } else {
                m2.run();
                m3.run();
                rec = false;
            }
        });

        b2 = new JButton();
        setButtonStyle(b2, getResizedIcon("/pause.png"));
        b2.addActionListener(e -> {
            if (isPaused()) {
                m5.run();
                setButtonStyle(b2, getResizedIcon("/pause.png"));
            } else {
                m4.run();
                setButtonStyle(b2, getResizedIcon("/play.png"));
            }
        });

        b3 = new JButton();
        setButtonStyle(b3, getResizedIcon("/mic.png"));

        b4 = new JButton();
        setButtonStyle(b4, getResizedIcon("/folder.png"));
        b4.addActionListener(e -> m6.run());

        b5 = new JButton();
        setButtonStyle(b5, getResizedIcon("/settings.png"));
        b5.addActionListener(e -> {
            SettingsWindow sw = new SettingsWindow();
        });

        jpanel.add(b1);
        jpanel.add(b2);
        jpanel.add(b3);
        jpanel.add(b4);
        jpanel.add(b5);

        add(jpanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setButtonStyle(JButton button, ImageIcon icon) {
        button.setIcon(icon);
        button.setText(null);
        button.setFocusPainted(false);
        button.setMargin(new Insets(5, 5, 5, 5));
    }

    public ImageIcon getResizedIcon(String path) {
        ImageIcon BIcon = new ImageIcon(this.getClass().getResource(path));
        Image resizedBIcon = BIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedBIcon);
    }
}