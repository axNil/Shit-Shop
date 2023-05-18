package application;

import enums.Condition;
import filter.FilterCriteria;
import beans.Product;
import data.DBI;
import enums.ProductType;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ProductManager {
    private final DBI DBI;

    protected ProductManager(DBI db) {
        DBI = db;
    }

    public void addNewProduct(Product product) {
        product.setProductID(DBI.getProductID());
        DBI.addProduct(product);
        notifySubscribersAsync(product.getProductType());
    }
    
    public List<Product> productSearch(List<FilterCriteria> criterias) {
        List<Product> products = DBI.getProducts();
        return SearchUtils.search(products, criterias);
    }

    private void notifySubscribersAsync(ProductType productType) {
        new Thread(()-> {
            LinkedList<ProductTypeSubscriber> subscribers = DBI.getSubscribers(productType);
            if (subscribers != null) {
                for (ProductTypeSubscriber o : subscribers)
                    o.update(productType);
            }
        }).start();
    }

    public String[] getProductTypes() {
        ProductType[] productTypes = ProductType.values();
        String[] productTypesStrings = new String[productTypes.length];
        for (int i = 0; i < productTypes.length; i++) {
            productTypesStrings[i] = productTypes[i].toString();
        }
        return productTypesStrings;
    }


    public String[] getConditions() {
        Condition[] conditions = Condition.values();
        String[] conditionStrings = new String[conditions.length];
        for (int i = 0; i < conditions.length; i++) {
            conditionStrings[i] = conditions[i].toString();
        }
        return conditionStrings;
    }
}
