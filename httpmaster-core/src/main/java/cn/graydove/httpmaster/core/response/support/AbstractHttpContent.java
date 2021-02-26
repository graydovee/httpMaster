package cn.graydove.httpmaster.core.response.support;

import cn.graydove.httpmaster.core.response.HttpContent;

import java.nio.charset.Charset;

public abstract class AbstractHttpContent implements HttpContent {

    private byte[] bytes;

    private long length;

    private Charset encodeType;


    @Override
    public byte[] getContentBytes() {
        return bytes;
    }

    @Override
    public long getLength() {
        return length;
    }

    @Override
    public Charset getEncodeType() {
        return encodeType;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public void setEncodeType(Charset encodeType) {
        this.encodeType = encodeType;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
