package io.github.mountainest.dto;

import lombok.Data;

@Data
public class UserDto {
    private String location;
    private Boolean femaleFlg;
    private Integer birthday;
    private Short height;
    private Short weight;

    private Short annualSalary;
    private Short companyType;
    private String job;
    private Short education;

    private String province;
    private String city;
    private String personality;
    private String hobbies;
    private String constellation;

    private String addition;
    private String pictures;
    private String contact;

    private Boolean enabledFlg;
    private String coordinate;
}
