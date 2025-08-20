package org.jrec.file;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Rename {
    public static void rename() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedNow = now.format(formatter);

        File file = new File("cache/output.mp4");
        File newFile = new File("cache/recording_" + formattedNow + ".mp4");

        if (file.exists()) {
            boolean success = file.renameTo(newFile);
            if (success) {
                System.out.println("Arquivo renomeado para: " + newFile.getName());
            } else {
                System.out.println("Falha ao renomear o arquivo.");
            }
        } else {
            System.out.println("Arquivo 'output.mp4' não encontrado.");
        }
    }
}
