package application;

import application.listener.ProductListener;
import beans.*;
import beans.message.Message;
import enums.ProductType;
import data.DBI;

import java.util.ArrayList;

public class UserManager implements ProductListener {
    private final DBI DBI;
    public UserManager(DBI db) {
        DBI = db;
    }

    public String addUser(User user) {
        //check validity
        if (user.getUsername().equals("")) {
            return "Invalid username";
        }
        if (user.getPassword().equals("")) {
            return "Invalid password";
        }
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
        ArrayList<Message> inbox = DBI.getMessages(username);
        if(inbox == null) {
            return new ArrayList<>();
        }

        ArrayList<Message> messages = new ArrayList<>(inbox);

        if(messages.size() > 0) {
            DBI.setHasUnsentMessages(username, false);
            for (Message m : messages) {
                Message updated = new Message(m.getText()) { };
                updated.setSent(true);
                DBI.updateMessage(username, updated);
            }
        }
        return messages;
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
