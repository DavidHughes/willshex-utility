//
//  JsonStringTest.java
//  com.willshex.utility
//
//  Created by billy1380 on 13 Aug 2013.
//  Copyright © 2013 WillShex Limited. All rights reserved.
//

package com.willshex.utility;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import com.willshex.utility.JsonUtils;

/**
 * @author billy1380
 * 
 */
public class JsonStringTest {

	/**
	 * Test method for {@link com.willshex.utility.JsonUtils#cleanJson(java.lang.String)} .
	 */
	@Test
	public void testCleanJson() {
		assertEquals("[null,\"som e\",\"test, \\\" \"]", JsonUtils.cleanJson("[ null ,  \"som e\"   ,   \"test, \\\" \"]   "));

	}

	/**
	 * Test method for {@link com.willshex.utility.JsonUtils#cleanJson(java.lang.String)} .
	 */
	@Test
	public void testCleanJson1() {
		assertEquals("[\"som e\",\"test, \\\" \"]", JsonUtils.cleanJson("[  ,  \"som e\"   ,   \"test, \\\" \"]   "));
	}

	/**
	 * Test method for {@link com.willshex.utility.JsonUtils#cleanJson(java.lang.String)} .
	 */
	@Test
	public void testCleanJson2() {
		assertEquals("{\"test\":[\"som e\",\"test, \\\" \"]}",
		        JsonUtils.cleanJson("{\"test\":[  ,  \"som e\"   ,   \"test, \\\" \"]   ,\"test1\" :  null, \"test3\":}"));
	}

	/**
	 * Test method for {@link com.willshex.utility.JsonUtils#cleanJson(java.lang.String)} .
	 */
	@Test
	public void testCleanJson3() {
		assertEquals("{\"test\":\"hobbit: ses\"}", JsonUtils.cleanJson("{\"test\":\"hobbit: ses\"}"));
	}

	/**
	 * Test method for {@link com.willshex.utility.JsonUtils#cleanJson(java.lang.String)} .
	 */
	@Test
	public void testCleanJson4() {
		assertEquals("{\"some\":\"test, \"}", JsonUtils.cleanJson("{\"some\":\"test, \"}"));
	}

	/**
	 * Test method for {@link com.willshex.utility.JsonUtils#cleanJson(java.lang.String)} .
	 */
	@Test
	public void testCleanJson5() {
		assertEquals("{\"some\":\"test, \"}", JsonUtils.cleanJson("{\"some\"   :   \"test, \"}"));
	}

	/**
	 * Test method for {@link com.willshex.utility.JsonUtils#cleanJson(java.lang.String)} .
	 */
	@Test
	public void testCleanJson6() {
		assertEquals("[\"som e\",\"test, \"]", JsonUtils.cleanJson("[\"som e\",\"test, \"]"));
	}

	/**
	 * Test method for {@link com.willshex.utility.JsonUtils#cleanJson(java.lang.String)} .
	 */
	@Test
	public void testCleanJson7() {
		assertEquals("[\"som e\",\"test, \\\" \"]", JsonUtils.cleanJson("[\"som e\",\"test, \\\" \"]"));
	}

	private static String uglyJson = null, beautifulJson = null;

	private static void getFiles() throws IOException {
		StringBuffer buffer = null;
		char[] chars = null;
		int read;
		BufferedReader reader;

		if (uglyJson == null) {
			buffer = new StringBuffer();
			chars = new char[1024];
			reader = new BufferedReader(new InputStreamReader(StringUtilsTest.class.getResourceAsStream("res/ugly.json")));

			while ((read = reader.read(chars)) > 0) {
				buffer.append(chars, 0, read);
			}

			reader.close();

			uglyJson = buffer.toString();
		}

		if (beautifulJson == null) {
			if (buffer == null) {
				buffer = new StringBuffer();
			} else {
				buffer.setLength(0);
			}

			if (chars == null) {
				chars = new char[1024];
			}

			reader = new BufferedReader(new InputStreamReader(StringUtilsTest.class.getResourceAsStream("res/beautiful.json")));

			while ((read = reader.read(chars)) > 0) {
				buffer.append(chars, 0, read);
			}

			reader.close();

			beautifulJson = buffer.toString();
		}
	}

	@Test
	public void testBeautifyJson() throws IOException {
		getFiles();
		assertEquals(beautifulJson, JsonUtils.beautifyJson(uglyJson));
	}

	@Test
	public void testUglifyJson() throws IOException {
		getFiles();
		assertEquals(uglyJson, JsonUtils.uglifyJson(beautifulJson));
	}
}
