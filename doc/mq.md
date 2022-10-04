kafka读写都在leader上，实现了强一致性。ISR机制保证了高可用性。

|      | kafka | rocketmq                                                                                                                                                                            | pulsar |
|------|-------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------| ---|
| 云原生  | 否     | 否                                                                                                                                                                                   | 是 |
| 事务消息 | 否     | 是                                                                                                                                                                                   |   |
| 延时队列 | 否     | 是，但是不支持任意时间的延时，只支持以下几个固定的延时等级。所有的延迟消息由producer发出之后，都会存放到同一个topic（SCHEDULE_TOPIC_XXXX）下，不同的延迟级别会对应不同的队列序号，当延迟时间到之后，由定时线程读取转换为普通的消息存的真实指定的topic下，此时对于consumer端此消息才可见，从而被consumer消费。 |   |

### kafka consumer rebalance
* RangeAssignor （默认的）
* RoundRobinAssignor
* StickyAssignor （最新版支持的）

https://zhuanlan.zhihu.com/p/421160954

### kafka consumer offset 提交确认
1. 自动提交
2. 手动提交（同步、异步）
https://www.ahfesco.com.cn/affairs/Article.asp?id=3278