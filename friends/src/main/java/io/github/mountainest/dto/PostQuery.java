package io.github.mountainest.dto;

import lombok.Data;

@Data
public class PostQuery {
    private String location;
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
