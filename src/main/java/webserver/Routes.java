package webserver;

import com.dora.koreny.annot.WebRoute;

import static javax.swing.text.html.FormSubmitEvent.MethodType.POST;

public class Routes {

    @WebRoute
    public String test1() {
        return "you are on test route";
    }

    @WebRoute(path = "/another")
    public String test2() {
        return "you are on another route";
    }

    @WebRoute(path = "/index")
    public String test3() {
        return "you are on INDEX route";
    }

    @WebRoute(path = "/users")
    public String test4() {
        return "you are on USERS route";
    }
}
