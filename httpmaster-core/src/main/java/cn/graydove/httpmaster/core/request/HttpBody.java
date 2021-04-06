package cn.graydove.httpmaster.core.request;

import java.util.Map;

/**
 * @author graydove
 */
public interface HttpBody extends HttpParam {

    HttpBody data(Object data);

    Object getData();

    Map<Object, Object> toQueryMap();
}
