package cn.graydove.httpmaster.core.engine.support.okhttp;

import okhttp3.OkHttpClient;

public class DefaultOkHttpClientFactory implements OkHttpClientFactory {

    @Override
    public OkHttpClient newOkHttpClient() {
        return new OkHttpClient();
    }
}
