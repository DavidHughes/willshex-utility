//
//  JsonUtils.java
//  com.willshex.utility
//
//  Created by William Shakour on August 16, 2011.
//  Copyright © 2011 WillShex Limited. All rights reserved.
//
package com.willshex.utility;

import java.util.ArrayList;
import java.util.List;

public final class JsonUtils {

	/**
	 * Clean Json method will remove null properties, empty collections, empty dictionaries/types
	 * 
	 * @param json
	 * @return
	 */
	public static String cleanJson (String json) {
		return cleanJson(json, true);
	}

	private static String cleanJson (String json, boolean stripStrings) {
		List<String> values = new ArrayList<String>();

		String stripped = json;

		if (stripStrings) {
			stripped = strip(json, values);
		}

		String cleaned = stripped;
		do {
			stripped = cleaned;
			cleaned = cleaned.replaceAll("\"[a-zA-z]+[a-zA-Z0-9]*\":null", "");
			cleaned = cleaned.replaceAll(",,", ",");
			cleaned = cleaned.replaceAll(", ", ",");
			cleaned = cleaned.replaceAll("\\{,", "{");
			cleaned = cleaned.replaceAll(",\\}", "}");
			cleaned = cleaned.replaceAll("\\[,", "[");
			cleaned = cleaned.replaceAll(",\\]", "]");
			cleaned = cleaned.replaceAll(":\\{\\}", ":null");
			cleaned = cleaned.replaceAll(":\\{ \\}", ":null");
			cleaned = cleaned.replaceAll(":\\[\\]", ":[]");
			cleaned = cleaned.replaceAll(":\\[ \\]", ":{}");
			cleaned = cleaned.replaceAll("\\{\\}", "");
			cleaned = cleaned.replaceAll("\\[\\]", "");
			cleaned = cleaned.replaceAll(":,", ":null,");
			cleaned = cleaned.replaceAll(":\\}", ":null}");
		} while (!stripped.equals(cleaned));

		cleaned = putBack(cleaned, values);

		if ("".equals(cleaned) || "{}".equals(cleaned)
				|| "[]".equals(cleaned)) {
			cleaned = "null";
		}

		return cleaned;
	}

	private static String strip (String json, List<String> values) {
		if (values == null) return json;

		StringBuffer stripped = new StringBuffer(json.length());

		boolean inStringValue = false;
		boolean escaped = false;
		int start = 0;
		char c;
		for (int i = 0; i < json.length(); i++) {
			c = json.charAt(i);

			switch (c) {
			case '\"':
				if (inStringValue && !escaped) {
					inStringValue = false;
					stripped.append("*");
					values.add(json.substring(start, i + 1));
				} else if (!inStringValue) {
					inStringValue = true;
					start = i;
				}

				escaped = false;
				break;
			case ' ':
				escaped = false;
				break;
			case ':':
				if (!inStringValue) {
					if (stripped
							.substring(stripped.length() - 1, stripped.length())
							.equals("*")) {
						stripped.replace(stripped.length() - 1,
								stripped.length(),
								values.get(values.size() - 1));
						values.remove(values.size() - 1);
					}

					stripped.append(c);
				}

				escaped = false;
				break;
			case '\\':
				if (inStringValue) {
					escaped = true;
				}
				break;
			default:
				if (!inStringValue) {
					stripped.append(c);
				}
				escaped = false;
				break;
			}

		}

		return stripped.toString();
	}

	private static String putBack (String stripped, List<String> values) {
		StringBuilder putBack = null;

		if (values.size() > 0) {
			putBack = new StringBuilder();
			char c;
			int replaced = 0;
			for (int i = 0; i < stripped.length(); i++) {
				c = stripped.charAt(i);

				if (c == '*') {
					putBack.append(values.get(replaced++));
				} else {
					putBack.append(c);
				}
			}
		}

		return putBack == null ? stripped : putBack.toString();
	}

	/**
	 * Beautify json string indenting with 4 spaces and adding line ends with \n
	 * @param json ugly gson
	 * @return processed json
	 */
	public static String beautifyJson (String json) {
		return beautifyJson(json, "    ", "\n");
	}

	/**
	 * Beautify json string indenting
	 * @param json ugly gson
	 * @param level string to indet with
	 * @param line line end string
	 * @return processed json
	 */
	public static String beautifyJson (String json, String level, String line) {
		List<String> values = new ArrayList<String>();
		String stripped = strip(json, values);

		int length = stripped.length();
		char current;
		String indent = "";
		boolean newLineAfter = true, newLineBefore = false;

		StringBuffer beautifulJson = new StringBuffer();
		for (int i = 0; i < length; i++) {
			current = stripped.charAt(i);

			if (current == '[' || current == '{') {
				indent += level;
				newLineAfter = true;
			} else if (current == ']' || current == '}') {
				indent = indent.substring(0, indent.length() - level.length());
				newLineBefore = true;
			} else if (current == ',') {
				newLineAfter = true;
			}

			if (newLineBefore) {
				beautifulJson.append(line);
				beautifulJson.append(indent);
				newLineBefore = false;
			}

			beautifulJson.append(current);

			if (newLineAfter) {
				beautifulJson.append(line);
				beautifulJson.append(indent);
				newLineAfter = false;
			}
		}

		return putBack(beautifulJson.toString(), values);
	}

	public static String uglifyJson (String json) {
		List<String> values = new ArrayList<String>();
		String stripped = strip(json, values);

		int length = stripped.length();
		char current;

		StringBuffer uglyJson = new StringBuffer();
		for (int i = 0; i < length; i++) {
			current = stripped.charAt(i);

			if (current != ' ' && current != '\r' && current != '\n') {
				uglyJson.append(current);
			}
		}

		return putBack(uglyJson.toString(), values);
	}
}