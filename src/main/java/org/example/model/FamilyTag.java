package org.example.model;

public class FamilyTag extends Tag {

    private String name;
    private String born;

    public FamilyTag(String tag, String name, String born) {
        super(tag);
        this.name = name;
        this.born = born;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getNameWithTag() {
        return "<name>" + name + "</name>";
    }

    public String getBornWithTag() {
        return "<born>" + born + "</born>";
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FamilyTag{");
        sb.append("name='").append(name).append('\'');
        sb.append(", born='").append(born).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
