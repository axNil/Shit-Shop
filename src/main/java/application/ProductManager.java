package application;

import beans.Product;
import data.Database;


public class ProductManager {
    private Database database;

    public ProductManager(Database db) {
        database = db;
    }


    public void addNewProduct(Product product) {
        product.setProductID(database.getProductID());
        database.addProduct(product);
    }
}
