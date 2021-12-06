package com.mountain.framework.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReqDto {
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
    }
}
