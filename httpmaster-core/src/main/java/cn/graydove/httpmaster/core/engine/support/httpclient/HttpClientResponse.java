package cn.graydove.httpmaster.core.engine.support.httpclient;

import cn.graydove.httpmaster.core.common.KVList;
import cn.graydove.httpmaster.core.response.HttpContent;
import cn.graydove.httpmaster.core.response.support.AbstractHttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

/**
 * @author graydove
 */
public class HttpClientResponse extends AbstractHttpResponse {

    private final CloseableHttpResponse response;

    public HttpClientResponse(CloseableHttpResponse closeableHttpResponse) {
        this.response = closeableHttpResponse;
    }

    @Override
    protected KVList<String, String> createHeader() {
        return KVList.toKVList(
                response.getAllHeaders(),
                NameValuePair::getName,
                NameValuePair::getValue
        );
    }

    @Override
    protected HttpContent createHttpContent() {
        return new HttpClientContent(response.getEntity());
    }


    @Override
    protected Integer createStatus() {
        return response.getStatusLine().getStatusCode();
    }


    @Override
    public void close() throws IOException {
        response.close();
    }
}
