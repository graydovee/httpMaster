package cn.graydove.httpmaster.starter.exception;

import cn.graydove.httpmaster.core.exception.HttpRequestException;

/**
 * @author graydove
 */
public class BadRequestException extends HttpRequestException {

    private int httpStatus;

    public BadRequestException(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public BadRequestException(String message, int httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public BadRequestException(String message, Throwable cause, int httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    public BadRequestException(Throwable cause, int httpStatus) {
        super(cause);
        this.httpStatus = httpStatus;
    }

    public BadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int httpStatus) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
