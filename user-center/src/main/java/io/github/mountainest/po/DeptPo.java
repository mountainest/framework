package io.github.mountainest.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.mountainest.BasePo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("uc_dept_t")
public class DeptPo extends BasePo {
    private Long id;
    @ApiModelProperty("唯一标识，同一个部门稳定不变")
    private Long did;
    @ApiModelProperty("部门编号")
    private String deptNo;
    @ApiModelProperty("部门名称")
    private String name;
    @ApiModelProperty("排序号，同一层级内排序，默认100")
    private Short sortNo;
    @ApiModelProperty("上级部门did，根部门id为0")
    private Long pid;
    @ApiModelProperty("部门层级，根层级为0")
    private Short level;
    @ApiModelProperty("部门主管uid")
    private Long leaderUid;
    @ApiModelProperty("部门did路径，从根部门开始，用英文逗号分割")
    private String didPath;
    @ApiModelProperty("部门名称路径，从根部门开始，用英文逗号分割")
    private String namePath;
    private Boolean enabledFlg;
    private String description;
}
