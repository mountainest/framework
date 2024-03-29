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

<b>常见的线程安全类：</b>  
Vector，Stack，HashTable，StringBuffer、ConcurrentHashMap、ThreadPoolExecutor。  
Collections中的synchronizedCollection(Collection c)方法可将一个集合变为线程安全。  
<b>非线程安全类：</b>  
ArrayList、LinkedList、StringBulider、  
HashMap -> HashSet、  
TreeMap（基于红黑树的Map实现） -> TreeSet


<b>比较常用的构建线程安全的List有三种方法：</b>
1. 使用Vector容器
2. 使用Collections的静态方法synchronizedList(List< T> list)
3. 采用CopyOnWriteArrayList容器

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

Future: 会阻塞主线程，或者主线程不停的去轮询是否结束。  
CompletableFuture: 回调的方式，主线程不需要关心任务何时结束。同时实现了CompletionStage和Future接口。

https://zhuanlan.zhihu.com/p/344431341
https://www.cnblogs.com/txmfz/p/11266411.html

以run开头的方法，其入口参数一定是无参的，并且没有返回值，类似于执行Runnable方法。
以supply开头的方法，入口也是没有参数的，但是有返回值
以Accept开头或者结尾的方法，入口参数是有参数，但是没有返回值
以Apply开头或者结尾的方法，入口有参数，有返回值

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

park/unpark 通过系统调用，而wait/notify通过对象锁。
park是等待一个许可，unpark是为某线程发放一个许可。

unpark调用时，如果当前线程还未进入park，则将许可设置为true;park调用时，判断许可是否为true，如果是true，则 park 不阻塞线程，程序继续往下执行；如果是false，则阻塞线程。所以只要我们的 park/unpark 成对出现，无论执行顺序如何，都不会因此造成死锁。



#### bootstrap vs application
1) bootstrap 优先加载。 
2) bootstrap 里面的属性不能被覆盖


JVM调优参数
https://blog.csdn.net/m0_46316970/article/details/123585951

Error 和 Exception 均继承自Throwable