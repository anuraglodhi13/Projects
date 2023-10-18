package model;

public class Insurance {

    private String type;
    private Double value;
    public Insurance(String type) {
        this.type = type;
    }
    public void setType (String type) {
        this.type = type;
    }
    public String getType() {
        return this.type;
    }
    public void setValue (Double value) {
        this.value = value;
    }
    public Double getValue() {
        return this.value;
    }
}
