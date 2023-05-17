package request;

import io.javalin.http.Context;

public abstract class SafeRequest extends Request {
    @Override
    protected boolean auth(Context ctx) {
        System.out.println("authorize");
        return true;
    }
}
