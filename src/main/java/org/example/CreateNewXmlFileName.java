package org.example;

public class CreateNewXmlFileName {

    public String getNewFileName(String fileName){
        String newFile = changeFileExtension(fileName);
        return newFile;
    }

    private String changeFileExtension(String filename){
        String newFilename = filename.replace(".txt", ".xml");
        return newFilename;
    }
}
