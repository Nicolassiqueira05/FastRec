package org.jrec.window;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private JButton b1, b2, b3, b4, b5;
    static ImageIcon b1i, b2i, b3i;
    private boolean rec = false;

    public Window(Runnable m1, Runnable m2, Runnable m3) {

        b1i = new ImageIcon(this.getClass().getResource("/b1.png"));
        Image resizedb1i = b1i.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        b1i = new ImageIcon(resizedb1i);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Java Recorder");
        setResizable(true);

        JPanel jpanel = new JPanel(new GridLayout(1, 5, 5, 5));

        b1 = new JButton();
        b1.setIcon(b1i);
        setButtonStyle(b1);
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

    public void setButtonStyle(JButton button){
        button.setSize(new Dimension(40, 40));
        button.setPreferredSize(new Dimension(40, 40));
    }

}
