package io.github.mountainest;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        Long uid = this.getUid();

        strictInsertFill(metaObject, "creator_uid", Long.class, uid);
        strictInsertFill(metaObject, "ctime", Date.class, date);

        this.fill(metaObject, uid, date);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date date = new Date();
        Long uid = this.getUid();

        this.fill(metaObject, uid, date);
    }

    private void fill(MetaObject metaObject, Long uid, Date date) {
        strictUpdateFill(metaObject, "updater_uid", Long.class, uid);
        strictUpdateFill(metaObject, "utime", Date.class, date);
    }

    private Long getUid() {
        return SecurityContext.getUid().orElse(null);
    }
}
