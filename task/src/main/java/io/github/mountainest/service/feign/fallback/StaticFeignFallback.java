package io.github.mountainest.service.feign.fallback;

import io.github.mountainest.service.feign.StaticFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StaticFeignFallback implements FallbackFactory<StaticFeign> {
    @Override
    public StaticFeign create(Throwable cause) {
        log.error("调用失败。", cause);
        return new StaticFeign() {
            @Override
            public void test(String wd) {
                return;
            }
        };
    }
}
