package com.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtilities extends StringUtils {
	public static String lpad(String src, int size, char padChar) {
		return leftPad(src, size, padChar);
	}

	public static String lpad(String src, int size, String padStr) {
		return leftPad(src, size, padStr);
	}

	public static String rpad(String src, int size, char padChar) {
		return rightPad(src, size, padChar);
	}

	public static String rpad(String src, int size, String padStr) {
		return rightPad(src, size, padStr);
	}
}
