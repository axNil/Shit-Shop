package application;

import beans.Loginbean;
import beans.Message;
import beans.Product;
import beans.User;
import enums.ProductType;
import data.DBI;

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

        DBI.addUser(user.getUsername(), user);
        return "OK";
    }

    public void addToConnectedUsers(Loginbean user, String token) {
        DBI.addToConnectedUsers(user.getUsername(), token);
    }

    public void subscribeToProductType(ProductType pt, String username) {
        DBI.addSubscriber(pt, username);
    }

    public List<Message> getMessages(String username) {
        User user = DBI.getUser(username);
        user.setHasUnsentMessages(false);
        return DBI.getUser(username).getInbox();
    }

    public boolean checkForUnsentMessages(String username) {
        return DBI.getUser(username).hasUnsentMessages();

    }

    @Override
    public void onAdd(Product newProduct) {
        // TODO: Implement
        // User[] users = DBI.getSubscribedUsers(productType);
        // for (User u in users)
        //  u.inbox.add(new WishlistMessage("New products you subcribed on!"));
    }
}
