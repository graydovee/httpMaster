package cn.graydove.httpmaster.core.engine.support.okhttp;

import cn.graydove.httpmaster.core.common.KVList;
import cn.graydove.httpmaster.core.response.HttpContent;
import cn.graydove.httpmaster.core.response.support.AbstractHttpResponse;
import cn.hutool.core.util.StrUtil;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpResponse extends AbstractHttpResponse {

    private final Response response;

    public OkHttpResponse(Response response) {
        this.response = response;
    }

    @Override
    public void close() throws IOException {
        response.close();
    }

    @Override
    protected Integer createStatus() {
        return response.code();
    }

    @Override
    protected KVList<String, String> createHeader() {
        return KVList.toKVList(
                response.headers(),
                head -> StrUtil.toString(head.getFirst()),
                head -> StrUtil.toString(head.getSecond())
        );
    }

    @Override
    protected HttpContent createHttpContent() {
        return new OkHttpContent(response.body());
    }
}
