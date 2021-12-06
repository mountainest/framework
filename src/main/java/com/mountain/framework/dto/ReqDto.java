package com.mountain.framework.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReqDto {
    @NotNull
    private Integer id;
}
