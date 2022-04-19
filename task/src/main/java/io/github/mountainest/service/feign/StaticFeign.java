package io.github.mountainest.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "static", url = "https://www.baidu.com")
public interface StaticFeign {
    @GetMapping("/s")
    void test(@RequestParam String wd);
}
