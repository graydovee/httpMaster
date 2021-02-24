package cn.graydove.httpmaster.core.enums;

import cn.graydove.httpmaster.core.common.HeaderConstant;
import cn.graydove.httpmaster.core.exception.UnsupportedException;
import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.hutool.core.util.StrUtil;

public enum HttpMediaType {

    URL_ENCODED("application/x-www-form-urlencoded"),

    MULTIPART("multipart/form-data"),

    JSON("application/json"),

    XML("text/xml");

    private String value;

    HttpMediaType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static HttpMediaType toHttpMediaType(String contentType) {
        for (String s : StrUtil.split(contentType, ';')) {
            for (HttpMediaType value : values()) {
                if (StrUtil.equals(value.getValue(), s)) {
                    return value;
                }
            }
        }
        throw new UnsupportedException("unsupported mediaType: " + contentType);
    }


    public static HttpMediaType toHttpMediaType(HttpRequest httpRequest) {
        String contentType = httpRequest.header().asMap().get(HeaderConstant.CONTENT_TYPE);
        return toHttpMediaType(contentType);
    }
}
