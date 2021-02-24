package cn.graydove.httpmaster.core.response;

import java.nio.charset.Charset;

public interface HttpContent {

    byte[] getContentBytes();

    long getLength();

    Charset getEncodeType();
}
