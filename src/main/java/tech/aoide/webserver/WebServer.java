package tech.aoide.webserver;

import spark.Spark;
import tech.aoide.webserver.api.ProcessAPI;

import java.util.ArrayList;

public class WebServer {

    private static ArrayList<String> allowedHosts = new ArrayList<>();

    public static void port(int port) {
        Spark.port(port);
    }

    public static void setupRoutes() {
        Spark.get("/", (req, res) -> {
            res.header("Content-Type", "text/html; charset=utf-8");
            return "\uD83C\uDF11 Aoide API server";
        });
        Spark.post("/process", ProcessAPI.processJava);
    }

    public static void enableCORS(final String methods, final String headers) {
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
            if (allowedHosts.contains(request.host())) {
                response.header("Access-Control-Allow-Origin", request.headers("Origin"));
            }
            else {
                response.header("Access-Control-Allow-Origin", "null");
            }
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            response.type("application/json");
        });
    }

    public static void allowHost(String origin) {
        allowedHosts.add(origin);
    }

}
