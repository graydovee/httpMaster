package cn.graydove.httpmaster.starter.handler;

import java.util.List;

public interface RequestHandlerContext {
    List<BeforeRequestHandler> getBeforeRequestHandlerList();

    List<AfterRequestHandler> getAfterRequestHandlerList();

    List<RequestFailureHandler> getRequestFailureHandlerList();
}
