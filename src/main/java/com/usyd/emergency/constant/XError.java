package com.usyd.emergency.constant;

import lombok.Getter;

@Getter
public enum XError {
    SUCCESS(0, "success"),
    UNAUTHORIZED(401, "authentification failed"),
    USERNAME_OR_PASSWORD_INCORRECT(401, "username or password is incorrect"),
    USERNAME_ALREADY_EXISTS(409, "username already exists"),

    DATABASE_ERROR(-2,"database error"),
    UNKNOWN_ERROR(-1, "unknown error");

    public int code;
    public final String msg;

    XError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
