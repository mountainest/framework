package io.github.mountainest.dto;

import io.github.mountainest.BasePo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@SuperBuilder
public class DeptDto extends BasePo {
    private Long id;
    @ApiModelProperty("唯一标识，同一个部门稳定不变")
    @NotNull(groups = PutMapping.class)
    private Long did;
    @ApiModelProperty("部门编号")
    @NotBlank
    private String deptNo;
    @ApiModelProperty("部门名称")
    @NotBlank
    private String name;
    @ApiModelProperty("排序号，同一层级内排序")
    @NotNull(groups = PutMapping.class)
    private Short sortNo;
    @ApiModelProperty("上级部门did，根部门id为0")
    @NotNull
    private Long pid;
    @ApiModelProperty("部门层级，根层级为0")
    @NotNull
    private Short level;
    @ApiModelProperty("部门主管uid")
    @NotNull
    private Long leaderUid;
    @ApiModelProperty("部门did路径，从根部门开始，用英文逗号分割")
    @NotBlank
    private String didPath;
//    @ApiModelProperty("部门名称路径，从根部门开始，用英文逗号分割")
//    @NotBlank
//    private String namePath;
    @NotNull
    private Boolean enabledFlg;
    private String description;
}
