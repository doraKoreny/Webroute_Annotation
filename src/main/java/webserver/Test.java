package webserver;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class Test {
    private static MyHandler myHandler = new MyHandler();

    public static void main(String[] args) throws Exception {
        myHandler.fillMapRoutes();
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        for (MapKey mapKey : myHandler.mapRoutesMethods.keySet()) {
            server.createContext((String) mapKey.getPathKey(), myHandler);
        }
        server.setExecutor(null); // creates a default executor
        server.start();
    }







}