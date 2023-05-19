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
        app.post("/product", (ctx) -> {
            new AddProductRequest().doHandle(ctx);
        });

        //fetch my orders // shows orders i've placed, AKA. order history
        app.get("/order", (ctx) -> {
            new OrderHistoryRequest().doHandle(ctx);
        });

        // TODO: /order/seller?         // show orders for all my products

        // /order/{product_id}     // shows orders for a specific listing
        app.get("/order/{product_id}", (ctx) -> {
            new PendingOrdersRequest().doHandle(ctx);
        });

        app.post("/order", (ctx) -> {
            new PlaceOrderRequest().doHandle(ctx);
        });

        app.patch("/order", (ctx) -> {
            new ProcessOrderRequest().doHandle(ctx);
        });

        //get messages from username
        app.get("/message", (ctx) -> {
            new GetMessagesRequest().doHandle(ctx);
        });

        //get unsent messages from username
        app.get("/message/unsent", (ctx) -> {
            new GetUnsentMessagesRequest().doHandle(ctx);
        });
    }
}
