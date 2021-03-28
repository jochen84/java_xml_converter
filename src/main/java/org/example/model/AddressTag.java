package org.example.model;

public class AddressTag extends Tag {

    private String street;
    private String city;
    private String zipCode;

    public AddressTag(String tag, String street, String city, String zipCode) {
        super(tag);
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreetWithTag() {
        return street.isEmpty() ? "<street/>" : "<street>" + street + "</street>";
    }
    public String getCitytWithTag() {
        return city.isEmpty() ? "<city/>" : "<city>" + city + "</city>";
    }
    public String getZipcodeWithTag() {
        return  zipCode.isEmpty() ? "<zipcode/>" : "<zipcode>" + zipCode + "</zipcode>";
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AddressTag{");
        sb.append("street='").append(street).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", zipCode='").append(zipCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
