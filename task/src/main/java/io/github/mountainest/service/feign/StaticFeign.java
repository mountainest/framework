package io.github.mountainest.service.feign;

import io.github.mountainest.config.ReqHeaderInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "static", url = "https://www.baidu.com", configuration = ReqHeaderInterceptor.class)
public interface StaticFeign {
    @GetMapping("/s")
    void test(@RequestParam String wd);
}
