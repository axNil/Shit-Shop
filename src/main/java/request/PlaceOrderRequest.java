package request;

import application.Distributer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.javalin.http.Context;
import security.Security;

public class PlaceOrderRequest extends SafeRequest {
    @Override
    protected void handle(Context ctx) {
        String username = Security.extractUsernameFromToken(ctx);
        JsonArray array = new Gson().fromJson(ctx.body(), JsonArray.class);
        for (JsonElement e : array) {
            int productID = e.getAsInt();
            Distributer.getInstance().getOrderManager().placeOrder(username, productID);
        }
        ctx.status(201).result("Order placed.");
    }
}
