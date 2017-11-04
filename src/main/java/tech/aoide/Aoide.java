package tech.aoide;

import tech.aoide.webserver.WebServer;

public class Aoide {

    public static void main(String[] args) {
        if (System.getenv("PORT") != null) {
            WebServer.port(Integer.parseInt(System.getenv("PORT")));
        }
        WebServer.enableCORS("*", "*", "*");
        WebServer.setupRoutes();
    }

}
