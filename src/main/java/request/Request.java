package request;

import io.javalin.http.Context;

public abstract class Request {
    // TODO: Add logging? Singleton? ;)
    public final void doHandle(Context ctx) {
        if (auth(ctx)) {
            handle(ctx);
        }
    }
    protected abstract boolean auth(Context ctx);
    protected abstract void handle(Context ctx);
}
