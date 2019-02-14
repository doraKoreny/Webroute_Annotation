package webserver;

import com.dora.koreny.annot.WebRoute;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class RoutingController {

    @WebRoute(path = "/index")
    void onIndex(HttpExchange requestData) throws IOException {
        String response = "Index page is available.";
        requestData.sendResponseHeaders(200, response.getBytes().length);
        OutputStream stream = requestData.getResponseBody();
        stream.write(response.getBytes());
        stream.close();
    }

    @WebRoute(path = "/order")
    void onTest(HttpExchange requestData) throws IOException {
        String response = "Order page is available.";
        requestData.sendResponseHeaders(200, response.getBytes().length);
        OutputStream stream = requestData.getResponseBody();
        stream.write(response.getBytes());
        stream.close();
    }
}
