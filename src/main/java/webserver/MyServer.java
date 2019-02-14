package webserver;

import com.dora.koreny.annot.WebRoute;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class MyServer implements HttpHandler {

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "This is a response.";
        httpExchange.sendResponseHeaders(
                200, response.getBytes().length);
                //int responseCode, long responseLength (: specify a fixed response body length)
        OutputStream outputStream = httpExchange.getResponseBody();
                //returns a stream to which the response body must be written.
        outputStream.write(response.getBytes());
        outputStream.close();
    }


}
