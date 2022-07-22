### 查找哈希算法
MurMurHash
xxHash

## 多线程
线程不安全： SimpleDateFormat
线程安全： DateTimeFormatter

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
}
```