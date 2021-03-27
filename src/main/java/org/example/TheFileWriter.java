package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TheFileWriter {

    public String writeToXmlFile(File destination, String text){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
