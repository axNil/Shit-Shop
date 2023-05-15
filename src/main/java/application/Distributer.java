package application;

import application.beans.Loginbean;
import application.beans.Product;
import application.beans.User;
import com.google.gson.Gson;
import data.Database;
import io.javalin.http.Context;
import security.Security;

import java.util.HashMap;
import java.util.LinkedList;

public class Distributer {
    private Gson gson;
    private Database database;
    private Security security;

    public Distributer() {
        gson = new Gson();
        database = new Database();
        security = new Security();
    }
    public void registerNewUser(Context ctx) {
        System.out.println("create new user");
        User user = gson.fromJson(ctx.body(), User.class);

        //check validity
        if (database.checkIfEmailExist(user.email))
            ctx.status(400).json(createErrorMessage("Email already exists"));

        if (database.checkIfUsernameExist(user.username))
            ctx.status(400).json(createErrorMessage("Username already exists"));

        //create user
        createNewUser(user);

        ctx.status(201).json(createToken());
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

    public void login(Context ctx) {
        Loginbean user = gson.fromJson(ctx.body(), Loginbean.class);

        if (database.getPassword(user.username).equals(user.password)) {
            HashMap<String, String> token = createToken();
            database.addToConnectedUsers(user.username, token.get("auth_token"));
            ctx.status(200).json(token);
        } else {
            ctx.status(400).json(createErrorMessage("Nope, try again"));
        }
    }

    public void addNewProduct(Context ctx) {
        if (security.authenticateToken(ctx.header("auth_token"))) {
            Product prod = gson.fromJson(ctx.body(), Product.class);
//            database.addProduct(prod);
            ctx.status(201);
        } else {
            ctx.status(400).json("");
        }
    }

    private HashMap<String, String> createErrorMessage(String errorMsg) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", errorMsg);
        response.put("status", "error");
        return response;
    }

    private HashMap<String, String> createToken() {
        HashMap<String, String> response = new HashMap<>();
        response.put("auth_token", security.createToken());
        return response;
    }


}
