package com.nagarro.service;

import java.util.Comparator;

import com.nagarro.model.TshirtData;

public class SortingService {
	public static Comparator<TshirtData> ratingSorter =  new Comparator<TshirtData>() {
        @Override
        public int compare(TshirtData obj1, TshirtData obj2) {
            double obj1Rating = Double.parseDouble(obj1.getRating());
            double obj2Rating = Double.parseDouble(obj2.getRating());
            return Double.compare(obj1Rating,obj2Rating);
        }
    };

    public static Comparator<TshirtData> priceSorter =  new Comparator<TshirtData>() {
        @Override
        public int compare(TshirtData obj1, TshirtData obj2) {
            double obj1Price = Double.parseDouble(obj1.getPrice());
            double obj2Price = Double.parseDouble(obj2.getPrice());
            return Double.compare(obj1Price,obj2Price);
        }
    };

    public static Comparator<TshirtData> priceAndRatingSorter = new Comparator<TshirtData>() {
        @Override
        public int compare(TshirtData obj1, TshirtData obj2) {
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
