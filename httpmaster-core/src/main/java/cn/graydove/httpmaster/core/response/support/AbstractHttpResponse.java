package cn.graydove.httpmaster.core.response.support;

import cn.graydove.httpmaster.core.common.KVList;
import cn.graydove.httpmaster.core.request.HttpHeader;
import cn.graydove.httpmaster.core.response.HttpContent;
import cn.graydove.httpmaster.core.response.HttpResponse;

public abstract class AbstractHttpResponse implements HttpResponse {
    private int statusCode;

    private KVList<String, String> header;

    private HttpContent httpContent;

    public int getStatusCode() {
        return statusCode;
    }

    public KVList<String, String> getHeader() {
        return header;
    }

    public HttpContent getHttpContent() {
        return httpContent;
    }

    protected void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    protected void setHead(KVList<String, String> header) {
        this.header = header;
    }

    protected void setHttpContent(HttpContent httpContent) {
        this.httpContent = httpContent;
    }
}
