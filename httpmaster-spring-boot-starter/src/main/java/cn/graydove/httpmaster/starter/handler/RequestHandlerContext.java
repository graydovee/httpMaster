package cn.graydove.httpmaster.starter.handler;

import java.util.Set;

public interface RequestHandlerContext {
    Set<BeforeRequestHandler> getBeforeRequestHandlerSet();

    Set<AfterRequestHandler> getAfterRequestHandlerSet();

    Set<RequestFailureHandler> getRequestFailureHandlerSet();
}
