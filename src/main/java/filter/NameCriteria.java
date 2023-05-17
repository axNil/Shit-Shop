package filter;

import beans.Product;

import java.util.ArrayList;
import java.util.List;

public class NameCriteria implements FilterCriteria {
    private String name;
    public NameCriteria(String name) {
        this.name = name;
    }

    @Override
    public List<Product> meetCriteria(List<Product> products) {
        List<Product> list = new ArrayList<>();
        for (Product p : products) {
            if (p.getName().equals(name))
                list.add(p);
        }
        return list;
    }
}
