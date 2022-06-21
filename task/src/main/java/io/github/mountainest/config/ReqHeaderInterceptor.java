package io.github.mountainest.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

/**
 * 如果只对某个feignclient生效，可以在feignclient加上configuration参数。
 * 如果对全局生效，此处加上@Configuration注解。
 */
public class ReqHeaderInterceptor implements RequestInterceptor {
    @Value("${param.iam.token:abc}")
    private String token;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String apiToken = this.getToken();
        if (StringUtils.isEmpty(apiToken)) {
            return;
        }

        requestTemplate.header("x-gw-token", apiToken);
    }

    private String getToken() {
        return this.token;
    }
}
