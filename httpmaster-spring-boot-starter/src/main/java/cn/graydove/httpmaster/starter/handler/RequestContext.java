package cn.graydove.httpmaster.starter.handler;

import cn.graydove.httpmaster.core.engine.HttpEngine;

import java.util.List;

/**
 * @author graydove
 */
public interface RequestContext {

    HttpEngine getHttpEngine();

    List<BeforeRequestHandler> getBeforeRequestHandlerList();

    List<AfterRequestHandler> getAfterRequestHandlerList();

    List<RequestFailureHandler> getRequestFailureHandlerList();
}
