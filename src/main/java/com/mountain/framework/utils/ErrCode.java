package com.mountain.framework.utils;

public enum ErrCode {
    // 非零值代表成功，能够减少失误。
    OK(200, "OK"),

    // 客户端参数错误
    // 内部服务器错误
    ;

    ErrCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
