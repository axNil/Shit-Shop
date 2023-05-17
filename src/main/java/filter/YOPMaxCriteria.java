package filter;

import beans.Product;

import java.util.ArrayList;
import java.util.List;

public class YOPMaxCriteria implements FilterCriteria {
    private int maxYOP;
    public YOPMaxCriteria(int maxYearOfProduction) {
        maxYOP = maxYearOfProduction;
    }

    @Override
    public List<Product> meetCriteria(List<Product> products) {
        List<Product> list = new ArrayList<>();
        for (Product p : products) {
            if (p.getYearOfProduction() <= maxYOP)
                list.add(p);
        }
        return list;
    }
}
