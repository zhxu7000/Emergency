package com.usyd.emergency.exception;


public class ConflictException extends BaseException{
    public ConflictException(int code, String msg) {
        super(code, msg);
    }

    @Override
    public String getReportMsg() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Confliction Exception with code %d:%s", getCode(), getMsg()));
        return sb.toString();
    }


}
