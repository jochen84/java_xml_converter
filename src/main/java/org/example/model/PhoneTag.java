package org.example.model;

public class PhoneTag extends Tag {

    private String mobile;
    private String home;

    public PhoneTag(String tag, String mobile, String home) {
        super(tag);
        this.mobile = mobile;
        this.home = home;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getMobileWithTag(){
        return "<mobile>" + mobile  + "</mobile>";
    }

    public String getHomeWithTag(){
        return "<home>" + home  + "</home>";
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PhoneTag{");
        sb.append("mobile='").append(mobile).append('\'');
        sb.append(", home='").append(home).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
