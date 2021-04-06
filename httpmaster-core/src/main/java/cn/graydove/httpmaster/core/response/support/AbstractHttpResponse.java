package cn.graydove.httpmaster.core.response.support;

import cn.graydove.httpmaster.core.common.KVList;
import cn.graydove.httpmaster.core.common.Singleton;
import cn.graydove.httpmaster.core.response.HttpContent;
import cn.graydove.httpmaster.core.response.HttpResponse;

import java.util.function.Supplier;

/**
 * @author graydove
 */
public abstract class AbstractHttpResponse implements HttpResponse {

    private Singleton<Integer> statusCode = Singleton.of(this::createStatus);

    private Singleton<KVList<String, String>> header = Singleton.of(this::createHeader);

    private Singleton<HttpContent> httpContent = Singleton.of(this::createHttpContent);

    @Override
    public int getStatusCode() {
        return statusCode.get();
    }

    @Override
    public KVList<String, String> getHeader() {
        return header.get();
    }

    @Override
    public HttpContent getHttpContent() {
        return httpContent.get();
    }

    protected void setStatusCode(Supplier<Integer> statusCode) {
        this.statusCode = Singleton.of(statusCode);
    }

    protected void setHeader(Supplier<KVList<String, String>> header) {
        this.header = Singleton.of(header);
    }

    protected void setHttpContent(Supplier<HttpContent> httpContent) {
        this.httpContent = Singleton.of(httpContent);
    }

    protected abstract Integer createStatus();

    protected abstract KVList<String, String> createHeader();

    protected abstract HttpContent createHttpContent();
}
