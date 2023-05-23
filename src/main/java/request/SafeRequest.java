package request;

import application.Distributer;
import io.javalin.http.Context;

import java.util.Objects;

public abstract class SafeRequest extends Request {
    @Override
    protected final boolean auth(Context ctx) {
        boolean authOk = Distributer.getInstance().getSecurity()
                .authenticateToken(Objects.requireNonNull(ctx.header("auth_token")));
        if(!authOk) ctx.status(400).json("Bad token");
        return authOk;
    }
}
