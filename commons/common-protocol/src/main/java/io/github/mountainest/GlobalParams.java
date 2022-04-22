package io.github.mountainest;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class GlobalParams {
//    @Value("${param.base.ignore.urls}")
    private String[] ignoreUrls;
}
