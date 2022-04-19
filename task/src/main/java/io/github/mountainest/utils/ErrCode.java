package io.github.mountainest.utils;

public enum ErrCode {
    // 非零值代表成功，能够减少失误。
    OK(200, "OK"),

    // 客户端参数错误
    UNKNOWN_REQUEST_ERROR(40000, "请求错误。"),
    CLIENT_PARAM_ERROR(40001, "参数校验错误。"),

    // 内部服务器错误
    REQUEST_URL_ERR(50000, "请求的URL异常。"),
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
