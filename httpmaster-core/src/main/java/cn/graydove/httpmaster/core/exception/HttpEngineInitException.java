package cn.graydove.httpmaster.core.exception;

public class HttpEngineInitException extends HttpException {

    public HttpEngineInitException() {
        this("engine init fail");

    }
    public HttpEngineInitException(Throwable cause) {
        this("engine init fail", cause);
    }

    public HttpEngineInitException(String message) {
        super(message);
    }

    public HttpEngineInitException(String message, Throwable cause) {
        super(message, cause);
    }
}
