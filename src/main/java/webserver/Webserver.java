package webserver;

import com.dora.koreny.annot.WebRoute;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsServer;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;

public class Webserver {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException, InstantiationException {
        HttpServer server = HttpServer.create(
                new InetSocketAddress(8000), 1);
                // second param is the socket backlog, this is the max num of
                //queued incoming connections to allow on the listening socket.
                //If it is <= 0, then a sys default value is used.

        Class<MyHandler> myHandlerClass = MyHandler.class;

//        server.createContext("/test", myHandlerClass.newInstance());



        for (Method method : myHandlerClass.getMethods()) {
            if (method.isAnnotationPresent(WebRoute.class)) {
                Annotation annotation = method.getAnnotation(WebRoute.class);
                WebRoute webRouteAnnot = (WebRoute) annotation;

                if (webRouteAnnot.path().equals("/index")) {
                    server.createContext("/index", myHandlerClass.newInstance() );
                }
                break;
            }
        }


        server.setExecutor(null); // null for default impl.
        server.start();
    }
}
