package io.github.mountainest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.mountainest.BasePo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@SuperBuilder
public class UserDto extends BasePo {
    private Long id;
    @ApiModelProperty("唯一标识，同一个用户稳定不变")
    @NotNull
    private Long uid;
    @ApiModelProperty("用户工号")
    @NotBlank
    private String sid;
    @ApiModelProperty("用户域账号名称")
    @NotBlank
    @JsonProperty("ad_name")
    private String adName;
    @ApiModelProperty("用户名")
    @NotBlank
    private String nickname;
    @ApiModelProperty("女性标识")
    @NotNull
    @JsonProperty("female_flg")
    private Boolean femaleFlg;
    @NotBlank
    private String mobile;
    @NotBlank
    private String email;
    @ApiModelProperty("用户头像链接")
    @NotNull
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @ApiModelProperty("用户归属的部门id")
    @NotNull
    private Long did;
    @NotNull
    @JsonProperty("enabled_flg")
    private Boolean enabledFlg;
    private String description;
}
