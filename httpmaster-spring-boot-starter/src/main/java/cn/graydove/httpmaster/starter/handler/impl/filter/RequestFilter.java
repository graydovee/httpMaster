package cn.graydove.httpmaster.starter.handler.impl.filter;

import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.graydove.httpmaster.starter.exception.RequestInterruptedException;


public interface RequestFilter {

    void doFilter(HttpRequest httpRequest, RequestFilterChain requestFilterChain) throws RequestInterruptedException;
}
