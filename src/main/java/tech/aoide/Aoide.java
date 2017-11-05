package tech.aoide;

import tech.aoide.webserver.WebServer;

public class Aoide {

    public static void main(String[] args) {
        if (System.getenv("PORT") != null) {
            WebServer.port(Integer.parseInt(System.getenv("PORT")));
        }
        WebServer.allowOrigin("https://aoide-frontend.herokuapp.com");
        WebServer.allowOrigin("https://aoide-frontend-dev.herokuapp.com");
        WebServer.allowOrigin("https://aoide.tech");
        WebServer.allowOrigin("https://codeaoide.com");
        WebServer.allowOrigin("http://10.131.182.35");
        WebServer.enableCORS("POST, GET, OPTIONS", "*");
        WebServer.setupRoutes();
    }

}
