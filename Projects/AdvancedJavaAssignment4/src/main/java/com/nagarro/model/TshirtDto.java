package com.nagarro.model;

public class TshirtDto {
    private String color;
    private String genderRecommendation;
    private String size;
    private String outputPreference;

    public TshirtDto(String color, String genderRecommendation, String size, String outputPreference) {
        this.color = color;
        this.genderRecommendation = genderRecommendation;
        this.size = size;
        this.outputPreference = outputPreference;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGenderRecommendation() {
        return genderRecommendation;
    }

    public void setGenderRecommendation(String genderRecommendation) {
        this.genderRecommendation = genderRecommendation;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getOutputPreference() {
        return outputPreference;
    }

    public void setOutputPreference(String outputPreference) {
        this.outputPreference = outputPreference;
    }
}
