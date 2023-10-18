package org.nagarro.model;

import java.util.Comparator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tshirt_data")
public class Tshirt {
    @Id
    @Column(name = "tshirt_id")
    private String id;
    @Column(name = "tshirt_name")
    private String name;
    @Column(name = "tshirt_color")
    private String color;
    @Column(name = "tshirt_genderRecommendation")
    private String genderRecommendation;
    @Column(name = "tshirt_size")
    private String size;
    @Column(name = "tshirt_price")
    private String price;
    @Column(name = "tshirt_rating")
    private String rating;
    @Column(name = "tshirt_availability")
    private String availability;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public static Comparator<Tshirt> ratingSorter =  new Comparator<Tshirt>() {
        @Override
        public int compare(Tshirt obj1, Tshirt obj2) {
            double obj1Rating = Double.parseDouble(obj1.getRating());
            double obj2Rating = Double.parseDouble(obj2.getRating());
            return Double.compare(obj1Rating,obj2Rating);
        }
    };

    public static Comparator<Tshirt> priceSorter =  new Comparator<Tshirt>() {
        @Override
        public int compare(Tshirt obj1, Tshirt obj2) {
            double obj1Price = Double.parseDouble(obj1.getPrice());
            double obj2Price = Double.parseDouble(obj2.getPrice());
            return Double.compare(obj1Price,obj2Price);
        }
    };

    public static Comparator<Tshirt> priceAndRatingSorter = new Comparator<Tshirt>() {
        @Override
        public int compare(Tshirt obj1, Tshirt obj2) {
            double obj1Price = Double.parseDouble(obj1.getPrice());
            double obj2Price = Double.parseDouble(obj2.getPrice());
            double obj1Rating = Double.parseDouble(obj1.getRating());
            double obj2Rating = Double.parseDouble(obj2.getRating());
            int result = Double.compare(obj1Price, obj2Price);
            if(result == 0) {
                result = Double.compare(obj1Rating,obj2Rating);
            }
            return result;
        }
    };
}
