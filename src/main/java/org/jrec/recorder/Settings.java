package org.jrec.recorder;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Settings {

    private static double framerate;
    private static String folderuri;

    // Métodos estáticos para acessar os campos
    public static void setFramerate(double f) {
        framerate = f;
    }

    public static double getFramerate() {
        return framerate;
    }

    public static void setFolderuri(String uri) {
        folderuri = uri;
    }

    public static String getFolderuri() {
        return folderuri;
    }

    // Método estático para escolher pasta
    public static void chooseFolder(Component parent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Escolha uma pasta");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        int result = chooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = chooser.getSelectedFile();
            setFolderuri(selectedFolder.getAbsolutePath()); // já seta diretamente
        }
    }
}
