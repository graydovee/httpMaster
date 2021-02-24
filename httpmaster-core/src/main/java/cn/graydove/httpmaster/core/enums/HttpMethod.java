package cn.graydove.httpmaster.core.enums;

import java.util.HashMap;
import java.util.Map;

public enum HttpMethod {
    GET, HEAD, POST(true), PUT(true), PATCH(true), DELETE, OPTIONS, TRACE;


    private static final Map<String, HttpMethod> mappings = new HashMap<>(16);

    private boolean hasBody;

    HttpMethod() {
        this(false);
    }

    HttpMethod(boolean hasBody) {
        this.hasBody = hasBody;
    }

    static {
        for (HttpMethod httpMethod : values()) {
            mappings.put(httpMethod.name(), httpMethod);
        }
    }


    public static HttpMethod resolve( String method) {
        return (method != null ? mappings.get(method) : null);
    }


    public boolean matches(String method) {
        return (this == resolve(method));
    }

    public boolean isHasBody() {
        return hasBody;
    }
}
