package request;

import application.Distributer;
import io.javalin.http.Context;

import java.util.HashMap;

public class GetUnsentMessagesRequest extends SafeRequest {
    @Override
    protected void handle(Context ctx) {
        String username = ctx.header("auth_token").split(" ")[0];
        HashMap<String, Boolean> response = new HashMap<>();
        if (Distributer.getInstance().
                getUserManager().checkForUnsentMessages(username)) {
            response.put("hasUnsentMessages", true);
            ctx.status(200).json(response);
        } else {
            response.put("hasUnsentMessages", false);
            ctx.status(200).json(response);
        }
    }
}
