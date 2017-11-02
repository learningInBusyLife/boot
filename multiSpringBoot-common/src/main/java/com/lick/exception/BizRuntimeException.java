package com.lick.exception;

/**
 * @Description：
 * @Author: lick
 * @Date: 2017年11月01日 17:00
 * @Copyright: 版权归 lick 所有
 */
public class BizRuntimeException extends Exception {
    private final String errorCode;
    private final String msg;
    private Throwable throwable;

    public BizRuntimeException(String errorCode, String msg) {
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public BizRuntimeException(String errorCode, String msg, Throwable throwable) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.throwable = throwable;
    }
    @Override
    public String getMessage() {
        Throwable cause = getCause();
        String message = this.msg;
        if (cause != null) {
            StringBuilder sb = new StringBuilder();
            if (message != null) {
                sb.append("【errorCode】:").append(this.errorCode).append("; ");
            }
            if (message != null) {
                sb.append(this.msg).append("; ");
            }
            sb.append("nested exception is ").append(cause);
            return sb.toString();
        } else {
            return message;
        }
    }
    public String getErrorCode() {
        return errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
