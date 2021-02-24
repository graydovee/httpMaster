package cn.graydove.httpmaster.core.request;

import java.util.Map;

public interface HttpBody extends HttpParam {

    HttpBody data(Object data);

    Object getData();

    Map<Object, Object> toQueryMap();
}
