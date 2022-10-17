## 通用
mybatis中的#{}会进行预编译，能防止sql注入，${}只是进行字符串替换。

## pg
http://www.postgres.cn/docs/13/
```sql
-- 会生成200个id
select generate_series(1, 200);
-- 添加列
alter table "test_t" add column "name" varchar(127);
COMMENT ON COLUMN "test_t"."id" IS '主键';
-- 添加唯一性约束
ALTER TABLE "test_t"
    ADD CONSTRAINT "st_name_uk" UNIQUE("name");
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
-- 统计每天的数量
select date_trunc('day', "create_time"), count(*) from "bpm_t" group by date_trunc('day', "create_time");
-- 按时段统计
select extract(hour from "create_time"), count(*) from "bpm_t" group by extract(hour from "create_time");
```
pg并没有聚簇索引的概念，但是可以指定某个索引列，将数据按行聚集在一起，也就是基于索引进行物理上的排序。参考：https://blog.csdn.net/xiaobaixiaobai1234/article/details/6146610  

pg的主备复制属于物理复制，时延毫秒级，相较于MySQL基于binlog的逻辑复制，数据的一致性更高，复制性能也更高，对主机性能的影响更小。  
pg的物理复制是实例级别的复制，无法做到表级复制，占用的磁盘、网络等开销更大。备库只读不写。  
逻辑复制是重新执行一次DML语句。

pg支持的链接方式有嵌套循环，哈希链接，排序合并，而mysql只支持嵌套循环。

pg的稳定行更强，mysql在断电、崩溃等灾难场景下会有数据丢失的情况。

pg支持递归查询：https://www.cnblogs.com/abclife/p/11022849.html  
mysql在8.0版本之后才支持递归查询。

## mysql
聚簇索引默认是主键，也可以设置为其他索引。

## redis
redis 只有keys命令支持通配符，有3个通配符 *, ? ,[]
*: 通配任意多个字符
?: 通配单个字符
[]: 通配括号内的某1个字符

## pg vs mysql
1. 为什么说金融行业一般用pg的多？金融行业有什么特点？数据一致性？数据不丢？
2. 高可用：pg稳如死狗，物理复制，数据一致性更高。
3. 一致性：mysql逻辑复制，pg物理复制（WAL流复制、槽复制、级联复制），严格单写，数据一致性更高。
4. 高并发：pg并发量更大
5. 高性能：pg单表支持的数据量更大
6. 为什么说互联网行业用mysql的多？人员参差不齐，懂mysql的多，就像有些公司要求接口都定义为post类型一样。
7. pg堆表，mysql索引组织表。支持大数据量的都是堆表？
8. pg和innodb都支持多列主键。pg可以不设置主键，也不要求主键自增。
9. 那到底还要不要搞一个自增主键呢？直接用一个唯一索引作为主键？
10. 支持的索引类型较全：B*树、hash、GIN（Generalized Inverted Index，倒排索引，全文索引）、BRIN（Block Range Index，块范围索引，对块的摘要，比如时序数据）、函数索引、部分索引（基于部分行建索引）
11. pg支持正则表达式搜索。
12. pg支持递归查询，mysql的8.0版本才支持。
13. mysql B+树（块的最低使用率1/2），pg B*树（块的最低使用率2/3），所以pg分配新节点的概率低于mysql，块的使用率较高。


高可用
主备：keepalived，冷备
共享存储

Canal：阿里的MySQL数据库binlog的增量订阅&消费
Mycat：MySQL分库分表
Galera Cluster：MySQL多主集群，高可用。

pgpool-II：pg的高可用中间件，同步复制
http://www.postgres.cn/downfiles/pg2016conf_day1_s3_pm4.pdf
https://blog.csdn.net/sunny05296/article/details/121170496
https://cloud.tencent.com/developer/article/1785162

Seata：分布式事务

DC < AZ < Region
AZ = 1+ DC
region = 2+ AZ，AZ之间使用低延迟的光纤互联

mybatis: sqlSessionFactory -> sqlSession -> ResultSet -> 转成pojo