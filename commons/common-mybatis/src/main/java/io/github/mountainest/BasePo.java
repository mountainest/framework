package io.github.mountainest;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class BasePo {
    @TableField(fill = FieldFill.INSERT)
    private Long creatorUid;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updaterUid;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
