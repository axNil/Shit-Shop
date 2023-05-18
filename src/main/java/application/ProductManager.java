package application;

import application.listener.ProductListener;
import filter.FilterCriteria;
import beans.Product;
import data.DBI;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private final DBI DBI;

    protected ProductManager(DBI db) {
        DBI = db;
    }

    private final ArrayList<ProductListener> listeners = new ArrayList<>();
    public void addListener(ProductListener listener) {
        listeners.add(listener);
    }
    public void onProductAdded(Product newProduct) {
        new Thread(()-> {
            for (ProductListener l : listeners) {
                l.onAdd(newProduct);
            }
        }).start();
    }

    public void addNewProduct(Product product) {
        DBI.addProduct(product);
        onProductAdded(product);
    }
    
    public List<Product> productSearch(List<FilterCriteria> criterias) {
        List<Product> products = DBI.getProducts();
        return SearchUtils.search(products, criterias);
    }
}
