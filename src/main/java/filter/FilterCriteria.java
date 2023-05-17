package filter;

import beans.Product;

import java.util.List;

public interface FilterCriteria {
    List<Product> meetCriteria(List<Product> products);
}
