package cn.graydove.httpmaster.core.response.support;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractStringHttpContent extends AbstractHttpContent {

    private Map<Charset, String> strCache = new ConcurrentHashMap<>();

    public String getContentStr() {
        return getContentStr(getEncodeType());
    }

    public String getContentStr(Charset charset) {
        if (null == charset) {
            charset = Charset.defaultCharset();
        }
        return strCache.computeIfAbsent(charset, this::createStrContent);
    }

    private String createStrContent(Charset charset) {
        return new String(getContentBytes(), charset);
    }
}
