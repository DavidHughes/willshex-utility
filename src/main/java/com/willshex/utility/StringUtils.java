//
//  StringUtils.java
//  com.willshex.utility
//
//  Created by William Shakour on Jun 14, 2012.
//  Copyright © 2012 WillShex Limited. All rights reserved.
//
package com.willshex.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author William Shakour
 * 
 */
public class StringUtils {

	private static final String[] ESCAPE_CHARS = { " ", "{", "}", ";", "/", "?",
			":", "@", "&", "=", "+", "$", ",", "[", "]", "#", "!", "'", "(",
			")", "*", "\"", "<", ">" };

	private static final String[] REPLACE_CHARS = { "%20", "%7B", "%7D", "%3B",
			"%2F", "%3F", "%3A", "%40", "%26", "%3D", "%2B", "%24", "%2C",
			"%5B", "%5D", "%23", "%21", "%27", "%28", "%29", "%2A", "%22",
			"%3C", "%3E" };

	public static String sanitise (String value) {
		return value;
		// value.replace("<", "").replace(">", "");
	}

	/**
	 * Strip slashes from string specifically for un-escaping slashes (\) and single quotes (')
	 * @param value string to strip
	 * @return striped string
	 */
	public static String stripslashes (String value) {
		return value == null ? null
				: value.replace("\\'", "'").replace("\\\"", "\"")
						.replace("\\\\", "\\");
	}

	/**
	 * Strip slashes from string specifically for escaping slashes (\) and single quotes (')
	 * @param value string to escape
	 * @return escaped string
	 */
	public static String addslashes (String value) {
		return value == null ? null
				: value.replace("\\", "\\\\").replace("\"", "\\\"").replace("'",
						"\\'");
	}

	/**
	 * Url decode replaces codes "%20", "%7B", "%7D", "%3B",
			"%2F", "%3F", "%3A", "%40", "%26", "%3D", "%2B", "%24", "%2C",
			"%5B", "%5D", "%23", "%21", "%27", "%28", "%29", "%2A", "%22",
			"%3C", "%3E" with " ", "{", "}", ";", "/", "?",
			":", "@", "&", "=", "+", "$", ",", "[", "]", "#", "!", "'", "(",
			")", "*", "\"", "<", ">"
	 * @param value string to decode
	 * @return decoded string
	 */
	public static String urldecode (String value) {
		StringBuffer replaced = new StringBuffer(value);

		int i, start;
		for (i = 0; i < REPLACE_CHARS.length; i++) {
			while ((start = replaced.indexOf(REPLACE_CHARS[i])) >= 0) {
				replaced.replace(start, start + REPLACE_CHARS[i].length(),
						ESCAPE_CHARS[i]);
			}
		}

		return replaced.toString();
	}

	/**
	 * Url encode replaces " ", "{", "}", ";", "/", "?",
			":", "@", "&", "=", "+", "$", ",", "[", "]", "#", "!", "'", "(",
			")", "*", "\"", "<", ">" with codes "%20", "%7B", "%7D", "%3B",
			"%2F", "%3F", "%3A", "%40", "%26", "%3D", "%2B", "%24", "%2C",
			"%5B", "%5D", "%23", "%21", "%27", "%28", "%29", "%2A", "%22",
			"%3C", "%3E"
	 * @param value string to encode
	 * @return encoded string
	 */
	public static String urlencode (String value) {
		StringBuffer replaced = new StringBuffer(value);

		int i, start;
		for (i = 0; i < ESCAPE_CHARS.length; i++) {
			while ((start = replaced.indexOf(ESCAPE_CHARS[i])) >= 0) {
				replaced.replace(start, start + ESCAPE_CHARS[i].length(),
						REPLACE_CHARS[i]);
			}
		}

		return replaced.toString();
	}

