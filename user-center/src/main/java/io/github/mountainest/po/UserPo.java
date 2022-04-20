package io.github.mountainest.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.mountainest.BasePo;

import javax.validation.constraints.NotNull;

@TableName("uc_user_t")
public class UserPo extends BasePo {
    private Long id;
    /**
     * 唯一标识，同一个用户稳定不变
     */
    @NotNull
    private Long uid;
    /**
     * 用户工号
     */
    private String sid;
    /**
     * 用户域账号名称
     */
    private String adName;
    /**
     * 用户名称
     */
    private String nickname;
    private Boolean femaleFlg;
    private String mobile;
    private String email;
    /**
     * 用户头像链接
     */
    private String avatarUrl;
    /**
     * 用户归属的部门id
     */
    private Long did;
    private Boolean enabledFlg;
    private String description;
}
