package application;

import application.filter.FilterCriteria;
import beans.Product;
import beans.SearchBean;
import data.Database;
import enums.ProductType;

import java.util.LinkedList;
import java.util.List;

public class ProductManager {
    private Database database;
    private SearchUtils searchUtils;

    public ProductManager(Database db) {
        database = db;
        searchUtils = new SearchUtils();
    }


    public void addNewProduct(Product product) {
        product.setProductID(database.getProductID());
        database.addProduct(product);
        notifySubscribersAsync(product.getProductType());
    }
    
    public List<Product> productSearch(List<FilterCriteria> criterias) {
        List<Product> products = database.getProducts();
        return searchUtils.search(products, criterias);
    }

    private void notifySubscribersAsync(ProductType productType) {
        new Thread(()-> {
            LinkedList<ProductTypeSubscriber> subscribers = database.getSubscribers(productType);
            if (subscribers != null) {
                for (ProductTypeSubscriber o : subscribers)
                    o.update(productType);
            }
        }).start();
    }
    



}
