package tech.aoide;

import tech.aoide.webserver.WebServer;

public class Aoide {

    public static void main(String[] args) {
        if (System.getenv("PORT") != null) {
            WebServer.port(Integer.parseInt(System.getenv("PORT")));
        }
        WebServer.allowHost("aoide-frontend.herokuapp.com");
        WebServer.allowHost("aoide-frontend-dev.herokuapp.com");
        WebServer.allowHost("aoide.tech");
        WebServer.allowHost("codeaoide.com");
        WebServer.allowHost("aoide.io");
        WebServer.enableCORS("POST, GET, OPTIONS", "*");
        WebServer.setupRoutes();
    }

}
