package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TheFileReader {

    public List<String> readTextFile(File src) throws FileNotFoundException {
        List<String> fileTextLines = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(src);
            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                fileTextLines.add(line);
            }
            fileScanner.close();
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("File not found");
        }
        return fileTextLines;
    }

}
