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
        app.post("/product/search", (ctx) -> {
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

        //get the product types
        app.get("/product/types", (ctx -> {
            new GetProductTypesRequest().doHandle(ctx);
        }));

        //fetch my orders // shows orders i've placed, ALL orders approved+pending
        app.get("/order", (ctx) -> {
            new OrderHistoryRequest().doHandle(ctx);
        });

        //fetch all orders that has been approved AKA. order history
        app.post("/order/approved", (ctx) -> {
            new ApprovedOrdersRequest().doHandle(ctx);
        });

        //fetch orders for all my products (pending, approved)
        app.get("/order/seller", (ctx) -> {
            new SaleHistoryRequest().doHandle(ctx);
        });

        //fetch orders for a specific listing
        app.get("/order/{product_id}", (ctx) -> {
            new PendingOrdersRequest().doHandle(ctx);
        });

        //place new order
        app.post("/order", (ctx) -> {
            new PlaceOrderRequest().doHandle(ctx);
        });

        //decline/approve order
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

        //get the condition types
        app.get("product/condition", (ctx) -> {
           new GetConditionsRequest().doHandle(ctx);
        });

        //get the color types
        app.get("product/color", (ctx) -> {
            new GetColorRequest().doHandle(ctx);
        });
    }
}