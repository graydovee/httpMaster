package cn.graydove.httpmaster.starter.handler;

/**
 * @author graydove
 */
public interface RequestHandlerRegister {

    void registerBeforeRequestHandler(BeforeRequestHandler beforeRequestHandler);

    void registerAfterRequestHandler(AfterRequestHandler afterRequestHandler);

    void registerRequestFailureHandler(RequestFailureHandler requestFailureHandler);
}
