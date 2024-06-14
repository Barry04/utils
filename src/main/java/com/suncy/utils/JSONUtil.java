package com.suncy.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class JSONUtil {
	
	private static final String ESC_QUOT = "\"";
	
	private static final String ESC_SLASH_QUOT = "\\\"";
	
	private static final String SLASH = "\\";
	
	/**
	 * 替换json文本中全部的"key":"value"的格式的串，将指定的key的value替换为新的value，如果没有匹配规则则返回原值。
	 * 例子："key":"originValue" 替换为"key":"newValue"。冒号前后的空格会自动忽略。
	 *
	 * @param json      传入的json
	 * @param key       需要替换的key
	 * @param valueMaps key：原值， value : 新值。
	 * @return 替换后的json
	 */
	public static String replaceJson(String json, String key, Map<String, String>... valueMaps) {
		if (StringUtils.isBlank(json) || StringUtils.isBlank(key) ||
				Objects.isNull(valueMaps) || valueMaps.length == 0) {
			return json;
		}
		if (isJsonString(json) && json.contains(ESC_QUOT + key + ESC_QUOT)) {
			return doReplaceJson(json, key, valueMaps).toString();
		}
		return json;
	}
	
	/**
	 * 替换json文本中全部的"key":value的格式的串，将指定的key的value替换为新的value，如果没有匹配规则则返回原值。
	 * 例子："key":originValue 替换为"key":newValue。冒号前后的空格会自动忽略。
	 *
	 * @param json      传入的json
	 * @param key       需要替换的key
	 * @param valueMaps key：原值， value : 新值。
	 * @return 替换后的json
	 */
	public static String replaceJsonWhitOutQuote(String json, String key, Map<String, String>... valueMaps) {
		if (StringUtils.isBlank(json) || StringUtils.isBlank(key) ||
				Objects.isNull(valueMaps) || valueMaps.length == 0) {
			return json;
		}
		if (isJsonString(json) && json.contains(ESC_QUOT + key + ESC_QUOT)) {
			return doReplaceJsonWhitOutQuote(json, key, valueMaps).toString();
		}
		return json;
	}
	
	/**
	 * 替换json文本中全部的\"key\":\"value\"的格式的串，将指定的key的value替换为新的value，如果没有匹配规则则返回原值。
	 * 例子：\"key\":\"originValue\" 替换为\"key\":\"newValue\"。冒号前后的空格会自动忽略。
	 *
	 * @param json      传入的json
	 * @param key       需要替换的key
	 * @param valueMaps key：原值， value : 新值。
	 * @return 替换后的json
	 */
	public static String replaceWarpJson(String json, String key, Map<String, String>... valueMaps) {
		if (StringUtils.isBlank(json) || StringUtils.isBlank(key) ||
				Objects.isNull(valueMaps) || valueMaps.length == 0) {
			return json;
		}
		if (isJsonString(json) && hasWrapQuote(json) && json.contains(ESC_SLASH_QUOT + key + SLASH)) {
			return doReplaceWarpJson(json, key, valueMaps).toString();
		}
		return json;
	}
	
	/**
	 * 替换json文本中全部的\"key\":\"value\"的格式的串，将指定的key的value替换为新的value，如果没有匹配规则则返回原值。
	 * 例子：\"key\":originValue 替换为\"key\":newValue。冒号前后的空格会自动忽略。
	 *
	 * @param json      传入的json
	 * @param key       需要替换的key
	 * @param valueMaps key：原值， value : 新值。
	 * @return 替换后的json
	 */
	// todo 待实现。
	public static String replaceWarpJsonWhitOutQuote(String json, String key, Map<String, String>... valueMaps) {
		if (StringUtils.isBlank(json) || StringUtils.isBlank(key) ||
				Objects.isNull(valueMaps) || valueMaps.length == 0) {
			return json;
		}
		if (isJsonString(json) && hasWrapQuote(json) && json.contains(ESC_SLASH_QUOT + key + SLASH)) {
			return doReplaceWarpJson(json, key, valueMaps).toString();
		}
		return json;
	}
	
	@SafeVarargs
	private static CharSequence doReplaceWarpJson(CharSequence json, String key, Map<String, String>... valueMaps) {
		
		final String regex = "((\\\\+\")" + key + "\\2\\s*:\\s*\\2)" + "(.+?)" + "(\\2)";
		final Matcher matcher = Pattern.compile(regex).matcher(json);
		return doReplace(json, matcher, valueMaps, 3, v -> "$1" + v + "$4");
	}
	
	private static CharSequence doReplaceJson(CharSequence json, String key, Map<String, String>... valueMaps) {
		
		final String regex = "(\"" + key + "\"\\s*:\\s*\")" + "(.+?)" + "((?<!\\\\)\")";
		final Matcher matcher = Pattern.compile(regex).matcher(json);
		return doReplace(json, matcher, valueMaps, 2, v -> "$1" + v + "$3");
	}
	
	private static CharSequence doReplaceJsonWhitOutQuote(CharSequence json, String key,
	                                                      Map<String, String>... valueMaps) {
		final String regex = "(\"" + key + "\"\\s*:\\s*)" + "([^\",\\s]+)" + "((?<!\\\\))";
		final Matcher matcher = Pattern.compile(regex).matcher(json);
		return doReplace(json, matcher, valueMaps, 2, v -> "$1" + v + "$3");
	}
	
	public static String replaceJsonAll(String json, String key, Map<String, String>... valueMaps) {
		if (StringUtils.isBlank(json) || StringUtils.isBlank(key) ||
				Objects.isNull(valueMaps) || valueMaps.length == 0) {
			return json;
		}
		if (isJsonString(json)) {
			CharSequence result = json;
			if (json.contains(ESC_QUOT + key + ESC_QUOT)) {
				result = doReplaceJson(json, key, valueMaps);
			}
			if (hasWrapQuote(json) && json.contains(ESC_SLASH_QUOT + key + SLASH)) {
				result = doReplaceWarpJson(result, key, valueMaps);
			}
			return result.toString();
		}
		return json;
	}
	
	/**
	 * 1. "key":"originValue" 替换为"key":"newValue"。
	 * 2. "key":originValue   替换为"key":newValue。
	 * 3. \"key\":\"originValue\" 替换为\"key\":\"newValue\"。
	 * replaceJsonAll接口中会将上述三种情况替换，但第一种和第二种情况需要在入参中告知，quote：true时第一种情况替换，false第二种情况替换。
	 * 如果不传quote时，默认为第一种情况。
	 *
	 * @param json      传入的json
	 * @param key       需要替换的key
	 * @param quote     替换值是否是字符串
	 * @param valueMaps key：原值， value : 新值。  map数组，会循环遍历每一个map
	 * @return 替换后的json
	 */
	public static String replaceJsonAll(String json, String key, boolean quote, Map<String, String>... valueMaps) {
		if (StringUtils.isBlank(json) || StringUtils.isBlank(key) ||
				Objects.isNull(valueMaps) || valueMaps.length == 0) {
			return json;
		}
		if (isJsonString(json)) {
			CharSequence result = json;
			if (json.contains(ESC_QUOT + key + ESC_QUOT)) {
				if (quote) {
					result = doReplaceJson(json, key, valueMaps);
				} else {
					result = doReplaceJsonWhitOutQuote(json, key, valueMaps);
				}
			}
			if (hasWrapQuote(json) && json.contains(ESC_SLASH_QUOT + key + SLASH)) {
				result = doReplaceWarpJson(result, key, valueMaps);
			}
			return result.toString();
		}
		return json;
	}
	
	private static CharSequence doReplace(CharSequence text, Matcher matcher, Map<String, String>[] valueMaps,
	                                      int valueGroup, Function<String, String> replaceProvider) {
		if (matcher.find()) {
			StringBuffer stringBuffer = new StringBuffer(text.length());
			String value;
			do {
				value = matcher.group(valueGroup);
				for (Map<String, String> map : valueMaps) {
					if (map.containsKey(value)) {
						matcher.appendReplacement(stringBuffer,
								replaceProvider.apply(Matcher.quoteReplacement(map.get(value))));
					}
				}
			} while (matcher.find());
			matcher.appendTail(stringBuffer);
			return stringBuffer;
		}
		return text;
	}
	
	/**
	 * 简单判断一下是否是json对象字符串
	 *
	 * @param json
	 * @return
	 */
	public static boolean isJsonString(String json) {
		
		return json.indexOf('{') != -1 && json.indexOf('"') != -1;
	}
	
	/**
	 * 是否包含转义引号 \"
	 *
	 * @param json
	 * @return
	 */
	public static boolean hasWrapQuote(String json) {
		return json.contains("\\\"");
	}


	/**
	 * 根据键查找JSON字符串中的值。
	 * <p>
	 * 此方法旨在解析JSON字符串并提取给定键对应的值。它处理了键值对可能包含转义字符或在字符串中嵌套的情况。
	 *
	 * @param json 待搜索的JSON字符串。
	 * @param key 要查找的键。
	 * @return 键对应的值，如果找不到或输入无效，则返回空set
	 */
	public static Set<String> findValueByKey(String json, String key) {
		Set<String> result = new HashSet<>();
	    // 检查输入的JSON字符串或键是否为空，如果是，则直接返回null。
	    if (StringUtils.isBlank(json) || StringUtils.isBlank(key)) {
	        return Collections.emptySet();
	    }

	    // 检查输入字符串是否为有效的JSON字符串。
	    if (isJsonString(json)) {

	        // 如果JSON字符串包含转义的引号包裹的键，尝试使用正则表达式匹配并返回键对应的值。
	        if (json.contains(ESC_QUOT + key + ESC_QUOT)) {
				// 定义正则表达式，用于匹配键值对，其中键为给定的key。
				final String regex = "(\"" + key + "\"\\s*:\\s*\")" + "(.+?)" + "((?<!\\\\)\")";
	            final Matcher matcher = Pattern.compile(regex).matcher(json);
				if (matcher.find()) {
					do {
						result.add(matcher.group(2));
					} while (matcher.find());
				}
	        }

	        // 如果JSON字符串包含带转义斜杠的键，这里预留了处理逻辑的代码位，但当前未实现。
	        if (hasWrapQuote(json) && json.contains(ESC_SLASH_QUOT + key + SLASH)) {
				final String regex = "((\\\\+\")" + key + "\\2\\s*:\\s*\\2)" + "(.+?)" + "(\\2)";
				final Matcher matcher = Pattern.compile(regex).matcher(json);
				if (matcher.find()) {
					do {
						result.add(matcher.group(3));
					} while (matcher.find());
				}
	        }

	    }

	    return result;
	}
}
