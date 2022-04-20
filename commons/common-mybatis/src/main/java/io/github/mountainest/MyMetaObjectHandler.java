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
        String uid = this.getUid();

        strictInsertFill(metaObject, "creator_uid", String.class, uid);
        strictInsertFill(metaObject, "ctime", Date.class, date);

        this.fill(metaObject, uid, date);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date date = new Date();
        String uid = this.getUid();

        this.fill(metaObject, uid, date);
    }

    private void fill(MetaObject metaObject, String uid, Date date) {
        strictUpdateFill(metaObject, "updater_uid", String.class, uid);
        strictUpdateFill(metaObject, "utime", Date.class, date);
    }

    private String getUid() {
        return SecurityContext.getUid().orElse(null);
    }
}
