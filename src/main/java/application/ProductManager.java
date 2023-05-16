package application;

import beans.Product;
import data.Database;
import enums.ProductType;

import java.util.LinkedList;

public class ProductManager {
    private Database database;

    public ProductManager(Database db) {
        database = db;
    }


    public void addNewProduct(Product product) {
        product.setProductID(database.getProductID());
        database.addProduct(product);
        notifySubscribersAsync(product.getProductType());
    }

    private void notifySubscribersAsync(ProductType productType) {
        new Thread(()-> {
            LinkedList<ProductTypeSubscriber> subscribers = database.getSubscribers(productType);
            for (ProductTypeSubscriber o : subscribers)
                o.update(productType);
        }).start();
    }


}
