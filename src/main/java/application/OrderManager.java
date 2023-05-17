package application;

import data.DBI;

public class OrderManager {
    private final DBI DBI;

    protected OrderManager(DBI db) {
        DBI = db;
    }

    public void placeOrder(String username, int productID) {
        DBI.addOrder(username, productID);
        // notify SELLER!
    }

    public void acceptOrder(Object order) {
        // notify BUYER + pending buyers!
    }
}
