package com.mountain.framework.controller.valid;

import com.mountain.framework.utils.IpAddress;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReqDto {
    // Update仅仅是一个标识，甚至可以不定义使用PutMapping代替。
    public interface Update {}
    @NotNull(groups = Update.class)
    private Integer id;
    @NotBlank
    @Email
    private String email;
    @Valid
    private Address address;

    @Data
    private static class Address {
        private String province;
        @NotBlank
        private String city;
        @NotBlank
        @IpAddress
        private String ip;
    }
}
