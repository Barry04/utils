# Utils

## JSONUtil

JSON文本的key和value替换
在一个JSON数据中，JSON是可以无限嵌套的，假如需要一次性修改其中的一个值，常见的做法是层层解套，然后修改。
本工具类利用正则匹配方式处理JSON字符串，无需解套，只要传入修改的key，及新旧值的映射map就可以实现替换。
## 使用示例
```java
     Map<String, String> keys = new HashMap<>();
     keys.put("value", "newValue");
     
     String jsonString = "{ \"key\": \"value\" }";
     jsonString = JSONUtil.replaceJsonAll(jsonString, "key", keys);
     assertThat(jsonString, is("{ \"key\": \"newValue\" }")); // 结果为true 
	 
```

**JSONUtil 提供了三种替换 JSON 文本中格式的字符串的方法。**

### 1. 替换 "key":"originValue" 为 "key":"newValue"
```java

      /**
       * 替换json文本中全部的"key":"value"的格式的串，将指定的key的value替换为新的value，如果没有匹配规则则返回原值。
       * 例子："key":"originValue" 替换为"key":"newValue"。冒号前后的空格会自动忽略。
       *
       * @param json      传入的json
       * @param key       需要替换的key
       * @param valueMaps key：原值， value : 新值。
       * @return 替换后的json
       */
      String replaceJson(String json, String key, Map<String, String>... valueMaps)
```

### 2. "key":originValue 替换为"key":newValue。
   replaceJsonWhitOutQuot接口  
   参数说明：

```java
      /**
       * 替换json文本中全部的"key":value的格式的串，将指定的key的value替换为新的value，如果没有匹配规则则返回原值。
       * 例子："key":originValue 替换为"key":newValue。冒号前后的空格会自动忽略。
       *
       * @param json      传入的json
       * @param key       需要替换的key
       * @param valueMaps key：原值， value : 新值。
       * @return 替换后的json
       */
      String replaceJsonWhitOutQuote(String json, String key, Map<String, String>... valueMaps)
```

### 3. \"key\":\"originValue\" 替换为\"key\":\"newValue\"。
   replaceWarpJson接口  
   参数说明：

```java
      /**
       * 替换json文本中全部的\"key\":\"value\"的格式的串，将指定的key的value替换为新的value，如果没有匹配规则则返回原值。
       * 例子：\"key\":\"originValue\" 替换为\"key\":\"newValue\"。冒号前后的空格会自动忽略。
       *
       * @param json      传入的json
       * @param key       需要替换的key
       * @param valueMaps key：原值， value : 新值。
       * @return 替换后的json
       */
      String replaceWarpJson(String json, String key, Map<String, String>... valueMaps) {
    

```

replaceJsonAll接口中会将上述三种情况替换，但第一种和第二种情况需要在入参中告知，quote：true时第一种情况替换，false第二种情况替换。
如果不传quote时，默认为第一种情况。


```java
      /**
       * @param json      传入的json
       * @param key       需要替换的key
       * @param quote     替换值是否是字符串
       * @param valueMaps key：原值， value : 新值。  map数组，会循环遍历每一个map
       * @return 替换后的json
       */
      String replaceJsonAll(String json, String key, boolean quote, Map<String, String>... valueMaps)

```
