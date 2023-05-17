package request;

import io.javalin.http.Context;
import security.Security;

public abstract class SafeRequest extends Request {
    @Override
    protected final boolean auth(Context ctx) {
        boolean authOk = Security.authenticateToken(ctx.header("auth_token"));
        if(!authOk) ctx.status(400);
        return authOk;
    }
}
