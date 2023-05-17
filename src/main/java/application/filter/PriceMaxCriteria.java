package application.filter;

import beans.Product;

import java.util.ArrayList;
import java.util.List;

public class PriceMaxCriteria implements FilterCriteria {
    private final double maxPrice;
    public PriceMaxCriteria(double maxPrice) {
        this.maxPrice = maxPrice;
    }
    @Override
    public List<Product> meetCriteria(List<Product> products) {
        List<Product> list = new ArrayList<>();
        for (Product p : products) {
            if (p.getPrice() <= maxPrice)
                list.add(p);
        }
        return list;
    }
}
