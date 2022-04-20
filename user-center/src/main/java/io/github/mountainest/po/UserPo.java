package io.github.mountainest.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.mountainest.BasePo;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@TableName("uc_user_t")
public class UserPo extends BasePo {
    private Long id;
    @ApiModelProperty("唯一标识，同一个用户稳定不变")
    @NotNull
    private Long uid;
    @ApiModelProperty("用户工号")
    @NotBlank
    private String sid;
    @ApiModelProperty("用户域账号名称")
    @NotBlank
    private String adName;
    @ApiModelProperty("用户名")
    @NotBlank
    private String nickname;
    @ApiModelProperty("女性标识")
    @NotNull
    private Boolean femaleFlg;
    @NotBlank
    private String mobile;
    @NotBlank
    private String email;
    @ApiModelProperty("用户头像链接")
    @NotNull
    private String avatarUrl;
    @ApiModelProperty("用户归属的部门id")
    @NotNull
    private Long did;
    @NotNull
    private Boolean enabledFlg;
    private String description;
}
