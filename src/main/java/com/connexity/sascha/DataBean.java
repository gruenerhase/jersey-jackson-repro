package com.connexity.sascha;

public class DataBean {

    private String knownProperty1;
    private String knownProperty2;

    public String getKnownProperty1() {
        return knownProperty1;
    }

    public void setKnownProperty1(String knownProperty1) {
        this.knownProperty1 = knownProperty1;
    }

    public String getKnownProperty2() {
        return knownProperty2;
    }

    public void setKnownProperty2(String knownProperty2) {
        this.knownProperty2 = knownProperty2;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "knownProperty1='" + knownProperty1 + '\'' +
                ", knownProperty2='" + knownProperty2 + '\'' +
                '}';
    }
}
