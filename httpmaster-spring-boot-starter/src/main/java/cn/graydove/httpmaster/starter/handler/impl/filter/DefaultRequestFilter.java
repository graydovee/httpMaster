package cn.graydove.httpmaster.starter.handler.impl.filter;

import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.graydove.httpmaster.starter.exception.RequestInterruptedException;

/**
 * @author graydove
 */
public class DefaultRequestFilter implements RequestFilter {

    @Override
    public void doFilter(HttpRequest httpRequest, RequestFilterChain requestFilterChain) throws RequestInterruptedException {

    }
}
