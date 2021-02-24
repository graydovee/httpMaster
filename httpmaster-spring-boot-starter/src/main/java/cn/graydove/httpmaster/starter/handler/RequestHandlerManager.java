package cn.graydove.httpmaster.starter.handler;

import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.ArrayList;
import java.util.List;

public class RequestHandlerManager implements RequestHandlerContext, RequestHandlerRegister {

    private final List<BeforeRequestHandler> beforeRequestHandlerList = new ArrayList<>();

    private final List<AfterRequestHandler> afterRequestHandlerList = new ArrayList<>();

    private final List<RequestFailureHandler> requestFailureHandlerList = new ArrayList<>();

    private boolean beforeRequestHandlerListNotSorted = true;

    private boolean afterRequestHandlerListNotSorted = true;

    private boolean requestFailureHandlerListNotSorted = true;

    @Override
    public void registerBeforeRequestHandler(BeforeRequestHandler beforeRequestHandler) {
        beforeRequestHandlerList.add(beforeRequestHandler);
        beforeRequestHandlerListNotSorted = true;
    }

    @Override
    public void registerAfterRequestHandler(AfterRequestHandler afterRequestHandler) {
        afterRequestHandlerList.add(afterRequestHandler);
        afterRequestHandlerListNotSorted = true;
    }

    @Override
    public void registerRequestFailureHandler(RequestFailureHandler requestFailureHandler) {
        requestFailureHandlerList.add(requestFailureHandler);
        requestFailureHandlerListNotSorted = true;
    }

    @Override
    public List<BeforeRequestHandler> getBeforeRequestHandlerList() {
        if (beforeRequestHandlerListNotSorted) {
            synchronized (beforeRequestHandlerList) {
                if (beforeRequestHandlerListNotSorted) {
                    beforeRequestHandlerList.sort(AnnotationAwareOrderComparator.INSTANCE);
                    beforeRequestHandlerListNotSorted = false;
                }
            }
        }
        return beforeRequestHandlerList;
    }

    @Override
    public List<AfterRequestHandler> getAfterRequestHandlerList() {
        if (afterRequestHandlerListNotSorted) {
            synchronized (afterRequestHandlerList) {
                if (afterRequestHandlerListNotSorted) {
                    afterRequestHandlerList.sort(AnnotationAwareOrderComparator.INSTANCE);
                    afterRequestHandlerListNotSorted = false;
                }
            }
        }
        return afterRequestHandlerList;
    }

    @Override
    public List<RequestFailureHandler> getRequestFailureHandlerList() {
        if (requestFailureHandlerListNotSorted) {
            synchronized (requestFailureHandlerList) {
                if (requestFailureHandlerListNotSorted) {
                    requestFailureHandlerList.sort(AnnotationAwareOrderComparator.INSTANCE);
                    requestFailureHandlerListNotSorted = false;
                }
            }
        }
        return requestFailureHandlerList;
    }
}
