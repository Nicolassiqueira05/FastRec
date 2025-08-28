package org.jrec.file;
import java.awt.*;
import java.awt.desktop.*;
import java.io.File;
import java.io.IOException;

public class RecorderFolder {
    public void openFolder(){
        Desktop desktop = Desktop.getDesktop();
        File dir = null;
        try{
            dir = new File("records");
            desktop.open(dir);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
