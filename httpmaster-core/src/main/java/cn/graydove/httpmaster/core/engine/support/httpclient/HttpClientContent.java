package cn.graydove.httpmaster.core.engine.support.httpclient;

import cn.graydove.httpmaster.core.exception.HttpRequestException;
import cn.graydove.httpmaster.core.response.support.AbstractStringHttpContent;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Optional;

public class HttpClientContent extends AbstractStringHttpContent {

    private final HttpEntity entity;

    public HttpClientContent(HttpEntity entity) {
        this.entity = entity;
    }

    @Override
    protected Long createLength() {
        return entity.getContentLength();
    }

    @Override
    protected Charset createEncodeType() {
        return Optional.ofNullable(entity.getContentEncoding())
                .map(NameValuePair::getValue)
                .map(Charset::forName)
                .orElse(null);
    }

    @Override
    protected byte[] createBytes() {
        try {
            return EntityUtils.toByteArray(entity);
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    @Override
    protected InputStream createInputStream() {
        try {
            return entity.getContent();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }
}
