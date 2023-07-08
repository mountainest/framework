package io.github.mountainest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PostDto {
    private Date ctime;
    private Date utime;

    @NotBlank
    private String location;
    @NotNull
    private Boolean femaleFlg;
    @NotNull
    private Integer birthday;
    @NotNull
    private Short height;
    @NotNull
    private Short weight;

    @NotNull
    private Short annualSalary;
    private Short companyType;
    private String job;
    @NotNull
    private Short education;

    private String province;
    private String city;
    private String personality;
    private String hobbies;
    private String constellation;

    private String addition;
    private String pictures;
    @NotNull
    private String contact;

    @NotNull
    private Boolean enabledFlg;
    private String coordinate;
}
