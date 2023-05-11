import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;

public class RestLayer {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.plugins.enableCors(cors -> {
                cors.add(CorsPluginConfig::anyHost);
            });
        }).start(5008);
    }
}
