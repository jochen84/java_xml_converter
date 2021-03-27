package org.example;

import org.example.model.Tag;

import java.io.*;
import java.util.List;

public class App {

    public static void main( String[] args ) throws FileNotFoundException {

        //Scanner sc = new Scanner(System.in);
        //System.out.println("Enter file you want to convert:");
        //String filePath = sc.nextLine();
        //File inputFile = new File(filePath);
        File inputFile = new File("input/konvertera1.txt");

        ConvertFileName convertFileName = new ConvertFileName();
        String newFileName = convertFileName.getNewFileName(inputFile.getName());

        File outputFile = new File("output/" + newFileName);

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