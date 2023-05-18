package presentation;

import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;
import request.*;

public class Endpoints {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.plugins.enableCors(cors -> {
                cors.add(CorsPluginConfig::anyHost);
            });
        }).start(5008);

        //register new user
        app.post("/user", (ctx) -> {
            new RegisterUserRequest().doHandle(ctx);
        });

        //get access token
        app.post("/token", (ctx) -> {
            new AuthTokenRequest().doHandle(ctx);
        });

        // search products
        app.get("/product", (ctx) -> {
            new SearchRequest().doHandle(ctx);
        });

        //subscribe to product type
        app.post("/product/subscribe/{ProductType}", (ctx) -> {
            new SubscribeToProductRequest().doHandle(ctx);
        });

        //create new listing
        //app.post("/product", disty::addNewProduct);
        app.post("/product", (ctx) -> {
            new AddProductRequest().doHandle(ctx);
        });

        //get the product types
        app.get("/product/types", (ctx -> {
            new GetProductTypesRequest().doHandle(ctx);
        }));

        //TODO: fetch all orders
        app.get("/order", (ctx) -> {

        });

        //TODO:Get a specific order maybe?

        //TODO: create new order
        app.post("/order", (ctx) -> {
            new PlaceOrderRequest().doHandle(ctx);
        });

        //TODO: approve/decline order
        app.patch("/order", (ctx) -> {

        });

        //get messages from username
        app.get("/message", (ctx) -> {
            new GetMessagesRequest().doHandle(ctx);
        });

        //get unsent messages from username
        app.get("/message/unsent", (ctx) -> {
            new GetUnsentMessagesRequest().doHandle(ctx);
        });

        //get the condition types
        app.get("product/condition", (ctx) -> {
           new GetConditionsRequest().doHandle(ctx);
        });
    }
}