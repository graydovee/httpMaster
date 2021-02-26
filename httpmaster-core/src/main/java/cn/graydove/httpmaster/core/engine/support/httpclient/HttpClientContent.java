package cn.graydove.httpmaster.core.engine.support.httpclient;

import cn.graydove.httpmaster.core.exception.HttpRequestException;
import cn.graydove.httpmaster.core.response.HttpContent;
import cn.graydove.httpmaster.core.response.support.AbstractHttpContent;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Optional;

public class HttpClientContent extends AbstractHttpContent {

    private final HttpEntity entity;

    public HttpClientContent(HttpEntity entity) {
        this.entity = entity;
    }

    @Override
    public byte[] getContentBytes() {
        byte[] contentBytes = super.getContentBytes();
        if (null == contentBytes) {
            synchronized (entity) {
                contentBytes = super.getContentBytes();
                if (null == contentBytes) {
                    try {
                        contentBytes = EntityUtils.toByteArray(entity);
                        setBytes(contentBytes);
                    } catch (IOException e) {
                        throw new HttpRequestException(e);
                    }
                }
            }
        }
        return contentBytes;
    }

    @Override
    public long getLength() {
        return entity.getContentLength();
    }

    @Override
    public Charset getEncodeType() {
        return Optional.ofNullable(entity.getContentEncoding())
                .map(NameValuePair::getValue)
                .map(Charset::forName)
                .orElse(null);
    }

    @Override
    public InputStream getContent() {
        try {
            return entity.getContent();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }
}
