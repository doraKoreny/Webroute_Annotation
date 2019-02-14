package webserver;

import com.dora.koreny.annot.WebRoute;

public class Route {

    @WebRoute
    public String test1() {
        return "you are on test route";
    }

    @WebRoute(path = "/another")
    public String test2() {
        return "you are on another route";
    }
}
