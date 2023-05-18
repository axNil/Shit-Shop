package request;

import application.Distributer;
import beans.Order;
import com.google.gson.Gson;
import enums.OrderStatus;
import io.javalin.http.Context;

public class ProcessOrderRequest extends SafeRequest {
    @Override
    protected void handle(Context ctx) {
        Order processedOrder = new Gson().fromJson(ctx.body(), Order.class);
        if(processedOrder.getStatus() == OrderStatus.APPROVED) {
            Distributer.getInstance().getOrderManager().approveOrder(processedOrder);
            ctx.status(201).result("Order approved.");
        } else if (processedOrder.getStatus() == OrderStatus.DECLINED) {
            Distributer.getInstance().getOrderManager().declineOrder(processedOrder);
            ctx.status(201).result("Order declined.");
        }
    }
}
