package request;

import application.Distributer;
import application.ProductManager;
import beans.Product;
import com.google.gson.Gson;
import io.javalin.http.Context;
import security.Security;

public class AddProductRequest extends SafeRequest {
    @Override
    protected void handle(Context ctx) {
        Product product = new Gson().fromJson(ctx.body(), Product.class);
        String seller = Security.extractUsernameFromToken(ctx);
        product.setSeller(seller);
        Distributer.getInstance().getProductManager().addNewProduct(product);
        ctx.status(201);
    }
}
