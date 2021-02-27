package cn.graydove.httpmaster.core.response.support;

import cn.graydove.httpmaster.core.common.Singleton;
import cn.graydove.httpmaster.core.response.HttpContent;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.function.Supplier;

public abstract class AbstractHttpContent implements HttpContent {

    private Singleton<byte[]> bytes = Singleton.of(this::createBytes);

    private Singleton<Long> length = Singleton.of(this::createLength);

    private Singleton<Charset> encodeType = Singleton.of(this::createEncodeType);

    private Singleton<InputStream> inputStream = Singleton.of(this::createInputStream);

    @Override
    public InputStream getContent() {
        return inputStream.get();
    }

    @Override
    public byte[] getContentBytes() {
        return bytes.get();
    }

    @Override
    public long getLength() {
        return length.get();
    }

    @Override
    public Charset getEncodeType() {
        return encodeType.get();
    }

    protected void setLength(Supplier<Long> length) {
        this.length = Singleton.of(length);
    }

    protected void setEncodeType(Supplier<Charset> encodeType) {
        this.encodeType = Singleton.of(encodeType);
    }

    protected void setBytes(Supplier<byte[]> bytes) {
        this.bytes = Singleton.of(bytes);
    }

    protected void setInputStream(Supplier<InputStream> inputStream) {
        this.inputStream = Singleton.of(inputStream);
    }

    protected abstract Long createLength();

    protected abstract Charset createEncodeType();

    protected abstract byte[] createBytes();

    protected abstract InputStream createInputStream();
}
