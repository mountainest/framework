```sql
-- 用户账号表
CREATE TABLE "uc_user_t" (
    "uid" INT8 NOT NULL UNIQUE,
    "ctime" TIMESTAMP(6) NOT NULL,
    "nickname" VARCHAR(127) NOT NULL,
    "female_flg" BOOLEAN NOT NULL DEFAULT FALSE,
    "mobile" VARCHAR(127) NOT NULL,
    "email" VARCHAR(127) NOT NULL,
    "avatar_url" VARCHAR(127) NOT NULL DEFAULT '',
    "enabled_flg" BOOLEAN NOT NULL DEFAULT TRUE,
    "location" VARCHAR(255) NOT NULL DEFAULT ''
);

COMMENT ON TABLE "uc_user_t" IS '用户账号表';
COMMENT ON COLUMN "uc_user_t"."uid" IS '唯一标识，同一个用户稳定不变';
COMMENT ON COLUMN "uc_user_t"."nickname" IS '名称';
COMMENT ON COLUMN "uc_user_t"."female_flg" IS '女性标识';
COMMENT ON COLUMN "uc_user_t"."mobile" IS '手机号';
COMMENT ON COLUMN "uc_user_t"."email" IS '邮箱';
COMMENT ON COLUMN "uc_user_t"."avatar_url" IS '用户头像链接';
COMMENT ON COLUMN "uc_user_t"."enabled_flg" IS '启用标识';
COMMENT ON COLUMN "uc_user_t"."location" IS '用户注册时，所在的地理位置';

-- 用户帖子表
CREATE TABLE "uc_post_t" (
    "uid" INT8 NOT NULL UNIQUE,
    "ctime" TIMESTAMP(6) NOT NULL,
    "utime" TIMESTAMP(6) NOT NULL,
    "female_flg" BOOLEAN NOT NULL DEFAULT FALSE,
    "birthday" INT4 NOT NULL,
    "location" VARCHAR(127) NOT NULL,
    "target_female_flg" BOOLEAN NOT NULL DEFAULT FALSE,
    "target_female_flg" BOOLEAN NOT NULL DEFAULT FALSE,
    "email" VARCHAR(127) NOT NULL,
    "pictures" VARCHAR(127) NOT NULL DEFAULT '',
    "enabled_flg" BOOLEAN NOT NULL DEFAULT TRUE,
    "location" VARCHAR(255) NOT NULL DEFAULT ''
);

COMMENT ON TABLE "uc_user_t" IS '用户信息表';
COMMENT ON COLUMN "uc_user_t"."uid" IS '唯一标识，同一个用户稳定不变';
COMMENT ON COLUMN "uc_user_t"."female_flg" IS '女性标识';
COMMENT ON COLUMN "uc_user_t"."year" IS '出生年份';
COMMENT ON COLUMN "uc_user_t"."month" IS '出生月份';
COMMENT ON COLUMN "uc_user_t"."location" IS '身高';
COMMENT ON COLUMN "uc_user_t"."location" IS '体重';
COMMENT ON COLUMN "uc_user_t"."location" IS '位置';
COMMENT ON COLUMN "uc_user_t"."province" IS '家乡省份';
COMMENT ON COLUMN "uc_user_t"."avatar_url" IS '学历';
COMMENT ON COLUMN "uc_user_t"."location" IS '工作地';
COMMENT ON COLUMN "uc_user_t"."location" IS '公司性质';
COMMENT ON COLUMN "uc_user_t"."location" IS '公司行业';
COMMENT ON COLUMN "uc_user_t"."job" IS '职业';
COMMENT ON COLUMN "uc_user_t"."location" IS '年收入';
COMMENT ON COLUMN "uc_user_t"."location" IS '性格';
COMMENT ON COLUMN "uc_user_t"."location" IS '星座';
COMMENT ON COLUMN "uc_user_t"."location" IS '兴趣爱好';
COMMENT ON COLUMN "uc_user_t"."location" IS '补充说明';
COMMENT ON COLUMN "uc_user_t"."enabled_flg" IS '启用标识';
COMMENT ON COLUMN "uc_user_t"."pictures" IS '靓照秀';

-- 目标用户表

-- 目标性格表
-- 目标年龄表
-- 目标家乡表

-- 性格表
-- 省份表

-- 用户中心用户表
CREATE TABLE "uc_user_t" (
    "id" BIGSERIAL PRIMARY KEY NOT NULL,
    "creator_uid" INT8 NOT NULL,
    "updater_uid" INT8 NOT NULL,
    "ctime" TIMESTAMP(6) NOT NULL,
    "utime" TIMESTAMP(6) NOT NULL,
    "uid" INT8 NOT NULL UNIQUE,
    "sid" VARCHAR(10) NOT NULL UNIQUE,
    "ad_name" VARCHAR(127) NOT NULL,
    "nickname" VARCHAR(127) NOT NULL,
    "female_flg" BOOLEAN NOT NULL DEFAULT FALSE,
    "mobile" VARCHAR(127) NOT NULL,
    "email" VARCHAR(127) NOT NULL,
    "avatar_url" VARCHAR(127) NOT NULL DEFAULT '',
    "did" INT8 NOT NULL,
    "enabled_flg" BOOLEAN NOT NULL DEFAULT TRUE,
    "description" VARCHAR(255) NOT NULL DEFAULT ''
);

COMMENT ON TABLE "uc_user_t" IS '用户表';
COMMENT ON COLUMN "uc_user_t"."id" IS '主键';
COMMENT ON COLUMN "uc_user_t"."uid" IS '唯一标识，同一个用户稳定不变';
COMMENT ON COLUMN "uc_user_t"."sid" IS '用户工号';
COMMENT ON COLUMN "uc_user_t"."ad_name" IS '域账号名';
COMMENT ON COLUMN "uc_user_t"."nickname" IS '名称';
COMMENT ON COLUMN "uc_user_t"."female_flg" IS '女性标识';
COMMENT ON COLUMN "uc_user_t"."mobile" IS '手机号';
COMMENT ON COLUMN "uc_user_t"."email" IS '邮箱';
COMMENT ON COLUMN "uc_user_t"."avatar_url" IS '用户头像链接';
COMMENT ON COLUMN "uc_user_t"."did" IS '归属部门id';
COMMENT ON COLUMN "uc_user_t"."enabled_flg" IS '启用标识';
COMMENT ON COLUMN "uc_user_t"."description" IS '描述';

-- 用户中心部门表
CREATE TABLE "uc_dept_t" (
    "id" BIGSERIAL PRIMARY KEY NOT NULL,
    "creator_uid" INT8 NOT NULL,
    "updater_uid" INT8 NOT NULL,
    "ctime" TIMESTAMP(6) NOT NULL,
    "utime" TIMESTAMP(6) NOT NULL,
    "did" INT8 NOT NULL UNIQUE,
    "dept_no" CHAR(10) NOT NULL UNIQUE,
    "name" VARCHAR(127) NOT NULL,
    "sort_no" INT2 NOT NULL DEFAULT 100,
    "pid" INT8 NOT NULL,
    "level" INT2 NOT NULL,
    "leader_uid" INT8 NOT NULL DEFAULT -1,
    "leader_ad_name" VARCHAR(255) NOT NULL DEFAULT '',
    "did_path" VARCHAR(127) NOT NULL,
    "name_path" VARCHAR(127) NOT NULL DEFAULT '',
    "enabled_flg" BOOLEAN NOT NULL DEFAULT TRUE,
    "description" VARCHAR(255) NOT NULL DEFAULT ''
);

COMMENT ON TABLE "uc_dept_t" IS '部门表';
COMMENT ON COLUMN "uc_dept_t"."id" IS '主键';
COMMENT ON COLUMN "uc_dept_t"."did" IS '部门唯一标识，同一个部门稳定不变';
COMMENT ON COLUMN "uc_dept_t"."dept_no" IS '部门编号';
COMMENT ON COLUMN "uc_dept_t"."name" IS '部门名称';
COMMENT ON COLUMN "uc_dept_t"."sort_no" IS '排序号，同一层级内排序';
COMMENT ON COLUMN "uc_dept_t"."pid" IS '上级部门did，根部门id为-1';
COMMENT ON COLUMN "uc_dept_t"."level" IS '部门层级，根层级为-1';
COMMENT ON COLUMN "uc_dept_t"."leader_uid" IS '部门主管uid';
COMMENT ON COLUMN "uc_dept_t"."did_path" IS '部门did路径，从根部门开始，用英文逗号分割';
COMMENT ON COLUMN "uc_dept_t"."name_path" IS '部门名称路径，从根部门开始，用英文逗号分割';
COMMENT ON COLUMN "uc_dept_t"."enabled_flg" IS '启用标识';
COMMENT ON COLUMN "uc_dept_t"."description" IS '描述';

-- 分布式锁
CREATE TABLE "uc_method_lock_t" (
    "id" BIGSERIAL PRIMARY KEY NOT NULL,
    "ctime" TIMESTAMP(6) NOT NULL,
    "utime" TIMESTAMP(6) NOT NULL,
    "method_name" VARCHAR(127) NOT NULL UNIQUE,
    "thread_id" INT8 NOT NULL,
    "version" INT8 NOT NULL,
    "description" VARCHAR(255) NOT NULL DEFAULT ''
);

COMMENT ON TABLE "uc_method_lock_t" IS '用户表';
COMMENT ON COLUMN "uc_method_lock_t"."id" IS '主键';
COMMENT ON COLUMN "uc_method_lock_t"."method_name" IS '方法名称';
COMMENT ON COLUMN "uc_method_lock_t"."thread_id" IS '线程，用于重入';
COMMENT ON COLUMN "uc_method_lock_t"."version" IS '版本号';
COMMENT ON COLUMN "uc_method_lock_t"."description" IS '描述信息';

```