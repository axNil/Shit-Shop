package request;

import application.Distributer;
import beans.Order;
import io.javalin.http.Context;
import security.Security;

import java.util.List;

public class OrderHistoryRequest extends SafeRequest {

    @Override
    protected void handle(Context ctx) {
        String username = Security.extractUsernameFromToken(ctx);
        List<Order> result = Distributer.getInstance().getOrderManager().getOrders(username);
        ctx.status(200).json(result);
    }
}
