package io.github.mountainest.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("uc_user_t")
public class UserPo {
    private String uid;
    @TableField(fill = FieldFill.INSERT)
    private Date ctime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date utime;

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
