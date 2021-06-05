package cn.graydove.httpmaster.core.request;

import java.lang.reflect.Type;

/**
 * @author graydove
 */
public interface JsonParser {

    String toJsonStr(Object o);

    <T> T toObject(String s, Type type);
}
