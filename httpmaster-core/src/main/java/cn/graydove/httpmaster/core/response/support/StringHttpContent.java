package cn.graydove.httpmaster.core.response.support;

import cn.graydove.httpmaster.core.common.Singleton;
import cn.graydove.httpmaster.core.response.HttpContent;

import java.io.InputStream;
import java.nio.charset.Charset;

public class StringHttpContent implements HttpContent {

    private HttpContent httpContent;

    private Singleton<String> strContent;

    public StringHttpContent(HttpContent httpContent) {
        this.httpContent = httpContent;
        this.strContent = new Singleton<>(this::createStrContent);
    }

    private String createStrContent() {
        Charset encodeType = getEncodeType();
        if (null == encodeType) {
            return new String(getContentBytes());
        }
        return new String(getContentBytes(), encodeType);
    }

    @Override
    public byte[] getContentBytes() {
        return httpContent.getContentBytes();
    }

    @Override
    public long getLength() {
        return httpContent.getLength();
    }

    @Override
    public Charset getEncodeType() {
        return httpContent.getEncodeType();
    }

    @Override
    public InputStream getContent() {
        return httpContent.getContent();
    }

    public String getContentStr() {
        return strContent.get();
    }

    public String getContentStr(Charset charset) {
        return new String(getContentBytes(), charset);
    }
}
