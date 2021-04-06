package cn.graydove.httpmaster.core.engine.support.okhttp;

import okhttp3.OkHttpClient;

/**
 * @author graydove
 */
public interface OkHttpClientFactory {

    OkHttpClient newOkHttpClient();
}
