package application;

import application.beans.User;
import com.google.gson.Gson;
import data.Database;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Distributer {
    private Gson gson;
    private Database database;

    public Distributer() {
        gson = new Gson();
    }
    public String registerNewUser(String body) {
        System.out.println("create new user");
        User user = gson.fromJson(body, User.class);

        //check validity
        if (database.checkIfEmailExist(user.email))
            return gson.toJson(new Error("Email already exists"));
        if (database.checkIfUsernameExist(user.username))
            return gson.toJson(new Error("Username already exists"));

        //create user
        createNewUser(user);

        return createToken();
    }

    private String createToken() {
        return gson.toJson(LocalDateTime.now().toString());
    }

    private void createNewUser(User user) {
        LinkedList<String> userData = new LinkedList<>();
        userData.add(user.firstName);
        userData.add(user.lastName);
        userData.add(user.dob);
        userData.add(user.email);
        userData.add(user.password);
        userData.add(user.id);
        database.addUser(user.username, userData);
    }
}
