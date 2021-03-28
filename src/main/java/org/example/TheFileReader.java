package org.example;

import org.example.exception.NoFileToConvertException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TheFileReader {

    public List<String> readTextFile(File src) {
        List<String> fileTextLines = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(src);
            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                fileTextLines.add(line);
            }
            fileScanner.close();
        } catch (FileNotFoundException e){
            throw new NoFileToConvertException("Something wrong with the input file");
        }
        return fileTextLines;
    }

}
