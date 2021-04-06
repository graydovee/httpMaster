package cn.graydove.httpmaster.core.exception;

/**
 * @author graydove
 */
public class HttpRequestException extends HttpException {
    public HttpRequestException() {
    }

    public HttpRequestException(String message) {
        super(message);
    }

    public HttpRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpRequestException(Throwable cause) {
        super(cause);
    }

    public HttpRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
