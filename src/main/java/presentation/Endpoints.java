package presentation;

import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;

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
        app.post("/token", (ctx) -> {
            System.out.println(ctx.body());
            disty.login(ctx);
        });

        //search product
        app.get("/product", (ctx) -> {

        });

        //subscribe to product type
        app.post("/product/subscribe/{ProductType}", disty::subscribeToProductType);

        //create new listing
        app.post("/product", disty::addNewProduct);

        //fetch all orders
        app.get("/orders", (ctx) -> {

        });

        //Get a specific order maybe?

        //create new order
        app.post("/order", (ctx) -> {

        });

        //approve/decline order
        app.patch("/order", (ctx) -> {

        });

        //get messages from username
        app.get("/messages", disty::getMessages);

        //get unsent messages from username
        app.get("/messages/unsent", (ctx) -> {
            disty.checkForUnsentMessages(ctx);
        });
    }
}
