package com.mountain.framework.utils;

public class Response<T> {
    private int code;
    private String msg;
    private T data;

    public static Response success() {
        return new Response(ErrCode.OK, null);
    }

    public static <T> Response success(T data) {
        return new Response(ErrCode.OK, data);
    }

    public static Response fail(ErrCode errCode) {
        return new Response(errCode, null);
    }

    private Response(ErrCode errCode, T data) {
        this.code = errCode.getCode();
        this.msg = errCode.getMsg();
        this.data = data;
    }
}
