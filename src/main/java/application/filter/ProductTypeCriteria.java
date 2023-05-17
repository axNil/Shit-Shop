package application.filter;

import beans.Product;
import enums.ProductType;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeCriteria implements FilterCriteria{
    private ProductType productType;
    public ProductTypeCriteria(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public List<Product> meetCriteria(List<Product> products) {
        List<Product> list = new ArrayList<>();
        for (Product p : products) {
            if (p.getProductType() == productType)
                list.add(p);
        }
        return list;
    }
}
