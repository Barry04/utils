# utils

## JSONUtils

三种替换json文本中格式的字符串。

1. "key":"originValue" 替换为"key":"newValue"。
   replaceJson接口。  
   参数说明：

```
      * @param json      传入的json
      * @param key       需要替换的key
      * @param valueMaps key：原值， value : 新值。  map数组，会循环遍历每一个map
      * @return 替换后的json
```

2. "key":originValue 替换为"key":newValue。
   replaceJsonWhitOutQuot接口  
   参数说明：

```
      * @param json      传入的json
      * @param key       需要替换的key
      * @param valueMaps key：原值， value : 新值。  map数组，会循环遍历每一个map
      * @return 替换后的json
```

3. \"key\":\"originValue\" 替换为\"key\":\"newValue\"。
   replaceWarpJson接口  
   参数说明：

```
      * @param json      传入的json
      * @param key       需要替换的key
      * @param valueMaps key：原值， value : 新值。  map数组，会循环遍历每一个map
      * @return 替换后的json
```

replaceJsonAll接口中会将上述三种情况替换，但第一种和第二种情况需要在入参中告知，quote：true时第一种情况替换，false第二种情况替换。
如果不传quote时，默认为第一种情况。
replaceJsonAll接口  
参数说明：

```
      * @param json      传入的json
      * @param key       需要替换的key
      * @param quote     替换值是否是字符串
      * @param valueMaps key：原值， value : 新值。  map数组，会循环遍历每一个map
      * @return 替换后的json
```
