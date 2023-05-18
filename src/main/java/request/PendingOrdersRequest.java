package request;

import application.Distributer;
import beans.Order;
import com.google.gson.Gson;
import com.google.gson.JsonPrimitive;
import io.javalin.http.Context;
import security.Security;

import java.util.List;

public class PendingOrdersRequest extends SafeRequest {

    @Override
    protected void handle(Context ctx) {
        String seller = Security.extractUsernameFromToken(ctx); // TODO: Make sure the product listing has the same seller!

        // product_id from body!
        JsonPrimitive id = new Gson().fromJson(ctx.body(), JsonPrimitive.class);

        List<Order> result = Distributer.getInstance().getOrderManager().getOrders(id.getAsInt());

        ctx.status(200).json(result);
    }
}
