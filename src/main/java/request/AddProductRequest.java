package request;

import io.javalin.http.Context;

public class AddProductRequest extends SafeRequest {
    @Override
    protected void handle(Context ctx) {
        ctx.status(999).result("we handling");
        //ProductManager.getInstance().addProduct();
    }
}
