package cn.graydove.httpmaster.starter.handler.impl;

import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.graydove.httpmaster.starter.bean.OrderBeanRegister;
import cn.graydove.httpmaster.starter.exception.RequestInterruptedException;
import cn.graydove.httpmaster.starter.handler.BeforeRequestHandler;
import cn.graydove.httpmaster.starter.handler.impl.filter.RequestFilter;
import cn.graydove.httpmaster.starter.handler.impl.filter.RequestFilterChain;

import java.lang.reflect.Method;

/**
 * @author graydove
 */
public class RequestFilterBeforeHandler implements BeforeRequestHandler {

    private final OrderBeanRegister<RequestFilter> requestFilterRegister;

    public RequestFilterBeforeHandler(OrderBeanRegister<RequestFilter> requestFilterRegister) {
        this.requestFilterRegister = requestFilterRegister;
    }

    @Override
    public boolean handle(HttpRequest httpRequest, Method method, Object[] args) {
        RequestFilterChain requestFilterChain = new RequestFilterChain(requestFilterRegister.getData());
        try {
            requestFilterChain.doFilter(httpRequest);
        } catch (RequestInterruptedException e) {
            return false;
        }
        return true;
    }
}
