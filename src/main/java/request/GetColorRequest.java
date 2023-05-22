package request;

import application.Distributer;
import io.javalin.http.Context;

public class GetColorRequest extends SafeRequest {
    @Override
    protected void handle(Context ctx) {
        String[] colors = Distributer.getInstance().getProductManager().getColors();
        ctx.status(200).json(colors);
    }
}
