package webserver;

import com.dora.koreny.annot.WebRoute;

import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;
import static javax.swing.text.html.FormSubmitEvent.MethodType.POST;

public class Routes {

    @WebRoute
    public String test1() {
        return "you are on TEST route";
    }

    @WebRoute(path = "/another")
    public String test2() {
        return "you are on ANOTHER route";
    }

    @WebRoute(path = "/index")
    public String test3() {
        return "you are on INDEX route";
    }

    @WebRoute(method=POST, path = "/users")
    public String test4() {
        return "you sent a POST request to USERS route";
    }

    @WebRoute(path = "/users")
    public String test5() {
        return "you sent a GET request to USERS route";
    }
}
