package cn.graydove.httpmaster.starter.handler;

import cn.graydove.httpmaster.core.engine.HttpEngine;

/**
 * @author graydove
 */
public interface RequestContextRegister {

    void setHttpEngine(HttpEngine httpEngine);

    void registerBeforeRequestHandler(BeforeRequestHandler beforeRequestHandler);

    void registerAfterRequestHandler(AfterRequestHandler afterRequestHandler);

    void registerRequestFailureHandler(RequestFailureHandler requestFailureHandler);
}
