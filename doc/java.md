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