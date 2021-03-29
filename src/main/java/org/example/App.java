package org.example;

import org.example.model.Tag;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main( String[] args ) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file you want to convert: (Just press enter to use konvertera2.txt in input folder");
        String filePath = sc.nextLine();
        filePath = filePath.isEmpty() ? "input/konvertera2.txt" : filePath;
        File inputFile = new File(filePath);

        CreateNewXmlFileName convertFileName = new CreateNewXmlFileName();
        String xmlFileName = convertFileName.getNewFileName(inputFile.getName());

        File outputFile = new File("output/" + xmlFileName);

        TheFileReader reader = new TheFileReader();
        List<String> linesFromFile = reader.readTextFile(inputFile);

        TextToObjectConverter ttoConverter = new TextToObjectConverter();
        List<Tag> objectTags = ttoConverter.convert(linesFromFile);

        ObjectToXMLConverter xmlConverter = new ObjectToXMLConverter();
        String finalString = xmlConverter.convert(objectTags);

        TheFileWriter writer = new TheFileWriter();
        String writtenText = writer.writeToXmlFile(outputFile, finalString);
    }
}
