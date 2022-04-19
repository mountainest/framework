package io.github.mountainest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value = "响应")
@Getter
public class Result<T> {
    @ApiModelProperty(value = "响应编码：0表示成功，其他表示失败")
    private int code;
    private String msg;
    private T data;

    public static Result success() {
        return new Result(0, MessageUtils.getMessage(0), null);
    }

    public static <T> Result success(T data) {
        return new Result(0, MessageUtils.getMessage(0), data);
    }

    public static Result fail(int code) {
        return new Result(code, MessageUtils.getMessage(code), null);
    }

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
