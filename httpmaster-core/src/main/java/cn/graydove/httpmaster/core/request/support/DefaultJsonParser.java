package cn.graydove.httpmaster.core.request.support;

import cn.graydove.httpmaster.core.request.JsonParser;
import cn.hutool.json.JSONUtil;

import java.lang.reflect.Type;

/**
 * @author graydove
 */
public class DefaultJsonParser implements JsonParser {

    @Override
    public String toJsonStr(Object o) {
        return JSONUtil.toJsonStr(o);
    }

    @Override
    public <T> T toObject(String s, Type type) {
        return JSONUtil.toBean(s, type, true);
    }
}
