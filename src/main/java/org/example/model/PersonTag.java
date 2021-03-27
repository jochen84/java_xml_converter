package org.example.model;

public class PersonTag extends Tag {


    private String firstName;
    private String lastName;

    public PersonTag(String tag, String firstName, String lastName) {
        super(tag);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstNameWithTag(){
        return "<firstname>" + firstName + "</firstname>";
    }

    public String getLastNameWithTag(){
        return "<lastname>" + lastName + "</lastname>";
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonTag{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
