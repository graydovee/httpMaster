package cn.graydove.httpmaster.core.engine.support.httpclient;

import cn.graydove.httpmaster.core.exception.HttpEngineInitException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @author graydove
 */
public class DefaultHttpClientFactory implements HttpClientFactory {
    @Override
    public CloseableHttpClient newClient() {
        return HttpClients.custom()
                // 设置连接池管理
                .setConnectionManager(newConnectionManager())
                // 设置请求配置
                .setDefaultRequestConfig(newRequestConfig())
                // 设置重试次数
                .setRetryHandler(new DefaultHttpRequestRetryHandler(3, true))
                // 设置重定向
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
    }

    private RequestConfig newRequestConfig() {

        // 根据默认超时限制初始化requestConfig
        int socketTimeout = 10000;
        int connectTimeout = 10000;
        int connectionRequestTimeout = 10000;

        return RequestConfig.custom()
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .build();
    }

    private PoolingHttpClientConnectionManager newConnectionManager() {

        // 连接管理器
        PoolingHttpClientConnectionManager pool;
        // 初始化HttpClientTest开始
        SSLContextBuilder builder = new SSLContextBuilder();

        try {
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        } catch (NoSuchAlgorithmException | KeyStoreException e) {
            e.printStackTrace();
        }
        SSLConnectionSocketFactory sslsf;
        try {
            sslsf = new SSLConnectionSocketFactory(
                    builder.build());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new HttpEngineInitException(e);
        }

        // 配置同时支持 HTTP 和 HTPPS
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register(
                "http", PlainConnectionSocketFactory.getSocketFactory()).register(
                "https", sslsf).build();
        // 初始化连接管理器
        pool = new PoolingHttpClientConnectionManager(
                socketFactoryRegistry);
        // 将最大连接数增加到200，实际项目最好从配置文件中读取这个值
        pool.setMaxTotal(200);
        // 设置最大路由
        pool.setDefaultMaxPerRoute(2);

        return pool;
    }
}
