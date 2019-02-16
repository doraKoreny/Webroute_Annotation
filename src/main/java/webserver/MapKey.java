package webserver;

public class MapKey {
    private String pathKey;
    private String reqMethodKey;

    public Object getPathKey() {
        return pathKey;
    }

    public void setPathKey(String pathKey) {
        this.pathKey = pathKey;
    }

    public Object getReqMethodKey() {
        return reqMethodKey;
    }

    public void setReqMethodKey(String reqMethodKey) {
        this.reqMethodKey = reqMethodKey;
    }
}
