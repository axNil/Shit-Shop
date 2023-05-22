package request;

import application.Distributer;
import beans.Order;
import io.javalin.http.Context;
import security.Security;

import java.util.ArrayList;

public class ApprovedOrdersRequest extends SafeRequest {
    @Override
    protected void handle(Context ctx) {
        String username = Security.extractUsernameFromToken(ctx);
        ArrayList<Order> orders = Distributer.getInstance().getOrderManager().getApprovedOrders(username);
        ctx.status(200).json(orders);
    }
}
