package request;

import application.Distributer;
import beans.Message;
import io.javalin.http.Context;

import java.util.List;

public class GetMessagesRequest extends SafeRequest {
    @Override
    protected void handle(Context ctx) {
        String username = ctx.header("auth_token").split(" ")[0];
        List<Message> messages = Distributer.getInstance()
                .getUserManager().getMessages(username);
        ctx.status(200).json(messages);
    }
}
