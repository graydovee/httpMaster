package cn.graydove.httpmaster.core.request;

public interface KeyValueHttpBody extends HttpBody {

    KeyValueHttpBody addParam(Object key, Object value);

    @Override
    KeyValueHttpBody data(Object data);

}
