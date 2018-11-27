//
//  StringUtils.java
//  com.willshex.utility
//
//  Created by William Shakour on Jun 14, 2012.
//  Copyright © 2012 WillShex Limited. All rights reserved.
//
package com.willshex.utility;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author William Shakour
 * 
 */
public class StringUtils {

	private static final String[] ESCAPE_CHARS = { "%", " ", "{", "}", ";", "/",
			"?", ":", "@", "&", "=", "+", "$", ",", "[", "]", "#", "!", "'",
			"(", ")", "*", "\"", "<", ">", "\n", "\r", "\t", "~", " ", "`", "",
			"‚", "ƒ", "„", "…", "†", "‡", "ˆ", "‰", "Š", "‹", "Œ", "", "Ž",
			"", "", "‘", "’", "“", "”", "•", "–", "—", "˜", "™", "š", "›",
			"œ", "", "ž", "Ÿ", " ", "¡", "¢", "£", "¤", "¥", "¦", "§", "¨",
			"©", "ª", "«", "¬", "\u00AD", "®", "¯", "°", "±", "²", "³", "´",
			"µ", "¶", "·", "¸", "¹", "º", "»", "¼", "½", "¾", "¿", "À", "Á",
			"Â", "Ã", "Ä", "Å", "Æ", "Ç", "È", "É", "Ê", "Ë", "Ì", "Í", "Î",
			"Ï", "Ð", "Ñ", "Ò", "Ó", "Ô", "Õ", "Ö", "×", "Ø", "Ù", "Ú", "Û",
			"Ü", "Ý", "Þ", "ß", "à", "á", "â", "ã", "ä", "å", "æ", "ç", "è",
			"é", "ê", "ë", "ì", "í", "î", "ï", "ð", "ñ", "ò", "ó", "ô", "õ",
			"ö", "÷", "ø", "ù", "ú", "û", "ü", "ý", "þ", "ÿ" };

	private static final String[] REPLACE_CHARS = { "%25", "%20", "%7B", "%7D",
			"%3B", "%2F", "%3F", "%3A", "%40", "%26", "%3D", "%2B", "%24",
			"%2C", "%5B", "%5D", "%23", "%21", "%27", "%28", "%29", "%2A",
			"%22", "%3C", "%3E", "%0A", "%0D", "%09", "%7E", "%7F", "%E2%82%AC",
			"%81", "%E2%80%9A", "%C6%92", "%E2%80%9E", "%E2%80%A6", "%E2%80%A0",
			"%E2%80%A1", "%CB%86", "%E2%80%B0", "%C5%A0", "%E2%80%B9", "%C5%92",
			"%C5%8D", "%C5%BD", "%8F", "%C2%90", "%E2%80%98", "%E2%80%99",
			"%E2%80%9C", "%E2%80%9D", "%E2%80%A2", "%E2%80%93", "%E2%80%94",
			"%CB%9C", "%E2%84", "%C5%A1", "%E2%80", "%C5%93", "%9D", "%C5%BE",
			"%C5%B8", "%C2%A0", "%C2%A1", "%C2%A2", "%C2%A3", "%C2%A4",
			"%C2%A5", "%C2%A6", "%C2%A7", "%C2%A8", "%C2%A9", "%C2%AA",
			"%C2%AB", "%C2%AC", "%C2%AC", "%C2%AE", "%C2%AF", "%C2%B0",
			"%C2%B1", "%C2%B2", "%C2%B3", "%C2%B4", "%C2%B5", "%C2%B6",
			"%C2%B7", "%C2%B8", "%C2%B9", "%C2%BA", "%C2%BB", "%C2%BC",
			"%C2%BD", "%C2%BE", "%C2%BF", "%C3%80", "%C3%81", "%C3%82",
			"%C3%83", "%C3%84", "%C3%85", "%C3%86", "%C3%87", "%C3%88",
			"%C3%89", "%C3%8A", "%C3%8B", "%C3%8C", "%C3%8D", "%C3%8E",
			"%C3%8F", "%C3%90", "%C3%91", "%C3%92", "%C3%93", "%C3%94",
			"%C3%95", "%C3%96", "%C3%97", "%C3%98", "%C3%99", "%C3%9A",
			"%C3%9B", "%C3%9C", "%C3%9D", "%C3%9E", "%C3%9F", "%C3%A0",
			"%C3%A1", "%C3%A2", "%C3%A3", "%C3%A4", "%C3%A5", "%C3%A6",
			"%C3%A7", "%C3%A8", "%C3%A9", "%C3%AA", "%C3%AB", "%C3%AC",
			"%C3%AD", "%C3%AE", "%C3%AF", "%C3%B0", "%C3%B1", "%C3%B2",
			"%C3%B3", "%C3%B4", "%C3%B5", "%C3%B6", "%C3%B7", "%C3%B8",
			"%C3%B9", "%C3%BA", "%C3%BB", "%C3%BC", "%C3%BD", "%C3%BE",
			"%C3%BF" };

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

