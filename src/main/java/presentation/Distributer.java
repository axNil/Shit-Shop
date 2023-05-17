package presentation;

import application.ProductManager;
import application.UserManager;
import filter.FilterCriteria;
import beans.*;
import com.google.gson.Gson;
import data.DBI;
import enums.ProductType;
import io.javalin.http.Context;
import security.Security;

import java.util.HashMap;
import java.util.List;

public class Distributer {
    private Gson gson;

    private Security security;
    private ProductManager productManager;
    private UserManager userManager;

    public Distributer() {
        gson = new Gson();
        DBI DBI = new DBI();
        security = new Security(DBI);
        productManager = new ProductManager(DBI);
        userManager = new UserManager(DBI);
    }
    public void registerNewUser(Context ctx) {
        System.out.println("create new user");
        User user = gson.fromJson(ctx.body(), User.class);

        //create user
        String response = userManager.addUser(user);
        if (response.equals("OK"))
            ctx.status(201).json(createToken(user.getUsername()));
        else
            ctx.status(400).json(createErrorMessage(response));
    }

    public void login(Context ctx) {
        Loginbean user = gson.fromJson(ctx.body(), Loginbean.class);
        String token = security.authenticateUser(user);
        if (token.equals("Not valid")) {
            ctx.status(400).json(createErrorMessage("Nope, try again"));
        } else {
            HashMap<String, String> response = new HashMap<>();
            response.put("auth_token", token);
            ctx.status(200).json(response);
            userManager.addToConnectedUsers(user, token);
        }
    }

    public void addNewProduct(Context ctx) {
        if (!checkIfValidUser(ctx)) {
            return;
        }

        Product product = gson.fromJson(ctx.body(), Product.class);
        System.out.println(product.getProductType());
        System.out.println(product.getPrice());
        System.out.println(product.getCondition());
        productManager.addNewProduct(product);
        ctx.status(201);
    }

    private boolean checkIfValidUser(Context ctx) {
        if (security.authenticateToken(ctx.header("auth_token")))
            return true;

        ctx.status(400).json(createErrorMessage("Bad token"));
        return false;
    }

    private HashMap<String, String> createErrorMessage(String errorMsg) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", errorMsg);
        response.put("status", "error");
        return response;
    }

    private HashMap<String, String> createToken(String username) {
        HashMap<String, String> response = new HashMap<>();
        response.put("auth_token", security.createToken(username));
        return response;
    }

    public void subscribeToProductType(Context ctx) {
        if (!checkIfValidUser(ctx)) {
            return;
        }

        ProductType pt = matchProductType(ctx.pathParam("ProductType"));
        String username = extractUsernameFromToken(ctx);
        userManager.subscribeToProductType(pt, username);
        ctx.status(201);
    }

    private ProductType matchProductType(String product) {
        return Enum.valueOf(ProductType.class, product);
    }

    public void getMessages(Context ctx) {
        if (!checkIfValidUser(ctx))
            return;
        String username = extractUsernameFromToken(ctx);
        List<Message> messages = userManager.getMessages(username);
        ctx.status(200).json(messages);
    }

    public void checkForUnsentMessages(Context ctx) {
        if (!checkIfValidUser(ctx))
            return;
        String username = extractUsernameFromToken(ctx);
        HashMap<String, Boolean> response = new HashMap<>();
        if (userManager.checkForUnsentMessages(username)) {
            response.put("hasUnsentMessages", true);
            ctx.status(200).json(response);
        } else {
            response.put("hasUnsentMessages", false);
            ctx.status(200).json(response);
        }
    }

    private String extractUsernameFromToken(Context ctx) {
        return ctx.header("auth_token").split(" ")[0];
    }


    public void searchProducts(Context ctx) {
        SearchBean sb = gson.fromJson(ctx.body(), SearchBean.class);

        //create filtercriterias based on json message from client
        List<FilterCriteria> criterias = CriteriaMapper.fromJSON(sb);

        //filter products based on criterias
        List<Product> result = productManager.productSearch(criterias);

        ctx.status(200).json(result);
    }
}
