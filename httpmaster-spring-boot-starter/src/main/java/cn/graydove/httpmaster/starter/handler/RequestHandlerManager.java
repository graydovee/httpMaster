package cn.graydove.httpmaster.starter.handler;

import java.util.HashSet;
import java.util.Set;

public class RequestHandlerManager implements RequestHandlerContext, RequestHandlerRegister {

    private Set<BeforeRequestHandler> beforeRequestHandlerSet = new HashSet<>();

    private Set<AfterRequestHandler> afterRequestHandlerSet = new HashSet<>();

    private Set<RequestFailureHandler> requestFailureHandlerSet = new HashSet<>();

    @Override
    public void registerBeforeRequestHandler(BeforeRequestHandler beforeRequestHandler) {
        beforeRequestHandlerSet.add(beforeRequestHandler);
    }

    @Override
    public void registerAfterRequestHandler(AfterRequestHandler afterRequestHandler) {
        afterRequestHandlerSet.add(afterRequestHandler);
    }

    @Override
    public void registerRequestFailureHandler(RequestFailureHandler requestFailureHandler) {
        requestFailureHandlerSet.add(requestFailureHandler);
    }

    @Override
    public Set<BeforeRequestHandler> getBeforeRequestHandlerSet() {
        return beforeRequestHandlerSet;
    }

    @Override
    public Set<AfterRequestHandler> getAfterRequestHandlerSet() {
        return afterRequestHandlerSet;
    }

    @Override
    public Set<RequestFailureHandler> getRequestFailureHandlerSet() {
        return requestFailureHandlerSet;
    }
}
