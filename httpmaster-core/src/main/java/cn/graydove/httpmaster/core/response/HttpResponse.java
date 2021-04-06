package cn.graydove.httpmaster.core.response;

import cn.graydove.httpmaster.core.common.KVList;

import java.io.Closeable;

/**
 * @author graydove
 */
public interface HttpResponse extends Closeable {

    int getStatusCode();

    KVList<String, String> getHeader();

    HttpContent getHttpContent();
}
