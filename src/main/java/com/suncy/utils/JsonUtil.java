package com.suncy.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class JsonUtil {
	
	private static final String ESC_COMMA = "\"";
	private static final String ESC_SLASH_COMMA = "\\\"";
	
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
		if (isJsonString(json) && json.contains(ESC_COMMA + key + ESC_COMMA)) {
			return doReplaceJson(json, key, valueMaps).toString();
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
		if (isJsonString(json) && hasWrapComma(json) && json.contains(ESC_SLASH_COMMA + key + SLASH)) {
			return doReplaceWarpJson(json, key, valueMaps).toString();
		}
		return json;
	}
	
	@SafeVarargs
	private static CharSequence doReplaceWarpJson(CharSequence json, String key, Map<String, String>... valueMaps) {
		
		final String regex = "(\\\\+\"" + key + "\\2\\s*:\\s*\\2)" + "(.+?)" + "(\\2)";
		final Matcher matcher = Pattern.compile(regex).matcher(json);
		return doReplace(json, matcher, valueMaps, 3, v -> "$1" + v + "$4");
	}
	
	private static CharSequence doReplaceJson(CharSequence json, String key, Map<String, String>... valueMaps) {
		
		final String regex = "(\"" + key + "\"\\s*:\\s*\")" + "(.+?)" + "((?<!\\\\)\")";
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
			if (json.contains(ESC_COMMA + key + ESC_COMMA)) {
				result = doReplaceJson(json, key, valueMaps);
			}
			if (hasWrapComma(json) && json.contains(ESC_SLASH_COMMA + key + SLASH)) {
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
	public static boolean hasWrapComma(String json) {
		return json.contains("\\\"");
	}
}
