package request;

import application.Distributer;
import enums.ProductType;
import io.javalin.http.Context;

public class GetProductTypesRequest extends UnsafeRequest {
    @Override
    protected void handle(Context ctx) {
        String[] productTypes = Distributer.getInstance().getProductManager().getProductTypes();
        ctx.status(200).json(productTypes);
    }
}
