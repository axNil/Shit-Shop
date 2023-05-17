package application;

import com.google.gson.Gson;
import data.DBI;
import security.Security;

public class Distributer {

    private static final class InstanceHolder {
        private static final Distributer instance = new Distributer();
    }

    public static Distributer getInstance() {
        return InstanceHolder.instance;
    }
    private Gson gson;

    private final Security security;
    public Security getSecurity() {
        return security;
    }
    private final ProductManager productManager;
    public ProductManager getProductManager() {
        return productManager;
    }
    private final UserManager userManager;
    public UserManager getUserManager() {
        return userManager;
    }
    private final OrderManager orderManager;
    public OrderManager getOrderManager() { return orderManager; }

    private Distributer() {
        gson = new Gson();
        DBI dbi = new DBI();
        security = new Security(dbi);
        productManager = new ProductManager(dbi);
        userManager = new UserManager(dbi);
        orderManager = new OrderManager(dbi);
    }
}
