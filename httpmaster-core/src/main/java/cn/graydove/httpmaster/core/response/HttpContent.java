package cn.graydove.httpmaster.core.response;

import java.io.InputStream;
import java.nio.charset.Charset;

public interface HttpContent {

    byte[] getContentBytes();

    long getLength();

    Charset getEncodeType();

    InputStream getContent();
}
