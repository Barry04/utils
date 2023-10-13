package com.suncy.utils;

public class StringUtil {
	
	/**
	 * 将下划线命名转换为驼峰命名
	 *
	 * @param str 下划线命名的字符串
	 * @return 驼峰命名的字符串
	 */
	public static String parseUnderLineToCamelCase(String str) {
		StringBuffer camelCaseString = new StringBuffer();
		boolean toUpperCase = false;
		
		for (char c : str.toCharArray()) {
			if (c == '_') {
				toUpperCase = true;
			} else {
				if (toUpperCase) {
					camelCaseString.append(Character.toUpperCase(c));
					toUpperCase = false;
				} else {
					camelCaseString.append(c);
				}
			}
		}
		
		return camelCaseString.toString();
	}
	
	
	/**
	 * 将驼峰命名转换为下划线命名
	 *
	 * @param camelCaseString 驼峰命名的字符串
	 * @return 下划线命名的字符串
	 */
	static String parseCameCaseStr(String camelCaseString) {
		// 用于构建下划线命名的字符串
		StringBuffer underscoreString = new StringBuffer();
		
		// 遍历输入字符串的每个字符
		for (char c : camelCaseString.toCharArray()) {
			if (Character.isUpperCase(c)) {
				// 遇到大写字母，在其前面添加下划线并转换为小写
				underscoreString.append('_').append(Character.toLowerCase(c));
			} else {
				// 小写字母直接追加到结果字符串
				underscoreString.append(c);
			}
		}
		
		// 返回最终的下划线命名字符串
		return underscoreString.toString();
	}
	
	
	/**
	 * 将字符串的首字母大写
	 *
	 * @param inputString 输入字符串
	 * @return 首字母大写的字符串
	 */
	static String capitalizeFirstLetter(String inputString) {
		if (inputString == null || inputString.isEmpty()) {
			// 处理空字符串或null的情况
			return inputString;
		}
		
		// 将字符串的第一个字符转换为大写
		char firstChar = Character.toUpperCase(inputString.charAt(0));
		
		// 构建新的字符串，将第一个字符替换为大写后的字符，并保留剩余部分
		return firstChar + inputString.substring(1);
	}
}
