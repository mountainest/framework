```sql
begin;
commit;
rollback;
SELECT @@tx_isolation;

show create table `user_t`;
show variables like 'character_set_database';
SHOW INDEX FROM `user_t`;
-- 将新表名改为原表
ALTER TABLE formal_table_new RENAME TO formal_table;

select version();
-- 查看配置的连接数
show variables like 'max_connections';
-- 查看已使用的连接数的最大值
show global status like 'Max_used_connections';
-- Threads_connected： 当前连接数，和 show full PROCESSLIST 的条数相等。
-- Threads_running： 正在执行sql的连接数，和 show full PROCESSLIST 中Query状态的条数相等。
show status like '%thread%';
-- Sleep: 正在等待客户端向它发送执行语句
show full PROCESSLIST;
-- 查询当前正在运行的sql
select * from information_schema.processlist where command = ('Query');
show status;

-- 性能相关的测量数据，在该库下面。
performance_schema
-- 查看慢查询的记录数
show global status like '%Slow_queries%';
-- 查询慢查询阈值，单位秒
show variables like 'long_query_time';

-- 查看sql执行日志的输出到file还是table
show variables like 'log_output';
-- 查看sql执行日志是否记录
SHOW VARIABLES LIKE "general_log%";

-- 查询事务
SELECT * FROM information_schema.INNODB_TRX;

-- 死锁 Waiting for table metadata lock
select * from information_schema.processlist where command not in ('Sleep') ORDER BY time desc;
kill {id};

-- Innodb_row_lock_current_waits : 当前等待锁的数量
-- Innodb_row_lock_time : 系统启动到现在，锁定的总时间长度
-- Innodb_row_lock_time_avg : 每次平均锁定的时间
-- Innodb_row_lock_time_max : 最长一次锁定时间
-- Innodb_row_lock_waits : 系统启动到现在总共锁定的次数
show status like 'innodb_row_lock_%';
show status like '%lock%';
-- 查询锁
select * from information_schema.innodb_lock_waits;
-- 提供有关InnoDB 事务已请求但尚未获取的每个锁的信息，以及事务持有的阻止另一个事务的每个锁。
select * from information_schema.innodb_locks;

-- 查看表锁的情况
show status like 'table%';
-- 查询是否锁表
show OPEN TABLES where In_use > 0;
    
-- 查询最近一次的死锁日志
show ENGINE INNODB STATUS;
-- 该参数开启之后，每次发生死锁后，系统会自动将死锁信息输出到错误日志中。
show variables like 'innodb_print_all_deadlocks';

-- 数据导出
select * from resource_num_info where resource_type_code = 'H3C_Standard_4C8G_02' and reserved_remain_desk_num > 0
    INTO OUTFILE '/home/deployer/pang.txt' CHARACTER SET utf8mb4;
SHOW VARIABLES LIKE "secure_file_priv";

-- information_schema 库里面常用的视图：
PROCESSLIST
STATISTICS
TABLES
INNODB_TRX
INNODB_BUFFER_PAGE_LRU
INNODB_BUFFER_PAGE
INNODB_METRICS
INNODB_BUFFER_POOL_STATS
INNODB_SYS_TABLESTATS
```

es vs mongodb
https://blog.csdn.net/kongliand/article/details/108691847

