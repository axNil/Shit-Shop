package filter;

import beans.Product;
import enums.Color;

import java.util.ArrayList;
import java.util.List;

public class ColorCriteria implements FilterCriteria {
    private Color color;
    public ColorCriteria(Color color) {
        this.color = color;
    }

    @Override
    public List<Product> meetCriteria(List<Product> products) {
        List<Product> list = new ArrayList<>();
        for (Product p : products) {
            if (p.getColor() == color)
                list.add(p);
        }
        return list;
    }
}
