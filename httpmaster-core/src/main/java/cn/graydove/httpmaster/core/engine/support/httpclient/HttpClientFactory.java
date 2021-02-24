package cn.graydove.httpmaster.core.engine.support.httpclient;

import org.apache.http.impl.client.CloseableHttpClient;

public interface HttpClientFactory {

    CloseableHttpClient newClient();
}
