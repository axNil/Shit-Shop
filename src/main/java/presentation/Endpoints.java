package presentation;

import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;
import request.AddProductRequest;

public class Endpoints {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.plugins.enableCors(cors -> {
                cors.add(CorsPluginConfig::anyHost);
            });
        }).start(5008);
        Distributer disty = new Distributer();

        //register new user
        app.post("/user", disty::registerNewUser);

        //get access token
        app.post("/token", disty::login);

        //TODO: search product
        app.get("/product", disty::searchProducts);

        //subscribe to product type
        app.post("/product/subscribe/{ProductType}", disty::subscribeToProductType);

        //create new listing
        //app.post("/product", disty::addNewProduct);
        app.post("/product", (ctx) -> {
            new AddProductRequest().doHandle(ctx);
        });

        //TODO: fetch all orders
        app.get("/order", (ctx) -> {

        });

        //TODO:Get a specific order maybe?

        //TODO: create new order
        app.post("/order", (ctx) -> {

        });

        //TODO: approve/decline order
        app.patch("/order", (ctx) -> {

        });

        //get messages from username
        app.get("/message", disty::getMessages);

        //get unsent messages from username
        app.get("/message/unsent", disty::checkForUnsentMessages);
    }
}
