package cn.graydove.httpmaster.starter.handler;

public interface RequestHandlerRegister {

    void registerBeforeRequestHandler(BeforeRequestHandler beforeRequestHandler);

    void registerAfterRequestHandler(AfterRequestHandler afterRequestHandler);

    void registerRequestFailureHandler(RequestFailureHandler requestFailureHandler);
}
