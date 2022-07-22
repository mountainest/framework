## 通用
mybatis中的#{}会进行预编译，能防止sql注入，${}只是进行字符串替换。

## pg
```sql
-- 会生成200个id
select generate_series(1, 200);
-- 增加删除非空约束
alter table "test1" alter column "logo_url" drop not null;
alter table "test1" alter column "logo_url" set not null;
-- 修改字段的默认值
ALTER TABLE "test_t" ALTER COLUMN "price" SET DEFAULT 7.77;
-- 删除字段的默认值
ALTER TABLE "test_t" ALTER COLUMN "price" DROP DEFAULT;
-- 重命名字段名称
ALTER TABLE products RENAME COLUMN product_no TO product_number;
-- 重命名表
ALTER TABLE products RENAME TO items;
-- 添加前缀
update "test1" set logo_url = concat('/server', "logo_url");
-- 联表删除
DELETE FROM "role_resource_t" USING "resource_t" WHERE "resource".id = "role_resource"."resource_id" AND "resource"."app_id" = 'app1';
delete from "language" using "resource" where "language".table_id = "resource".id and "resource".id is null;
-- 批量更新
update "resource" set "resource_bz_id" = "tmp"."resource_bz_id" from (values
(1, "test1"),
(2, "test2")
) as "tmp" ("id", "resource_bz_id")
where "resource"."id" = "tmp"."id";
```

## redis
redis 只有keys命令支持通配符，有3个通配符 *, ? ,[]
*: 通配任意多个字符
?: 通配单个字符
[]: 通配括号内的某1个字符