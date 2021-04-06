package cn.graydove.httpmaster.core.enums;

import cn.graydove.httpmaster.core.common.HeaderConstant;
import cn.graydove.httpmaster.core.exception.UnsupportedException;
import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.hutool.core.util.StrUtil;

import java.util.Optional;

/**
 * @author graydove
 */

public enum HttpMediaType {

    /**
     * 默认
     */
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

    public static Optional<HttpMediaType> toHttpMediaType(String contentType) {
        HttpMediaType httpMediaType = null;
        for (String s : StrUtil.split(contentType, ';')) {
            for (HttpMediaType value : values()) {
                if (StrUtil.equals(value.getValue(), s)) {
                    httpMediaType = value;
                    break;
                }
            }
        }
        return Optional.ofNullable(httpMediaType);
    }


    public static Optional<HttpMediaType> toHttpMediaType(HttpRequest httpRequest) {
        String contentType = httpRequest.header().asMap().get(HeaderConstant.CONTENT_TYPE);
        return toHttpMediaType(contentType);
    }
}
