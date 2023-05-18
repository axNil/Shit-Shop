package application;

import filter.FilterCriteria;
import beans.Product;
import data.DBI;
import enums.ProductType;

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

    public ProductType[] getProducTypes() {
        return ProductType.values();
    }
    



}
