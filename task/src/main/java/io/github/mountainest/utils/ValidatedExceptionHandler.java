package io.github.mountainest.utils;

import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ValidatedExceptionHandler {
    /**
     * 处理json参数校验错误。
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleException(MethodArgumentNotValidException e) {
        List<FieldError> list = e.getBindingResult().getFieldErrors();
        for (FieldError error: list) {
            String code = error.getCode();
            String msg = error.getField() + " " + error.getDefaultMessage();
            // 框架自带debug日志，就没有必要记debug日志了，info日志，自己根据需要是否添加。
//            log.info("参数校验失败。{}： {}, 异常码：{}, 输入值：{}",
//                error.getObjectName(), msg, code, error.getRejectedValue());
            return Response.fail(code.hashCode(), msg);
        }

        log.error("未知的校验错误。", e);
        return Response.fail(ErrCode.UNKNOWN_REQUEST_ERROR);
    }

    /**
     * 处理简单参数校验错误
     * @param e
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public Response handleException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> set = e.getConstraintViolations();
        for (ConstraintViolation v: set) {
//            log.info("参数校验失败。{}, {}", v.getRootBeanClass().getName(), e.getLocalizedMessage());
            PathImpl path = (PathImpl)v.getPropertyPath();
            return Response.fail(path.hashCode(), path.getLeafNode().getName() + " " + v.getMessage());
        }

        log.error("未知的校验错误。", e);
        return Response.fail(ErrCode.UNKNOWN_REQUEST_ERROR);
    }

    /**
     * 处理简单参数校验错误，加上@RequestParam时，抛出该异常。
     * @param e
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public Response handleException(MissingServletRequestParameterException e) {
        return Response.fail(e.hashCode(), e.getLocalizedMessage());
    }
}
