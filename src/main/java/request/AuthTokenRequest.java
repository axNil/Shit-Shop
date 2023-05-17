package request;

import application.Distributer;
import beans.Loginbean;
import com.google.gson.Gson;
import io.javalin.http.Context;

import java.util.HashMap;

public class AuthTokenRequest extends UnsafeRequest {
    @Override
    protected void handle(Context ctx) {
        Loginbean user = new Gson().fromJson(ctx.body(), Loginbean.class);
        String token = Distributer.getInstance().getSecurity().authenticateUser(user);
        if (token.equals("Not valid")) {
            ctx.status(400).json(MessageBuilder.createErrorMessage("Invalid auth_token."));
        } else {
            HashMap<String, String> response = new HashMap<>();
            response.put("auth_token", token);
            ctx.status(200).json(response);
            Distributer.getInstance().getUserManager().addToConnectedUsers(user, token);
        }
    }
}
