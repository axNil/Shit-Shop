package request;

import application.Distributer;
import application.ProductManager;
import beans.Product;
import com.google.gson.Gson;
import io.javalin.http.Context;

public class AddProductRequest extends SafeRequest {
    @Override
    protected void handle(Context ctx) {
        Product product = new Gson().fromJson(ctx.body(), Product.class);
        Distributer.getInstance().getProductManager().addNewProduct(product);
        ctx.status(201);
    }
}
