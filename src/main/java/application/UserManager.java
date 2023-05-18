package application;

import application.listener.ProductListener;
import beans.*;
import enums.ProductType;
import data.DBI;

import java.util.ArrayList;
import java.util.List;

public class UserManager implements ProductListener {
    private final DBI DBI;
    public UserManager(DBI db) {
        DBI = db;
    }

    public String addUser(User user) {
        //check validity
        if (DBI.checkIfEmailExist(user.getEmail())) {
            return "Email already exists";
        }

        if (DBI.checkIfUsernameExist(user.getUsername())) {
            return "Username already exists";
        }

        DBI.addUser(user);
        return "OK";
    }

    public void subscribeToProductType(ProductType pt, String username) {
        DBI.addSubscriber(pt, username);
    }

    public ArrayList<Message> getMessages(String username) {
        DBI.setHasUnsentMessages(username, false);
        return DBI.getMessages(username);
    }

    public boolean checkForUnsentMessages(String username) {
        return DBI.getUser(username).hasUnsentMessages();
    }

    @Override
    public void onAdd(Product newProduct) {
        System.out.println("UserManager: onProductAdd!");
        ProductType pt = newProduct.getProductType();
        ArrayList<String> subscribers = DBI.getSubscribers(pt);

        for (String uName : subscribers) {
            System.out.println("Adding wishlist pm to user: " + uName);
            DBI.addMessage(uName, new WishlistMessage(pt));
        }
    }
}
