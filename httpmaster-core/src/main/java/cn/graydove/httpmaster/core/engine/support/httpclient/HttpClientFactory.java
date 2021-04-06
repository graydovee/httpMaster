package cn.graydove.httpmaster.core.engine.support.httpclient;

import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author graydove
 */
public interface HttpClientFactory {

    CloseableHttpClient newClient();
}
