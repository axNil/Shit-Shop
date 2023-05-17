package filter;

import beans.Product;

import java.util.ArrayList;
import java.util.List;

public class YOPMinCriteria implements FilterCriteria {
    private int minYOP;
    public YOPMinCriteria(int minYearOfProduction) {
        minYOP = minYearOfProduction;
    }

    @Override
    public List<Product> meetCriteria(List<Product> products) {
        List<Product> list = new ArrayList<>();
        for (Product p : products) {
            if (p.getYearOfProduction() >= minYOP)
                list.add(p);
        }
        return list;
    }
}
