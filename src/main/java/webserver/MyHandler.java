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
    Map<MapKey, Method> mapRoutesMethods = new HashMap<MapKey, Method>();
    Class<Routes> routesClass = Routes.class;

    public void fillMapRoutes () {
        for (Method method : routesClass.getMethods()) {
            if (method.isAnnotationPresent(WebRoute.class)) {
                Annotation annotation = method.getAnnotation(WebRoute.class);
                String pathValue = ((WebRoute) annotation).path();
                String reqmethodValue = String.valueOf(((WebRoute) annotation).method());
                MapKey mapKey = new MapKey(pathValue, reqmethodValue);
                mapRoutesMethods.put(mapKey, method);
            }
        }
    }

    public void handle(HttpExchange t) throws IOException {
        String requestedPath = String.valueOf(t.getRequestURI());
        String requestMethod = t.getRequestMethod();
        System.out.println(requestMethod);
        System.out.println(requestedPath);
        String response ="";

        for (MapKey mapKey : mapRoutesMethods.keySet()) {
            if (mapKey.getPathKey().equals(requestedPath) && mapKey.getReqMethodKey().equals(requestMethod)) {
                Method method = mapRoutesMethods.get(mapKey);
                try {
                    response = (String) method.invoke(routesClass.newInstance());
                    break;
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
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