package request;

import io.javalin.http.Context;

public abstract class UnsafeRequest extends Request {
    @Override
    protected boolean auth(Context ctx) {
        return true;
    }
}
