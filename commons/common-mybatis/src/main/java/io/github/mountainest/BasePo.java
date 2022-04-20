package io.github.mountainest;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@NoArgsConstructor
@SuperBuilder
public class BasePo {
    @TableField(fill = FieldFill.INSERT)
    private Long creatorUid;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updaterUid;
    @TableField(fill = FieldFill.INSERT)
    private Date ctime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date utime;
}
