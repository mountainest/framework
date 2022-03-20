package com.mountain.framework.utils;

import lombok.Getter;

// get方法用于序列化，否则会报406客户端错误。
@Getter
@SuppressWarnings("unchecked")
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

    public static Response fail(int code, String msg) {
        return new Response(code, msg);
    }

    private Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Response(ErrCode errCode, T data) {
        this.code = errCode.getCode();
        this.msg = errCode.getMsg();
        this.data = data;
    }
}
