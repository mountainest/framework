package io.github.mountainest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PostQuery {
    @ApiModelProperty("根据更新时间倒序查询，该字段传当前分页的最大时间")
    @NotNull
    private Date utime;
    @NotBlank
    private String location;
    @NotNull
    private Boolean femaleFlg;
    private Integer minBirthday;
    private Integer maxBirthday;
    private Short minHeight;
    private Short maxHeight;
    private Short weight;
    private Short minWeight;
    private Short maxWeight;

    private Short minAnnualSalary;
    private Short companyType;
    private String job;
    private Short education;

    private String province;
    private String city;
}
