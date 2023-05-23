package request;

import application.Distributer;
import beans.User;
import com.google.gson.Gson;
import io.javalin.http.Context;
import request.utils.ResponseMessageBuilder;

import java.util.HashMap;

public class RegisterUserRequest extends UnsafeRequest {
    @Override
    protected void handle(Context ctx) {
        System.out.println("create new user");
        User user = new Gson().fromJson(ctx.body(), User.class);
        user.setHasUnsentMessages(false);
        //create user
        String result = Distributer.getInstance().getUserManager().addUser(user);
        if (result.equals("OK")) {
            HashMap<String, String> response = new HashMap<>();
            response.put("auth_token", Distributer.getInstance().
                    getSecurity().createToken(user.getUsername()));

            ctx.status(201).json(response);
        } else {
            ctx.status(400).json(ResponseMessageBuilder.createErrorMessage(result));
        }
    }
}
