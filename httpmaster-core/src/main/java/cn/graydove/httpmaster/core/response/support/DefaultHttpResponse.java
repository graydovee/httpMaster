package cn.graydove.httpmaster.core.response.support;

import cn.graydove.httpmaster.core.response.HttpContent;
import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.hutool.core.lang.Pair;

import java.util.List;

public class DefaultHttpResponse implements HttpResponse {
    private int statusCode;

    private List<Pair<String, String>> head;

    private HttpContent httpContent;

    public int getStatusCode() {
        return statusCode;
    }

    public List<Pair<String, String>> getHeads() {
        return head;
    }

    public HttpContent getHttpContent() {
        return httpContent;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setHead(List<Pair<String, String>> head) {
        this.head = head;
    }

    public void setHttpContent(HttpContent httpContent) {
        this.httpContent = httpContent;
    }
}
