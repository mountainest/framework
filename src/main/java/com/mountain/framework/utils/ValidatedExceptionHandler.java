package com.mountain.framework.utils;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ValidatedExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleException(MethodArgumentNotValidException e) {
        List<FieldError> list = e.getBindingResult().getFieldErrors();
        for (FieldError error: list) {
            String code = error.getCode();
            String msg = error.getField() + error.getDefaultMessage();
            log.debug("参数校验失败。{}： {}, 异常码：{}, 输入值：{}",
                error.getObjectName(), msg, code, error.getRejectedValue());
            return Response.fail(code.hashCode(), msg);
        }

        log.error("未知的校验错误。", e);
        return Response.fail(ErrCode.UNKNOWN_REQUEST_ERROR);
    }
}
