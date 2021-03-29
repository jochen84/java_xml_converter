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
        boolean toggleFamilyActive = toggleFamilyTagIfF(currentTag);

        switch (currentTag) {
            case "P":
                PersonTag personTag = (PersonTag)inputTag;
                finalXmlString.append(buildPersonTag(personTag));
                break;
            case "T":
                PhoneTag phoneTag = (PhoneTag)inputTag;
                finalXmlString.append(buildPhoneTag(phoneTag));
                break;
            case "A":
                AddressTag addressTag = (AddressTag)inputTag;
                finalXmlString.append(buildAddressTag(addressTag));
                break;
            case "F":
                FamilyTag familyTag = (FamilyTag)inputTag;
                finalXmlString.append(buildFamilyTag(familyTag));
                break;
        }
    }

    private String buildPersonTag(PersonTag personTag){
        if (firstPerson) {
            firstPerson = false;
            return "<people>" + indent[ONE] + "<person>" + indent[TWO] + personTag.getFirstNameWithTag() + indent[TWO] + personTag.getLastNameWithTag();
        } else {
            return indent[ONE] + "</person>" + indent[ONE] + "<person>" + indent[TWO] + personTag.getFirstNameWithTag() + indent[TWO] + personTag.getLastNameWithTag();
        }
    }

    private String buildPhoneTag(PhoneTag phoneTag){
        if (familyTagActive){
            return indent[THREE] + "<phone>" + indent[FOUR] + phoneTag.getMobileWithTag()+ indent[FOUR] + phoneTag.getHomeWithTag() + indent[THREE] + "</phone>";
        } else {
            return indent[TWO] + "<phone>" + indent[THREE] + phoneTag.getMobileWithTag() + indent[THREE] + phoneTag.getHomeWithTag() + indent[TWO] + "</phone>";
        }
    }

    private String buildAddressTag(AddressTag addressTag){
        if (familyTagActive) {
            return indent[THREE] + "<address>" + indent[FOUR] + addressTag.getStreetWithTag() + indent[FOUR] + addressTag.getCitytWithTag() + indent[FOUR] + addressTag.getZipcodeWithTag() + indent[THREE] + "</address>";
        } else {
            return indent[TWO] + "<address>" + indent[THREE] + addressTag.getStreetWithTag() + indent[THREE] + addressTag.getCitytWithTag() + indent[THREE] + addressTag.getZipcodeWithTag() + indent[TWO] + "</address>";
        }
    }

    private String buildFamilyTag(FamilyTag familyTag){
        return indent[TWO] + "<family>" + indent[THREE] + familyTag.getNameWithTag() + indent[THREE] + familyTag.getBornWithTag();
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

    private boolean toggleFamilyTagIfF(String currentTag){
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