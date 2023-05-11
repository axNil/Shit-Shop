package presentation;

import application.Distributer;
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
        app.post("/user", (ctx) -> {
            String response = disty.registerNewUser(ctx.body());
            ctx.json(response);
        });

        //get access token
        app.post("/token", (ctx) -> {

        });

        //search product
        app.get("/product", (ctx) -> {

        });

        //create new listing
        app.post("/product", (ctx) -> {

        });

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

        //get messages from user id
        app.get("messages/{id}", (ctx) -> {

        });
    }
}
