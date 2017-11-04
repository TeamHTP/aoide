package tech.aoide.webserver;

import spark.Spark;
import tech.aoide.webserver.api.ProcessAPI;

public class WebServer {

    public static void port(int port) {
        Spark.port(port);
    }

    public static void setupRoutes() {
        Spark.get("/", (req, res) -> "Aoide API server");
        Spark.post("/process", ProcessAPI.processString);
    }

}
