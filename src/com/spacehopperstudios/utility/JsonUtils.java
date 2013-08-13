//
//  JsonUtils.java
//  Scores
//
//  Created by William Shakour on August 16, 2011.
//  Copyright © 2011 WillShex Ltd. All rights reserved.
//  Copyright © 2012 SPACEHOPPER STUDIOS LTD. All rights reserved.
//
package com.spacehopperstudios.utility;

import java.util.ArrayList;
import java.util.List;

public final class JsonUtils {

	/**
	 * Clean Json method will remove null properties, empty collections, empty
	 * dictionaries/types
	 * 
	 * @param json
	 * @return
	 */
	public static String cleanJson(String json) {
		List<String> values = new ArrayList<String>();
		String cleaned = strip(json, values);

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

		if (values.size() > 0) {
			cleaned = putBack(cleaned, values);
		}

		if (!json.equals(cleaned)) {
			cleaned = JsonUtils.cleanJson(cleaned);
		}

		if ("".equals(cleaned) || "{}".equals(cleaned) || "[]".equals(cleaned)) {
			cleaned = "null";
		}

		return cleaned;
	}

	private static String strip(String json, List<String> values) {
		if (values == null)
			return json;

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
					if (stripped.substring(stripped.length() - 1, stripped.length()).equals("*")) {
						stripped.replace(stripped.length() - 1, stripped.length(), values.get(values.size() - 1));
						values.remove(values.size() - 1);
					}
				}

				stripped.append(c);
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

	private static String putBack(String stripped, List<String> values) {
		StringBuilder putBack = new StringBuilder();

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

		return putBack.toString();
	}
}