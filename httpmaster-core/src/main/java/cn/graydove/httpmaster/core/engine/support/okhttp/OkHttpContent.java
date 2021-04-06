package cn.graydove.httpmaster.core.engine.support.okhttp;

import cn.graydove.httpmaster.core.exception.HttpRequestException;
import cn.graydove.httpmaster.core.response.support.AbstractStringHttpContent;
import okhttp3.MediaType;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author graydove
 */
public class OkHttpContent extends AbstractStringHttpContent {

    private final ResponseBody responseBody;

    private static final Charset DEFAULT_CHAR_SET = StandardCharsets.UTF_8;

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
        return mediaType != null ? mediaType.charset(DEFAULT_CHAR_SET) : DEFAULT_CHAR_SET;
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
