package cn.graydove.httpmaster.starter.handler;

import cn.graydove.httpmaster.starter.bean.OrderBeanRegister;

import java.util.List;

/**
 * @author graydove
 */
public class RequestHandlerManager implements RequestHandlerContext, RequestHandlerRegister {

    private final OrderBeanRegister<BeforeRequestHandler> beforeRequestHandlerRegister;

    private final OrderBeanRegister<AfterRequestHandler> afterRequestHandlerRegister;

    private final OrderBeanRegister<RequestFailureHandler> requestFailureHandlerRegister;

    public RequestHandlerManager(List<BeforeRequestHandler> beforeRequestHandlerList, List<AfterRequestHandler> afterRequestHandlerList, List<RequestFailureHandler> requestFailureHandlerList) {
        this.beforeRequestHandlerRegister = new OrderBeanRegister<>(beforeRequestHandlerList);
        this.afterRequestHandlerRegister = new OrderBeanRegister<>(afterRequestHandlerList);
        this.requestFailureHandlerRegister = new OrderBeanRegister<>(requestFailureHandlerList);
    }

    @Override
    public List<BeforeRequestHandler> getBeforeRequestHandlerList() {
        return beforeRequestHandlerRegister.getData();
    }

    @Override
    public List<AfterRequestHandler> getAfterRequestHandlerList() {
        return afterRequestHandlerRegister.getData();
    }

    @Override
    public List<RequestFailureHandler> getRequestFailureHandlerList() {
        return requestFailureHandlerRegister.getData();
    }

    @Override
    public void registerBeforeRequestHandler(BeforeRequestHandler beforeRequestHandler) {
        beforeRequestHandlerRegister.register(beforeRequestHandler);
    }

    @Override
    public void registerAfterRequestHandler(AfterRequestHandler afterRequestHandler) {
        afterRequestHandlerRegister.register(afterRequestHandler);
    }

    @Override
    public void registerRequestFailureHandler(RequestFailureHandler requestFailureHandler) {
        requestFailureHandlerRegister.register(requestFailureHandler);
    }
}
