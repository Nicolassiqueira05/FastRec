package org.jrec.window;

import org.jrec.recorder.Settings;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SettingsWindow extends JFrame {

    public SettingsWindow() {
        setTitle("Configurações");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel folderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel folderLabel = new JLabel("Records Folder:");
        JLabel currentFolderLabel = new JLabel("C:\\Users\\Nicolas\\Documents\\Recordings");
        JButton chooseFolderButton = new JButton("Browse");
        folderPanel.add(folderLabel);
        folderPanel.add(currentFolderLabel);
        folderPanel.add(chooseFolderButton);
        mainPanel.add(folderPanel);
        mainPanel.add(Box.createVerticalStrut(15));

        chooseFolderButton.addActionListener((ActionEvent e) -> {
            Settings.chooseFolder(this);
            currentFolderLabel.setText(Settings.getFolderuri());
        });

        JPanel fpsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel fpsLabel = new JLabel("FPS:");
        String[] fpsOptions = {"30", "60", "120"};
        JComboBox<String> fpsComboBox = new JComboBox<>(fpsOptions);
        fpsPanel.add(fpsLabel);
        fpsPanel.add(fpsComboBox);
        mainPanel.add(fpsPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        JButton saveButton = new JButton("Salvar");
        JButton resetButton = new JButton("Redefinir");
        buttonsPanel.add(saveButton);
        buttonsPanel.add(resetButton);
        mainPanel.add(buttonsPanel);

        saveButton.addActionListener(e -> {
            int fps = Integer.parseInt((String) fpsComboBox.getSelectedItem());
            Settings.setFramerate(fps);
            JOptionPane.showMessageDialog(this, "Configurações salvas!\nFPS: " + fps +
                    "\nFolder: " + Settings.getFolderuri());
            setVisible(false);
        });

        resetButton.addActionListener(e -> {
            fpsComboBox.setSelectedIndex(0);
            Settings.setFramerate(0);
            Settings.setFolderuri(null);
            currentFolderLabel.setText("C:\\Users\\Nicolas\\Documents\\Recordings");
            JOptionPane.showMessageDialog(this, "Configurações redefinidas!");
        });

        add(mainPanel);
        setSize(520, 180);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
