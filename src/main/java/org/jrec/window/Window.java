package org.jrec.window;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private JButton b1, b2, b3, b4, b5;
    private boolean rec = false;

    public Window(Runnable m1, Runnable m2, Runnable m3) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Java Recorder");
        setResizable(true);

        JPanel jpanel = new JPanel(new GridLayout(1, 5, 5, 5));

        b1 = new JButton("Iniciar");
        b1.addActionListener(e -> {
            if(!rec)
            {
                m1.run();
                rec = true;
            }
            else
            {
                m2.run();
                m3.run();
                rec = false;
            }
        });
        b2 = new JButton("Pausar");
        b3 = new JButton("Microfone");
        b4 = new JButton("...");
        b5 = new JButton("Configurações");

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

}
