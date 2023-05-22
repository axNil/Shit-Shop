package request;

import application.Distributer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.javalin.http.Context;
import request.utils.ResponseMessageBuilder;
import security.Security;

public class PlaceOrderRequest extends SafeRequest {
    @Override
    protected void handle(Context ctx) {
        String username = Security.extractUsernameFromToken(ctx);
        JsonArray array = new Gson().fromJson(ctx.body(), JsonArray.class);
        int productID = -1;
        for (JsonElement e : array) {
            productID = e.getAsInt();
        }

        if (productID > -1) {
            //order created
            if (Distributer.getInstance().getOrderManager().placeOrder(username, productID)) {
                ctx.status(201).result("Order placed.");
                return;
            }
        }
        ctx.status(400).result("Nope.");
    }
}
