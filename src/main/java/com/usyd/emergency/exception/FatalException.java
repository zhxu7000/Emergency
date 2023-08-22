package com.usyd.emergency.exception;

public class FatalException extends BaseException{

    public FatalException(int code, String msg) {
        super(code, msg);
    }

    @Override
    public String getReportMsg() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Fatal Exception with code %d:%s", getCode(), getMsg()));
        return sb.toString();
    }
}
