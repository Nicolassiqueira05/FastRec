package org.jrec.window;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    public Window(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Java Recorder");
        this.setSize(500, 100);
        this.setVisible(true);
        this.setResizable(true);
    }
}
