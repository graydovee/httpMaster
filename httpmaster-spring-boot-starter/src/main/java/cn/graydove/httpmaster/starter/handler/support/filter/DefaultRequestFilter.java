package cn.graydove.httpmaster.starter.handler.support.filter;

import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.graydove.httpmaster.starter.exception.RequestInterruptedException;

public class DefaultRequestFilter implements RequestFilter {

    @Override
    public void doFilter(HttpRequest httpRequest, RequestFilterChain requestFilterChain) throws RequestInterruptedException {

    }
}
