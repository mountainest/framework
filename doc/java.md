# 工具
## maven
依赖包使用情况分析
mvn dependency:analyze
分析传递依赖
mvn dependency:tree -X

# 算法
## 查找哈希算法
MurMurHash
xxHash

# 语法
## 多线程
线程不安全： SimpleDateFormat
线程安全： DateTimeFormatter

AtomicLong < LongAdder

## 常用
```java
public class Test {
    String name = "abc  dd";
    // 正则替换
    name = name.replaceAll("  +"," ");
    // 常量替换
    name = name.replace("  "," ");

    // 正则提取
    Pattern pattern = Pattern.compile("(.+)abc$");
    Matcher matcher = pattern.matcher("testabc");
    if(matcher.find()) {
        String test = matcher.group(1);
        // 输出abc
        System.out.println(test);
    }

    // list转数组
    List<String> list = Arrays.asList("a", "b");
    String str1 = String.join(",", list);
    String str = list.stream().collect(Collectors.joining(","));
}
```

## 优雅停机
1. jre本身自带的
   Runtime.getRuntime().addShutdownHook(t -> {});
2. 如果是Spring项目，使用@PreDestroy。
3. 以上两个都是封装好的，如果有多个钩子，无法控制钩子的执行顺序。
   使用Signal处理钩子队列，即可以控制顺序。
   当然前两个的原理也都是采用Signal。
```java
	Signal signal = new Signal("TERM");
	Signal.handle(signal, s -> {
		// 顺序执行钩子队列，比如先停止消费，再停止线程池任务处理。
        // 记得调用System.exit(0)，才能保证其他的钩子都被执行。
        System.exit(0);
	});
```
Signal对以下几个操作可以感知：
1. 程序正常退出； 
2. 使用System.exit();
3. 终端使用Ctrl+C触发的终端；
4. 系统关闭；
5. 使用kill pid命令干掉进程。

另外，jre原生的线程池，在服务下线时，既不会主动调用shutdownNow，也不会主动调用shutdown，因为没有去注册钩子。  
但是Spring的线程池ThreadPoolTaskExecutor，默认调用shutdownNow，因为在bean容器回收时，注册了钩子。

k8s在杀死pod时，会使用优雅停机（默认30秒），在优雅停机过程中，被杀死的pod在API Server中会被更新为dead状态。
此时，pod会在API中消失，不会往该pod转发请求，只是处理完已有的请求。
但是要注意，只有主进程会收到Signal信号，需要往java进程转发Signal信号。

虚拟机部署的jar包，使用actuator可以很好的实现优雅停机。

参考：
https://www.jianshu.com/p/38101bff6c29
https://www.cnblogs.com/dengkaiting/p/15808785.html

stream: 访问者模式（本来是外部迭代集合，改成了内部迭代)，责任链模式（多个行为链式操作）