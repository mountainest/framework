package io.github.mountainest;

import io.github.mountainest.config.SpringContextUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class MessageUtils {
    public static String getMessage(int code){
        return getMessage(code, new String[0]);
    }

    public static String getMessage(int code, String... params){
        MessageSource messageSource = SpringContextUtil.getBean(MessageSource.class);
        return messageSource.getMessage(code+"", params, LocaleContextHolder.getLocale());
    }
}
