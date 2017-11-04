package tech.aoide.webserver;

import spark.Spark;
import tech.aoide.webserver.api.ProcessAPI;

public class WebServer {

    public static void setupRoutes() {
        Spark.post("/process", ProcessAPI.processString);
    }

}
