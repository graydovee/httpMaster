package cn.graydove.httpmaster.core.response.support;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author graydove
 */
public abstract class AbstractStringHttpContent extends AbstractHttpContent {

    private Map<Charset, String> strCache = new ConcurrentHashMap<>();

    @Override
    public String getContentStr() {
        return getContentStr(getEncodeType());
    }

    @Override
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
