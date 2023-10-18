package model;

public class Car {
    private String model;
    private String type;
    private Integer costPrice;

    public Car(String model, String type, Integer costPrice) {
        this.model = model;
        this.type = type;
        this.costPrice = costPrice;
    }

    public void setModel (String model) {
        this.model = model;
    }

    public String getModel() {
        return this.model;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setCostPrice(Integer costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getCostPrice() {
        return this.costPrice;
    }
}
