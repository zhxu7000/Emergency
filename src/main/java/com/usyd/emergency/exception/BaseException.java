package com.usyd.emergency.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {
    private final int code;
    private final String msg;

    public BaseException(int code, String msg) {
        super();
        this.msg = msg;
        this.code = code;
    }

    public abstract String getReportMsg();


}
