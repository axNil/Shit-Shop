package application;

import enums.Color;
import enums.Condition;
import application.listener.ProductListener;
import filter.FilterCriteria;
import beans.Product;
import data.DBI;
import enums.ProductType;

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

    public String[] getColors() {
        Color[] colors = Color.values();
        String[] colorStrings = new String[colors.length];
        for (int i = 0; i < colors.length; i++) {
            colorStrings[i] = colors[i].toString();
        }
        return colorStrings;
    }
}
