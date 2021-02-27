package cn.graydove.httpmaster.core.engine.support.okhttp;

import okhttp3.OkHttpClient;

public interface OkHttpClientFactory {

    OkHttpClient newOkHttpClient();
}