		int i, start = 0;
		for (i = 0; i < REPLACE_CHARS.length; i++) {
			while ((start = replaced.indexOf(REPLACE_CHARS[i], start)) >= 0) {
				replaced.replace(start, start + REPLACE_CHARS[i].length(),
						ESCAPE_CHARS[i]);
				start += ESCAPE_CHARS[i].length();
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

		int i, start = 0;
		for (i = 0; i < ESCAPE_CHARS.length; i++) {
			while ((start = replaced.indexOf(ESCAPE_CHARS[i], start)) >= 0) {
				replaced.replace(start, start + ESCAPE_CHARS[i].length(),
						REPLACE_CHARS[i]);
				start += REPLACE_CHARS[i].length();
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
		StringBuffer restricted = new StringBuffer();

		if (value != null && value.length() > 0) {
			value = value.toLowerCase();

			int size = Math.min(value.length(), maxLength);
			char c;
			boolean replacedOne = false;
			for (int i = 0; i < size; i++) {
				c = value.charAt(i);

				if (allowed.contains(Character.toString(c))) {
					restricted.append(c);
					replacedOne = false;
				} else if (!replacedOne) {
					restricted.append(replacement);
					replacedOne = true;
				}
			}
		}

		return restricted.toString();
	}

	/**
	 * Returns up to 100 character string restricted to abcdefghijklmnopqrstuvwxyz0123456789 with disallowed spaces replaced with -
	 * @param value value to be restricted
	 * @return process/restricted string
	 */
	public static String restrict (String value) {
		return restrict(value, ALLOWED_CHARS, "-", 100);
	}

	private static final String NUMBERS = "0123456789";

	private static final String CAMEL_PASCAL_ALLOWED = UPPER + LOWER + NUMBERS;

	public static String camelCase (String value) {
		StringBuffer restricted = new StringBuffer();

		if (value != null && value.length() > 0) {
			int size = value.length();
			boolean replacedOne = false;
			boolean foundOne = false;
			String characterAsString;
			for (int i = 0; i < size; i++) {
				characterAsString = Character.toString(value.charAt(i));

				if (CAMEL_PASCAL_ALLOWED.contains(characterAsString)) {
					if (LOWER.contains(characterAsString)) {
						if (foundOne) {
							if (replacedOne) {
								restricted.append(
										characterAsString.toUpperCase());
							} else {
								restricted.append(characterAsString);
							}
						} else {
							restricted.append(characterAsString);
						}

						replacedOne = false;
						foundOne = true;
					} else if (UPPER.contains(characterAsString)) {
						if (foundOne) {
							restricted.append(characterAsString);
						} else {
							restricted.append(characterAsString.toLowerCase());
						}

						replacedOne = false;
						foundOne = true;
					} else if (foundOne) {
						// must be a number
						restricted.append(characterAsString);
						replacedOne = false;
					}
				} else if (foundOne && !replacedOne) {
					replacedOne = true;
				}
			}
		}

		return restricted.toString();
	}

	public static String pascalCase (String value) {
		StringBuffer restricted = new StringBuffer();

		if (value != null && value.length() > 0) {
			int size = value.length();
			boolean replacedOne = false;
			boolean foundOne = false;
			String characterAsString;
			for (int i = 0; i < size; i++) {
				characterAsString = Character.toString(value.charAt(i));

				if (CAMEL_PASCAL_ALLOWED.contains(characterAsString)) {
					if (LOWER.contains(characterAsString)) {
						if (foundOne) {
							if (replacedOne) {
								restricted.append(
										characterAsString.toUpperCase());
							} else {
								restricted.append(characterAsString);
							}
						} else {
							restricted.append(characterAsString.toUpperCase());
						}

						replacedOne = false;
						foundOne = true;
					} else if (UPPER.contains(characterAsString)) {
						restricted.append(characterAsString);

						replacedOne = false;
						foundOne = true;
					} else if (foundOne) {
						// must be a number
						restricted.append(characterAsString);
						replacedOne = false;
					}
				} else if (foundOne && !replacedOne) {
					replacedOne = true;
				}
			}
		}

		return restricted.toString();
	}

	public static String expandByCase (String value, boolean capitalFirst,
			boolean capitalAfterSpace, String space, String append) {
		StringBuffer expanded = new StringBuffer();
		if (value != null && value.length() > 0) {
			int size = value.length();
			boolean inNumbers = false, isNumber = false, addSpace = false;
			String characterAsString;
			for (int i = 0; i < size; i++) {
				characterAsString = Character.toString(value.charAt(i));
				addSpace = false;
				isNumber = NUMBERS.contains(characterAsString);

				if (inNumbers) {
					if (!isNumber) {
						addSpace = true;
						inNumbers = false;
					}
				} else {
					if (isNumber) {
						addSpace = true;
						inNumbers = true;
					}
				}

				if (i == 0) {
					if (capitalFirst) {
						expanded.append(characterAsString.toUpperCase());
					} else {
						expanded.append(characterAsString.toLowerCase());
					}
				} else {
					if (!addSpace && UPPER.contains(characterAsString)) {
						addSpace = true;
					}

					if (addSpace) {
						expanded.append(space);
					}

					if (addSpace && capitalAfterSpace) {
						expanded.append(characterAsString.toUpperCase());
					} else {
						if (!isNumber) {
							expanded.append(characterAsString.toLowerCase());
						} else {
							expanded.append(characterAsString);
						}
					}
				}
			}

			expanded.append(append);
		}

		return expanded.toString();

	}

	public static String constantName (String value, String prefix,
			String suffix) {
		StringBuffer constant = new StringBuffer();

		constant.append(prefix);

		boolean addedPrefixSeparator = prefix == null || prefix.length() == 0;

		if (value != null && value.length() > 0) {
			int size = value.length();
			boolean replacedOne = false;
			boolean foundOne = false;
			String characterAsString;
			for (int i = 0; i < size; i++) {
				characterAsString = Character.toString(value.charAt(i));

				if (CAMEL_PASCAL_ALLOWED.contains(characterAsString)) {
					if (!NUMBERS.contains(characterAsString)) {
						if (!addedPrefixSeparator) {
							constant.append("_");
							addedPrefixSeparator = true;
						}

						constant.append(characterAsString.toUpperCase());

						replacedOne = false;
						foundOne = true;
					} else if (foundOne) {
						if (!addedPrefixSeparator) {
							constant.append("_");
							addedPrefixSeparator = true;
						}

						constant.append(characterAsString);
						replacedOne = false;
					}
				} else if (foundOne && !replacedOne) {
					constant.append("_");
					replacedOne = true;
				}
			}
		}

		if (constant.length() > 0
				&& constant.charAt(constant.length() - 1) != '_'
				&& suffix.length() > 0) {
			constant.append("_");
		}

		constant.append(suffix);

		return constant.toString();
	}

	// from http://stackoverflow.com/questions/392464/how-do-i-do-base64-encoding-on-iphone-sdk/800976#800976

	static char[] base64EncodingTable = new char[] { 'A', 'B', 'C', 'D', 'E',
			'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9', '+', '/' };

	public static String base64 (byte[] raw) {
		int ixtext, lentext;
		int ctremaining;
		byte[] input = new byte[3], output = new byte[4];
		short i, charsonline = 0, ctcopy;
		StringBuffer result;

		lentext = raw.length;

		if (lentext < 1) { return ""; }

		result = new StringBuffer(lentext);

		ixtext = 0;

		while (true) {
			ctremaining = lentext - ixtext;

			if (ctremaining <= 0) {
				break;
			}

			for (i = 0; i < 3; i++) {
				int ix = ixtext + i;

				if (ix < lentext) {
					input[i] = raw[ix];
				} else {
					input[i] = 0;
				}
			}

			output[0] = (byte) ((input[0] & 0xFC) >> 2);
			output[1] = (byte) (((input[0] & 0x03) << 4)
					| ((input[1] & 0xF0) >> 4));
			output[2] = (byte) (((input[1] & 0x0F) << 2)
					| ((input[2] & 0xC0) >> 6));
			output[3] = (byte) (input[2] & 0x3F);

			ctcopy = 4;

			switch (ctremaining) {
			case 1:
				ctcopy = 2;
				break;
			case 2:
				ctcopy = 3;
				break;
			}

			for (i = 0; i < ctcopy; i++) {
				result.append(base64EncodingTable[output[i]]);
			}

			for (i = ctcopy; i < 4; i++) {
				result.append("=");
			}

			ixtext += 3;
			charsonline += 4;

			if ((ixtext % 90) == 0) {
				result.append("\n");
			}

			if (raw.length > 0) {
				if (charsonline >= raw.length) {
					charsonline = 0;

					result.append("\n");
				}
			}
		}

		return result.toString();
	}

	public static byte[] unbase64 (String string) {
		int ixtext, lentext;
		byte ch;
		byte[] input = new byte[4], output = new byte[3];
		short i, ixinput;
		boolean flignore, flendtext = false;

		ByteArrayOutputStream result;

		if (string == null) { return new byte[0]; }

		ixtext = 0;

		lentext = string.length();

		result = new ByteArrayOutputStream(lentext);

		ixinput = 0;

		while (true) {
			if (ixtext >= lentext) {
				break;
			}

			ch = (byte) string.charAt(ixtext++);

			flignore = false;

			if ((ch >= 'A') && (ch <= 'Z')) {
				ch = (byte) (ch - 'A');
			} else if ((ch >= 'a') && (ch <= 'z')) {
				ch = (byte) (ch - 'a' + 26);
			} else if ((ch >= '0') && (ch <= '9')) {
				ch = (byte) (ch - '0' + 52);
			} else if (ch == '+') {
				ch = 62;
			} else if (ch == '=') {
				flendtext = true;
			} else if (ch == '/') {
				ch = 63;
			} else {
				flignore = true;
			}

			if (!flignore) {
				short ctcharsinput = 3;
				Boolean flbreak = false;

				if (flendtext) {
					if (ixinput == 0) {
						break;
					}

					if ((ixinput == 1) || (ixinput == 2)) {
						ctcharsinput = 1;
					} else {
						ctcharsinput = 2;
					}

					ixinput = 3;

					flbreak = true;
				}

				input[ixinput++] = ch;

				if (ixinput == 4) {
					ixinput = 0;

					byte char0 = input[0];
					byte char1 = input[1];
					byte char2 = input[2];
					byte char3 = input[3];

					output[0] = (byte) ((char0 << 2) | ((char1 & 0x30) >> 4));
					output[1] = (byte) (((char1 & 0x0F) << 4)
							| ((char2 & 0x3C) >> 2));
					output[2] = (byte) (((char2 & 0x03) << 6) | (char3 & 0x3F));

					for (i = 0; i < ctcharsinput; i++) {
						result.write(output, i, 1);
					}
				}

				if (flbreak) {
					break;
				}
			}
		}

		return result.toByteArray();
	}

	/**
	 * Returns a string of string repeated count times
	 * @param string String to repeat. 
	 * If a negative value is used the result will always be an empty string.
	 * @param count The number of times to repeat the string 
	 * @return 
	 */
	public static String repeat (String string, int count) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < count; i++) {
			buffer.append(string);
		}

		return buffer.toString();
	}

	public static Collection<String> longestCommonParts (String lhs,
			String rhs) {
		int[][] table = new int[lhs.length()][rhs.length()];
		int longest = 0;
		Set<String> result = new HashSet<>();

		for (int i = 0; i < lhs.length(); i++) {
			for (int j = 0; j < rhs.length(); j++) {
				if (lhs.charAt(i) == rhs.charAt(j)) {
					table[i][j] = (i == 0 || j == 0) ? 1
							: 1 + table[i - 1][j - 1];
					if (table[i][j] > longest) {
						longest = table[i][j];
						result.clear();
					}

					if (table[i][j] == longest) {
						result.add(lhs.substring(i - longest + 1, i + 1));
					}
				}
			}
		}

		return result;
	}

	public static String commonPrefix (String lhs, String rhs) {
		final int count = Math.min(lhs.length(), rhs.length());
		int index = 0;
		for (int i = 0; i < count; i++) {
			if (lhs.charAt(i) != rhs.charAt(i)) {
				break;
			}

			index = i;
		}

		return index == 0 ? "" : lhs.substring(0, index + 1);
	}

	public static String trim (String string, char c) {
		String trimmed = string;

		if (!(string == null || string.isEmpty())) {
			int start = 0;
			for (int i = start; i < string.length(); i++) {
				start = i;
				if (string.charAt(i) != c) {
					break;
				}
			}

			int end = string.length();
			if (start < (end - 1)) {
				for (int i = end; i > 0; i--) {
					end = i;
					if (string.charAt(i - 1) != c) {
						break;
					}
				}
			} else {
				end = start;
			}

			trimmed = string.substring(start, end);
		}

		return trimmed;
	}

	public static Collection<String> matchParts (Iterable<String> strings) {
		Set<String> matchParts = new HashSet<>();

		if (strings != null) {
			String[] split;
			String modified;
			StringBuffer buffer = new StringBuffer();

			for (String string : strings) {
				modified = restrict(string, ALLOWED_CHARS, " ",
						Integer.MAX_VALUE);
				split = modified.split(" ");
				for (String part : split) {
					if (part != null && !part.isEmpty()) {
						buffer.setLength(0);

						for (int i = 0; i < part.length() - 1; i++) {
							buffer.append(part.charAt(i));
							matchParts.add(buffer.toString());
						}
					}
				}
			}
		}

		return matchParts;
	}

	public static boolean isEmpty (String value) {
		return value == null || value.isEmpty();
	}

	public static boolean isNotEmpty (String value) {
		return !isEmpty(value);
	}

}
