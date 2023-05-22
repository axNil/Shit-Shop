package request;

import application.Distributer;
import beans.Order;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.javalin.http.Context;
import security.Security;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ApprovedOrdersRequest extends SafeRequest {
    @Override
    protected void handle(Context ctx) {
        String username = Security.extractUsernameFromToken(ctx);
        JsonObject body = new Gson().fromJson(ctx.body(), JsonObject.class);
        LocalDate date1;
        LocalDate date2;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (body != null) {
            String d1 = body.get("startDate").getAsString();
            String d2 = body.get("endDate").getAsString();
            if (!d1.equals("")) {
                date1 = LocalDate.parse(d1, df);
            } else {
                date1 = LocalDate.MIN;
            }

            if (!d2.equals("")) {
                date2 = LocalDate.parse(d2, df);
            } else {
                date2 = LocalDate.MAX;
            }
        } else {
            date1 = LocalDate.MIN;
            date2 = LocalDate.MAX;
        }

        ArrayList<Order> orders = Distributer.getInstance().getOrderManager().getApprovedOrders(username, date1, date2);
        ctx.status(200).json(orders);
    }
}
