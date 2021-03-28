package org.example;

import org.example.exception.ToManyValuesException;
import org.example.model.*;

import java.util.ArrayList;
import java.util.List;

public class TextToObjectConverter {

    private List<Tag> objectTags = new ArrayList<>();
    private final int TAG = 0;
    private final int FIRSTNAME = 1;
    private final int LASTNAME = 2;
    private final int MOBILE = 1;
    private final int HOME = 2;
    private final int STREET = 1;
    private final int CITY = 2;
    private final int ZIP = 3;
    private final int NAME = 1;
    private final int BORN = 2;

    public List<Tag> convert (List<String> lines) {

        for ( String line : lines ) {
            String[] lineValues = line.split("\\|", -1);
            boolean hasBadComments =  checkIfValuesContainsBadComments(lineValues);
            boolean toManyValues = checkIfToManyValuesInLine(lineValues);
            textToObjectTagsBuilder(lineValues);
        }
        return objectTags;
    }

    private void textToObjectTagsBuilder(String[] lineValues) {
        String tag = lineValues[TAG];
        switch (tag) {
            case "P":
                try {
                    objectTags.add(new PersonTag(lineValues[TAG], lineValues[FIRSTNAME], lineValues[LASTNAME]));
                } catch (IndexOutOfBoundsException e) {
                    throw new IndexOutOfBoundsException("Something wrong with the input in a Person tag");
                }
                break;
            case "T":
                try {
                    objectTags.add(new PhoneTag(lineValues[TAG], lineValues[MOBILE], lineValues[HOME]));
                } catch (IndexOutOfBoundsException e) {
                    throw new IndexOutOfBoundsException("Something wrong with the input in a Phone tag");
                }
                break;
            case "A":
                try {
                    objectTags.add(new AddressTag(lineValues[TAG], lineValues[STREET], lineValues[CITY], lineValues[ZIP]));
                } catch (IndexOutOfBoundsException e) {
                    throw new IndexOutOfBoundsException("Something wrong with the input in a Address tag");
                }
                break;
            case "F":
                try {
                    objectTags.add(new FamilyTag(lineValues[TAG], lineValues[NAME], lineValues[BORN]));
                } catch (IndexOutOfBoundsException e) {
                    throw new IndexOutOfBoundsException("Something wrong with the input in a Family tag");
                }
                break;
            default:
                throw new IllegalArgumentException("Input tag: [" + tag + "], does not exist, check your text file");
        }
    }

    private boolean checkIfValuesContainsBadComments(String[] lineValues){
        for (String value : lineValues){
            if (value.contains("<!--") && !value.contains("-->")) {
                throw new IllegalArgumentException("Bad comments");
            }
        }
        return false;
    }

    private boolean checkIfToManyValuesInLine(String[] lineValues) {
        if (lineValues.length > 4){
            throw new ToManyValuesException("To many input values in the line");
        }
        return false;
    }
}
