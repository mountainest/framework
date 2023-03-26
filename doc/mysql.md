```sql
begin;
commit;
rollback;
SELECT @@tx_isolation;

show create table `user_t`;
show variables like 'character_set_database';
SHOW INDEX FROM `user_t`;
select version();

-- 查询事务
SELECT * FROM information_schema.INNODB_TRX;
-- 查询锁
select * from information_schema.innodb_lock_waits;
select * from information_schema.innodb_locks;

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

-- 查看表锁的情况
show status like 'table%';
-- 查询是否锁表
show OPEN TABLES where In_use > 0;
-- 查询最近一次的死锁日志
show ENGINE INNODB STATUS;

```