package org.jrec.window;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private JButton b1, b2, b3, b4, b5;
    static ImageIcon b1i, b2i, b3i;
    private boolean rec = false;

    public Window(Runnable m1, Runnable m2, Runnable m3, Runnable m4) {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Fast Recorder");
        setResizable(false);

        JPanel jpanel = new JPanel(new GridLayout(1, 5, 10, 10));

        b1 = new JButton();
        setButtonStyle(b1, getResizedIcon("/b1.png"));
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
        b2 = new JButton();
        setButtonStyle(b2, getResizedIcon("/b2.png"));
        b2.addActionListener(e -> {
            m4.run();
        });
        b3 = new JButton();
        b4 = new JButton();
        b5 = new JButton();

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

    public void setButtonStyle(JButton button, ImageIcon icon){
        button.setIcon(icon);
        button.setText(null); // sem texto
        button.setFocusPainted(false);
        button.setMargin(new Insets(5, 5, 5, 5)); // margens internas
        // NÃO mexa no Border nem ContentAreaFilled, assim o Swing usa o estilo padrão
    }

    public ImageIcon getResizedIcon(String path){
        ImageIcon BIcon = new ImageIcon(this.getClass().getResource(path));
        Image resizedBIcon = BIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedBIcon);
    }

}
