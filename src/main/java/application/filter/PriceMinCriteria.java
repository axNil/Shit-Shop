package application.filter;

import beans.Product;

import java.util.ArrayList;
import java.util.List;

public class PriceMinCriteria implements FilterCriteria {
    private final double minPrice;
    public PriceMinCriteria(double minPrice) {
        this.minPrice = minPrice;
    }
    @Override
    public List<Product> meetCriteria(List<Product> products) {
        List<Product> list = new ArrayList<>();
        for (Product p : products) {
            if (p.getPrice() >= minPrice)
                list.add(p);
        }
        return list;
    }
}
