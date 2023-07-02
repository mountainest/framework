```sql

-- 用户自身信息表
CREATE TABLE "uc_user_t" (
    "uid" VARCHAR(127) NOT NULL UNIQUE,
    "ctime" TIMESTAMP(6) NOT NULL,
    "utime" TIMESTAMP(6) NOT NULL,
    "female_flg" BOOLEAN NOT NULL DEFAULT FALSE,
    "birthday" INT4 NOT NULL,
    "height" INT2 NOT NULL,
    "weight" INT2 NOT NULL,
    "location" GEOMETRY(Point, 4326) DEFAULT NULL,
    "province" VARCHAR(127) NOT NULL,
    "education" INT2 NOT NULL,
    "email" VARCHAR(127) NOT NULL,
    "pictures" VARCHAR(127) NOT NULL DEFAULT '',
    "enabled_flg" BOOLEAN NOT NULL DEFAULT TRUE,
    "location" VARCHAR(255) NOT NULL DEFAULT ''
);

COMMENT ON TABLE "uc_user_t" IS '用户信息表';
COMMENT ON COLUMN "uc_user_t"."uid" IS '唯一标识，同一个用户稳定不变';
COMMENT ON COLUMN "uc_user_t"."location" IS '所在城市';
COMMENT ON COLUMN "uc_user_t"."female_flg" IS '女性标识';
COMMENT ON COLUMN "uc_user_t"."birthday" IS '出生日期';
COMMENT ON COLUMN "uc_user_t"."height" IS '身高（公分）';
COMMENT ON COLUMN "uc_user_t"."weight" IS '体重（斤）';
COMMENT ON COLUMN "uc_user_t"."education" IS '学历，0：初中及以下，1：高中，2：大专，3：本科，4：硕士，5：博士及以上';
COMMENT ON COLUMN "uc_user_t"."location" IS '位置';
COMMENT ON COLUMN "uc_user_t"."job" IS '职业';
COMMENT ON COLUMN "uc_user_t"."company_type" IS '公司性质，0：自由职业，1：民营企业，2：民营大厂，3：国企及事业单位';
COMMENT ON COLUMN "uc_user_t"."annual_salary" IS '年收入';
COMMENT ON COLUMN "uc_user_t"."province" IS '家乡省份';
COMMENT ON COLUMN "uc_user_t"."city" IS '家乡城市';
COMMENT ON COLUMN "uc_user_t"."personality" IS '性格';
COMMENT ON COLUMN "uc_user_t"."hobbies" IS '兴趣爱好';
COMMENT ON COLUMN "uc_user_t"."constellation" IS '星座';
COMMENT ON COLUMN "uc_user_t"."addition" IS '补充说明';
COMMENT ON COLUMN "uc_user_t"."enabled_flg" IS '启用标识';
COMMENT ON COLUMN "uc_user_t"."pictures" IS '靓照秀';
COMMENT ON COLUMN "uc_user_t"."contact" IS '联系方式（如微信）';

-- 目标用户信息表
CREATE TABLE "uc_target_info_t" (
    "uid" VARCHAR(127) NOT NULL UNIQUE,
    "ctime" TIMESTAMP(6) NOT NULL,
    "utime" TIMESTAMP(6) NOT NULL,
    "birthday" INT4 NOT NULL,
    "height" INT2 NOT NULL,
    "weight" INT2 NOT NULL,
    "location" GEOMETRY(Point, 4326) DEFAULT NULL,
    "province" VARCHAR(127) NOT NULL,
    "education" INT2 NOT NULL,
    "email" VARCHAR(127) NOT NULL,
    "pictures" VARCHAR(127) NOT NULL DEFAULT '',
    "enabled_flg" BOOLEAN NOT NULL DEFAULT TRUE,
    "location" VARCHAR(255) NOT NULL DEFAULT ''
);

-- 目标性格表
-- 目标年龄表
-- 目标家乡表

-- 性格表
-- 省份表

```