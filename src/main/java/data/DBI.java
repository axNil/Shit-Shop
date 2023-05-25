package data;

import beans.message.Message;
import beans.Order;
import beans.Product;
import beans.User;
import enums.ProductType;

import java.util.ArrayList;
import java.util.List;

public class DBI {
    private final Database db;

    public DBI() {
        db = new Database();
    }

    public boolean checkIfUsernameExist(String username) {
        for (User u : db.selectAllUsers()) {
            if (u.getUsername().equals(username)) return true;
        }
        return false;
    }

    public boolean checkIfEmailExist(String email) {
        for (User u : db.selectAllUsers())
            if (u.getEmail().equals(email)) return true;

        return false;
    }

    public Order addOrder(String username, int productID) {
        return db.placeOrder(username, productID);
    }

    public ArrayList<Order> getOrders(int productID) {
        return db.selectProductsOrders(productID);
    }

    public ArrayList<Order> getOrders(String buyer) {
        return db.selectOrders(buyer);
    }

    public List<Order> getOrdersBySeller(String username) {
        return db.selectOrdersWhereSeller(username);
    }
    
    public Order updateOrder(Order updatedOrder) {
        return db.updateOrder(updatedOrder);
    }

    public String getSeller(int productID) {
        return db.getSeller(productID);
    }

    public void addSubscriber(ProductType pt, String username) {
        db.addWishlistSubscribtion(pt, username);
    }

    public String getPassword(String username) {
        User u = db.selectUser(username);
        return  u != null ? u.getPassword() : "";
    }

    public List<Product> getProducts() {
        return db.selectAllProducts().stream().toList();
    }

    public Product getProduct(int product_id) {
        return db.selectProductByID(product_id);
    }

    public void setProductToSold(int product_id) {
        db.setProductToSold(product_id);
    }

    public void addUser(User user) {
        db.addUser(user);
    }

    public User getUser(String username) {
        return db.selectUser(username);
    }

    public void addProduct(Product product) {
        db.addProduct(product);
    }

    public ArrayList<String> getSubscribers(ProductType productType) {
        return db.selectAllSubscribers(productType);
    }

    public void addMessage(String username, Message message) {
        db.addMessage(username, message);
        // if adding OK?
        setHasUnsentMessages(username, true);
    }

    public ArrayList<Message> getMessages(String username) {
        return db.selectMessages(username);
    }

    public void updateMessage(String username, Message updatedMsg) {
        db.updateMessage(username, updatedMsg);
    }

    public void setHasUnsentMessages(String username, boolean hasUnsent) {
        User u = db.selectUser(username);
        u.setHasUnsentMessages(hasUnsent);
        db.updateUser(username, u);
    }

    public Order getOrder(int productID, String buyer) {
        return db.getOrder(productID,buyer);
    }
}
