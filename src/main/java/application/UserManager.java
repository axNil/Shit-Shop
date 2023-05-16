package application;

import beans.Loginbean;
import beans.Message;
import beans.User;
import enums.ProductType;
import data.Database;

import java.util.List;

public class UserManager {
    private Database database;
    public UserManager(Database db) {
        database = db;
    }
    public String addUser(User user) {
        //check validity
        if (database.checkIfEmailExist(user.getEmail())) {
            return "Email already exists";
        }

        if (database.checkIfUsernameExist(user.getUsername())) {
            return "Username already exists";
        }

        database.addUser(user.getUsername(), user);
        return "OK";
    }

    public void addToConnectedUsers(Loginbean user, String token) {
        database.addToConnectedUsers(user.getUsername(), token);
    }

    public void subscribeToProductType(ProductType pt, String username) {
        database.addSubscriber(pt, username);
    }

    public List<Message> getMessages(String username) {
        User user = database.getUser(username);
        user.setHasUnsentMessages(false);
        return database.getUser(username).getInbox();
    }

    public boolean checkForUnsentMessages(String username) {
        return database.getUser(username).hasUnsentMessages();

    }
}
