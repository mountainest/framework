package io.github.mountainest.service.feign;

import io.github.mountainest.controller.valid.ReqDto;
import io.github.mountainest.utils.Response;
import feign.HeaderMap;
import feign.Headers;
import feign.RequestLine;
import java.net.URI;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "dync")
public interface DyncFeign {
    @RequestLine("POST")
    @Headers({"Content-Type: application/json"})
    Response post(URI uri, @HeaderMap Map<String, Object> mapHeader, @RequestBody ReqDto req);
}
