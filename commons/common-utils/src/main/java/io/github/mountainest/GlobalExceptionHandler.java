package io.github.mountainest;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理json参数校验错误。
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleException(MethodArgumentNotValidException e) {
        List<FieldError> list = e.getBindingResult().getFieldErrors();
        for (FieldError error: list) {
            String code = error.getCode();
            String msg = error.getField() + " " + error.getDefaultMessage();
            // 框架自带debug日志，就没有必要记debug日志了，info日志，自己根据需要是否添加。
//            log.info("参数校验失败。{}： {}, 异常码：{}, 输入值：{}",
//                error.getObjectName(), msg, code, error.getRejectedValue());
            return Result.fail(msg);
        }

        log.error("未知的校验错误。", e);
        return Result.fail(ErrCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理简单参数校验错误
     * @param e
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public Result handleException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> set = e.getConstraintViolations();
        for (ConstraintViolation v: set) {
//            log.info("参数校验失败。{}, {}", v.getRootBeanClass().getName(), e.getLocalizedMessage());
            PathImpl path = (PathImpl)v.getPropertyPath();
            return Result.fail(path.getLeafNode().getName() + " " + v.getMessage());
        }

        log.error("未知的校验错误。", e);
        return Result.fail(ErrCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理简单参数校验错误，加上@RequestParam时，抛出该异常。
     * @param e
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public Result handleException(MissingServletRequestParameterException e) {
        return Result.fail(e.getLocalizedMessage());
    }

    @ExceptionHandler({ResultException.class})
    public Result handleException(ResultException e) {
        return Result.fail(e.getCode());
    }
}
