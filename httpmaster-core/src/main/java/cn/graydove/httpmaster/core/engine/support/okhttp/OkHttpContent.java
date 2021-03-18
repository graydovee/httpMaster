package cn.graydove.httpmaster.core.engine.support.okhttp;

import cn.graydove.httpmaster.core.exception.HttpRequestException;
import cn.graydove.httpmaster.core.response.support.AbstractHttpContent;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class OkHttpContent extends AbstractHttpContent {

    private final ResponseBody responseBody;

    private static final Charset defaultCharSet = StandardCharsets.UTF_8;

    public OkHttpContent(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    protected Long createLength() {
        return responseBody.contentLength();
    }

    @Override
    protected Charset createEncodeType() {
        MediaType mediaType = responseBody.contentType();
        return mediaType != null ? mediaType.charset(defaultCharSet) : defaultCharSet;
    }

    @Override
    protected byte[] createBytes() {
        try {
            return responseBody.bytes();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    @Override
    protected InputStream createInputStream() {
        return responseBody.byteStream();
    }
}