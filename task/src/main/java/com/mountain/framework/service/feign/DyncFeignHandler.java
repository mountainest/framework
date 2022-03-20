package com.mountain.framework.service.feign;

import com.mountain.framework.controller.valid.ReqDto;
import com.mountain.framework.utils.Constant;
import com.mountain.framework.utils.ErrCode;
import com.mountain.framework.utils.Response;
import feign.Feign;
import feign.FeignException;
import feign.Logger.Level;
import feign.RequestTemplate;
import feign.Target.EmptyTarget;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.slf4j.Slf4jLogger;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DyncFeignHandler {
    private DyncFeign dyncFeign;

    public DyncFeignHandler() {
        Encoder encoder = new Encoder() {
            @Override
            public void encode(Object o, Type type, RequestTemplate requestTemplate) throws EncodeException {
                requestTemplate.body(Constant.GSON.toJson(o).getBytes(StandardCharsets.UTF_8), null);
            }
        };
        Decoder decoder = new Decoder() {
            @Override
            public Object decode(feign.Response response, Type type)
                throws IOException, DecodeException, FeignException {
                return Constant.GSON.fromJson(response.body().asReader(Util.UTF_8), type);
            }
        };

        this.dyncFeign = Feign.builder()
            .encoder(encoder).decoder(decoder)
//            .options(new Options(10L, TimeUnit.SECONDS, 10L, TimeUnit.SECONDS, true))
//            .retryer(new feign.Retryer.Default(2000L, 2000L, 5))
            .logger(new Slf4jLogger())
            .logLevel(Level.BASIC)
            .target(EmptyTarget.create(DyncFeign.class));
    }

    @SuppressWarnings("unchecked")
    public Response call(String url, String strHeader, ReqDto req) {
        URI uri;
        try {
            uri = URI.create(url);
        } catch (IllegalArgumentException e) {
            log.error("请求的url错误。url = {}", url, e);
            return Response.fail(ErrCode.REQUEST_URL_ERR);
        }

        Map<String, Object> mapHeader = Constant.GSON.fromJson(strHeader, Map.class);

        return this.dyncFeign.post(uri, mapHeader, req);
    }
}