	public static String sha1Hash (String content) {
		byte[] bytesOfMessage = null;
		byte[] theDigest = null;

		try {
			bytesOfMessage = content.getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("SHA-1");
			theDigest = md.digest(bytesOfMessage);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return theDigest == null ? null : convertDigestToString(theDigest);
	}

	public static String md5Hash (String content) {
		byte[] bytesOfMessage = null;
		byte[] theDigest = null;

		try {
			bytesOfMessage = content.getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("MD5");
			theDigest = md.digest(bytesOfMessage);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return theDigest == null ? null : convertDigestToString(theDigest);
	}

	private static String convertDigestToString (byte[] digest) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < digest.length; i++) {
			result.append(Integer.toString((digest[i] & 0xff) + 0x100, 16)
					.substring(1));
		}

		return result.toString();
	}

	public static String join (Iterable<? extends CharSequence> items,
			CharSequence glue) {
		StringBuffer buffer = new StringBuffer();

		for (CharSequence item : items) {
			if (buffer.length() != 0) {
				buffer.append(glue);
			}

			buffer.append(item);
		}

		return buffer.toString();
	}

	public static String join (Iterable<? extends CharSequence> items) {
		return join(items, ",");
	}

	private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			LOWER = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * Replace every alpha character in a string with the character 13 over  
	 * @param value string to rotate
	 * @return rotated result
	 */
	public static String rot13 (String value) {
		StringBuffer buffer = new StringBuffer(value.length());

		int count = value.length();
		int index;
		char c;
		for (int i = 0; i < count; i++) {
			c = value.charAt(i);

			if ((index = LOWER.indexOf(c)) >= 0) {
				buffer.append(LOWER.charAt((index + 13) % 26));
			} else if ((index = UPPER.indexOf(c)) >= 0) {
				buffer.append(UPPER.charAt((index + 13) % 26));
			} else {
				buffer.append(c);
			}
		}

		return buffer.toString();
	}

	/**
	 * Make the first character of the string upper-case
	 * @param value string to process
	 * @return string with upper-case first letter
	 */
	public static String upperCaseFirstLetter (String value) {
		String upperCaseFirstLetter = null;

		String firstLetter = value.substring(0, 1);
		upperCaseFirstLetter = value.replaceFirst(firstLetter,
				firstLetter.toUpperCase());

		return upperCaseFirstLetter;
	}

	/**
	 * Make the first character of the string lower-case
	 * @param value string to process
	 * @return string with lower-case first letter
	 */
	public static String lowerCaseFirstLetter (String value) {
		String lowerCaseFirstLetter = null;

		String firstLetter = value.substring(0, 1);
		lowerCaseFirstLetter = value.replaceFirst(firstLetter,
				firstLetter.toLowerCase());

		return lowerCaseFirstLetter;
	}

	private static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyz0123456789";

	/**
	 * Restricts allowed characters in string to allowed characters
	 * @param value value to be restricted
	 * @param allowed a string of all the allowed characters
	 * @param replacement the character used to replace a disallowed character
	 * @param maxLength a cap for the maximum characters processed from the value
	 * @return process/restricted string
	 */
	public static String restrict (String value, String allowed,
			String replacement, int maxLength) {
		StringBuffer slug = new StringBuffer();

		if (value != null && value.length() > 0) {
			value = value.toLowerCase();

			int size = Math.min(value.length(), maxLength);
			char c;
			boolean replacedOne = false;
			for (int i = 0; i < size; i++) {
				c = value.charAt(i);

				if (allowed.contains(Character.toString(c))) {
					slug.append(c);
					replacedOne = false;
				} else if (!replacedOne) {
					slug.append(replacement);
					replacedOne = true;
				}
			}
		}

		return slug.toString();
	}

	/**
	 * Returns up to 100 character string restricted to abcdefghijklmnopqrstuvwxyz0123456789 with disallowed spaces replaced with -
	 * @param value value to be restricted
	 * @return process/restricted string
	 */
	public static String restrict (String value) {
		return restrict(value, ALLOWED_CHARS, "-", 100);
	}
}
