package application;

import filter.FilterCriteria;
import beans.Product;

import java.util.List;

public class SearchUtils {

    public List<Product> search(List<Product> products, List<FilterCriteria> criterias) {
        for (int i = 0; i < criterias.size(); i++) {
            products = criterias.get(i).meetCriteria(products);
        }
        return products;
    }

}
