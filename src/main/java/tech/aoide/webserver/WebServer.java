package tech.aoide.webserver;

import spark.Spark;
import tech.aoide.webserver.api.ProcessAPI;

public class WebServer {

    public static void port(int port) {
        Spark.port(port);
    }

    public static void setupRoutes() {
        Spark.get("/", (req, res) -> {
            res.header("Content-Type", "text/html; charset=utf-8");
            return "\uD83C\uDF11 Aoide API server";
        });
        Spark.post("/process", ProcessAPI.processString);
    }

    public static void enableCORS(final String origin, final String methods, final String headers) {
        Spark.options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        Spark.before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            response.type("application/json");
        });
    }

}
