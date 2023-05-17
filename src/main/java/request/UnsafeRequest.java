package request;

import io.javalin.http.Context;

public abstract class UnsafeRequest extends Request {
    @Override
    protected final boolean auth(Context ctx) {
        return true;
    }
}
