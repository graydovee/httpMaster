package cn.graydove.httpmaster.core.engine.support.httpclient;

import cn.graydove.httpmaster.core.common.KVList;
import cn.graydove.httpmaster.core.response.HttpContent;
import cn.graydove.httpmaster.core.response.support.AbstractHttpResponse;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

public class HttpClientResponse extends AbstractHttpResponse {

    private final CloseableHttpResponse response;

    public HttpClientResponse(CloseableHttpResponse closeableHttpResponse) {
        this.response = closeableHttpResponse;
    }

    private KVList<String, String> createHeader() {
        KVList<String, String> KVList = new KVList<>();
        for (Header header : response.getAllHeaders()) {
            KVList.add(header.getName(), header.getValue());
        }
        return KVList;
    }

    @Override
    public int getStatusCode() {
        return response.getStatusLine().getStatusCode();
    }

    @Override
    public KVList<String, String> getHeader() {
        KVList<String, String> header = super.getHeader();
        if (null == header) {
            synchronized (response) {
                header = super.getHeader();
                if (null == header) {
                    header = createHeader();
                    super.setHead(header);
                }
            }
        }
        return header;
    }

    @Override
    public HttpContent getHttpContent() {
        HttpContent httpContent = super.getHttpContent();
        if (null == httpContent) {
            synchronized (response) {
                httpContent = super.getHttpContent();
                if (null == httpContent) {
                    httpContent = new HttpClientContent(response.getEntity());
                    super.setHttpContent(httpContent);
                }
            }
        }
        return httpContent;
    }


    @Override
    public void close() throws IOException {
        response.close();
    }
}
