package webserver;

import com.dora.koreny.annot.WebRoute;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MyHandler implements HttpHandler {
    Map<String, Method> mapRoutes = new HashMap<String, Method>();
    Class<Routes> routesClass = Routes.class;

    public void fillMapRoutes () {
        for (Method method : routesClass.getMethods()) {
            if (method.isAnnotationPresent(WebRoute.class)) {
                Annotation annotation = method.getAnnotation(WebRoute.class);
                String annotValue = ((WebRoute) annotation).path();
                mapRoutes.put(annotValue, method);
            }
        }
    }

    public void handle(HttpExchange t) throws IOException {
        String requestedPath = String.valueOf(t.getRequestURI());
        System.out.println(requestedPath);
        String response ="";

        for (String annotValue : mapRoutes.keySet()) {
            if (annotValue.equals(requestedPath)) {
                Method method = mapRoutes.get(annotValue);
                try {
                    response = (String) method.invoke(routesClass.newInstance());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }

        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}