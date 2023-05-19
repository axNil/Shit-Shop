package request;

import application.Distributer;
import io.javalin.http.Context;

public class GetConditionsRequest extends UnsafeRequest {
    @Override
    protected void handle(Context ctx) {
        String[] conditions = Distributer.getInstance().getProductManager().getConditions();
        ctx.status(200).json(conditions);
    }
}
