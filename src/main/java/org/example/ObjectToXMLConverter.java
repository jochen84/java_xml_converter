package org.example;

import org.example.model.*;

import java.util.List;

public class ObjectToXMLConverter {

    private boolean familyTagActive = false;
    private boolean firstPerson = true;
    private boolean firstTag = true;
    private String[] indent = {"\n\t", "\n\t\t", "\n\t\t\t", "\n\t\t\t\t"};
    private int ONE = 0;
    private int TWO = 1;
    private int THREE = 2;
    private int FOUR = 3;

    private StringBuilder finalXmlString = new StringBuilder();

    public String convert(List<Tag> objectTags) {

        for (Tag objTag : objectTags ) {
            objectToXmlTag(objTag);
        }

        closePeopleXmlTagsWhenEndOfList();
        return finalXmlString.toString();
    }

    private void objectToXmlTag(Tag inputTag){
        String currentTag = inputTag.getTag();

        boolean isFirstTag = throwErrorIfFirstTagNotEqualsP(currentTag);
        boolean isFamilyActive = closeFamilyTagIfActive(currentTag);
        boolean toggleFamilyActive = toggleFamilyTag(currentTag);

        switch (currentTag) {
            case "P":
                PersonTag personTag = (PersonTag)inputTag;
                if (firstPerson) {
                    finalXmlString.append("<people>" + indent[ONE] + "<person>" + indent[TWO] + personTag.getFirstNameWithTag() + indent[TWO] + personTag.getLastNameWithTag());
                    firstPerson = false;
                } else {
                    finalXmlString.append(indent[ONE] + "</person>" + indent[ONE] + "<person>" + indent[TWO] + personTag.getFirstNameWithTag() + indent[TWO] + personTag.getLastNameWithTag());
                }
                break;
            case "T":
                PhoneTag phoneTag = (PhoneTag)inputTag;
                if (familyTagActive){
                    finalXmlString.append(indent[THREE] + "<phone>" + indent[FOUR] + phoneTag.getMobileWithTag()+ indent[FOUR] + phoneTag.getHomeWithTag() + indent[THREE] + "</phone>");
                } else {
                    finalXmlString.append(indent[TWO] + "<phone>" + indent[THREE] + phoneTag.getMobileWithTag() + indent[THREE] + phoneTag.getHomeWithTag() + indent[TWO] + "</phone>");
                }
                break;
            case "A":
                AddressTag addressTag = (AddressTag)inputTag;
                if (familyTagActive) {
                    finalXmlString.append(indent[THREE] + "<address>" + indent[FOUR] + addressTag.getStreetWithTag() + indent[FOUR] + addressTag.getCitytWithTag() + indent[FOUR] + addressTag.getZipcodeWithTag() + indent[THREE] + "</address>");
                } else {
                    finalXmlString.append(indent[TWO] + "<address>" + indent[THREE] + addressTag.getStreetWithTag() + indent[THREE] + addressTag.getCitytWithTag() + indent[THREE] + addressTag.getZipcodeWithTag() + indent[TWO] + "</address>");
                }
                break;
            case "F":
                FamilyTag familyTag = (FamilyTag)inputTag;
                finalXmlString.append(indent[TWO] + "<family>" + indent[THREE] + familyTag.getNameWithTag() + indent[THREE] + familyTag.getBornWithTag());
                break;
        }
    }

    private boolean throwErrorIfFirstTagNotEqualsP(String currentTag){
        if (firstTag && !currentTag.equals("P")) {
            throw new IllegalArgumentException("First tag cant be: [" + currentTag + "], it must be [P]");
        }
        firstTag = false;
        return true;
    }

    private boolean closeFamilyTagIfActive(String currentTag){
        if(currentTag.equals("P") && familyTagActive || currentTag.equals("F") && familyTagActive){
            finalXmlString.append(indent[TWO] + "</family>");
            familyTagActive = false;
        }
        return familyTagActive;
    }

    private boolean toggleFamilyTag(String currentTag){
        if (currentTag.equals("F")){
            familyTagActive = !familyTagActive;
        }
        return familyTagActive;
    }

    private void closePeopleXmlTagsWhenEndOfList(){
        if (familyTagActive){
            finalXmlString.append(indent[TWO] + "</family>" + indent[ONE] + "</person>\n</people>");
        } else {
            finalXmlString.append(indent[ONE] + "</person>\n</people>");
        }
    }
}