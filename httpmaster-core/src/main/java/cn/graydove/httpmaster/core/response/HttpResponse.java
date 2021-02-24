package cn.graydove.httpmaster.core.response;

import cn.hutool.core.lang.Pair;

import java.util.List;

public interface HttpResponse {

    int getStatusCode();

    List<Pair<String, String>> getHeads();

    HttpContent getHttpContent();
}
