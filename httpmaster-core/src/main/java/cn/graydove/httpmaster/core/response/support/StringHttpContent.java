package cn.graydove.httpmaster.core.response.support;

import cn.graydove.httpmaster.core.response.HttpContent;

import java.io.InputStream;
import java.nio.charset.Charset;

public class StringHttpContent implements HttpContent {

    private HttpContent httpContent;

    public StringHttpContent(HttpContent httpContent) {
        this.httpContent = httpContent;
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
        Charset encodeType = getEncodeType();
        if (null == encodeType) {
            return new String(getContentBytes());
        }
        return new String(getContentBytes(), encodeType);
    }

    public String getContentStr(Charset charset) {
        return new String(getContentBytes(), charset);
    }
}
