package request;

import application.Distributer;
import enums.ProductType;
import io.javalin.http.Context;
import security.Security;

public class SubscribeToProductRequest extends SafeRequest {
    @Override
    protected void handle(Context ctx) {
        ProductType pt = Enum.valueOf(ProductType.class,
                ctx.pathParam("ProductType"));
        String username = Security.extractUsernameFromToken(ctx);
        Distributer.getInstance()
                .getUserManager().subscribeToProductType(pt, username);
        ctx.status(201);
    }
}
