package webserver;

public class MapKey {
    private String pathKey;
    private String reqMethodKey;

    public MapKey(String pathKey, String reqMethodKey) {
        this.pathKey = pathKey;
        this.reqMethodKey = reqMethodKey;
    }

    public Object getPathKey() {
        return pathKey;
    }


    public Object getReqMethodKey() {
        return reqMethodKey;
    }

}
