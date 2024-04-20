package org.example;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String jarFilePath = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        // Открывает папку, содержащую jar файл
        try {
            File jarFile = new File(jarFilePath);
            Desktop.getDesktop().open(jarFile.getParentFile());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        new GUI();
    }
}
