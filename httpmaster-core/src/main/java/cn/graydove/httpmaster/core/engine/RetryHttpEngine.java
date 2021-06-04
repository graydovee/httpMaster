package cn.graydove.httpmaster.core.engine;

import cn.graydove.httpmaster.core.enums.HttpMethod;
import cn.graydove.httpmaster.core.exception.HttpRequestException;
import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.hutool.http.HttpStatus;

/**
 * @author graydove
 */
public class RetryHttpEngine extends AbstractHttpEngine {

    private HttpEngine httpEngine;

    /**
     * 重试
     */
    private int retryTimes;

    /**
     * 重试间隔 单位：毫秒
     */
    private int retryInterval;

    public RetryHttpEngine(HttpEngine httpEngine) {
        this(httpEngine, 3, 1000);
    }

    public RetryHttpEngine(HttpEngine httpEngine, int retryTimes, int retryInterval) {
        this.httpEngine = httpEngine;
        this.retryTimes = retryTimes;
        this.retryInterval = retryInterval;
    }

    @Override
    public HttpResponse execute(HttpMethod httpMethod, HttpRequest httpRequest) {
        RuntimeException exception = null;
        HttpResponse response = null;
        int c = 1;
        while (c <= retryTimes) {
            try {
                response = httpEngine.execute(httpMethod, httpRequest);
                int statusCode = response.getStatusCode();
                if (statusCode == HttpStatus.HTTP_OK) {
                    return response;
                }
                throw new HttpRequestException("response status code is " + statusCode);
            } catch (RuntimeException e) {
                exception = e;
            }
            if (++c <= retryTimes) {
                try {
                    Thread.sleep(retryInterval);
                } catch (InterruptedException ignored) {
                }
            }
        }
        if (response != null) {
            return response;
        }
        throw exception == null ? new HttpRequestException("no HttpResponse") : exception;
    }
}
