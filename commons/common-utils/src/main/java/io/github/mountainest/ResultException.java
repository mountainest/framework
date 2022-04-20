package io.github.mountainest;

import lombok.Getter;

@Getter
public class ResultException extends RuntimeException {
    private int code;
    private String msg;

    public ResultException(int code) {
        this.code = code;
//        this.msg = MessageUtils.getMessage(code);
    }
}
