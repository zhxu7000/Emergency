package com.usyd.emergency.constant;

import lombok.Getter;

@Getter
public enum XError {
    SUCCESS(0, "success"),
    UNAUTHORIZED(401, "authentification failed"),
    USERNAME_OR_PASSWORD_INCORRECT(401, "username or password is incorrect"),
    UNKNOWN_ERROR(-1, "unknown error");

    public int code;
    public final String msg;

    XError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
