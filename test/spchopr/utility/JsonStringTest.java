//
//  JsonStringTest.java
//  spchopr.utility
//
//  Created by billy1380 on 13 Aug 2013.
//  Copyright © 2013 Spacehopper Studios Ltd. All rights reserved.
//

package spchopr.utility;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.spacehopperstudios.utility.JsonUtils;

/**
 * @author billy1380
 * 
 */
public class JsonStringTest {

	/**
	 * Test method for {@link com.spacehopperstudios.utility.JsonUtils#cleanJson(java.lang.String)} .
	 */
	@Test
	public void testCleanJson() {
		assertEquals("[null,\"som e\",\"test, \\\" \"]", JsonUtils.cleanJson("[ null ,  \"som e\"   ,   \"test, \\\" \"]   "));

	}

	/**
	 * Test method for {@link com.spacehopperstudios.utility.JsonUtils#cleanJson(java.lang.String)} .
	 */
	@Test
	public void testCleanJson1() {
		assertEquals("[\"som e\",\"test, \\\" \"]", JsonUtils.cleanJson("[  ,  \"som e\"   ,   \"test, \\\" \"]   "));
	}

	/**
	 * Test method for {@link com.spacehopperstudios.utility.JsonUtils#cleanJson(java.lang.String)} .
	 */
	@Test
	public void testCleanJson2() {
		assertEquals("{\"test\":[\"som e\",\"test, \\\" \"]}",
				JsonUtils.cleanJson("{\"test\":[  ,  \"som e\"   ,   \"test, \\\" \"]   ,\"test1\" :  null, \"test3\":}"));
	}

	/**
	 * Test method for {@link com.spacehopperstudios.utility.JsonUtils#cleanJson(java.lang.String)} .
	 */
	@Test
	public void testCleanJson3() {
		assertEquals("{\"test\":\"hobbit: ses\"}", JsonUtils.cleanJson("{\"test\":\"hobbit: ses\"}"));
	}

	/**
	 * Test method for {@link com.spacehopperstudios.utility.JsonUtils#cleanJson(java.lang.String)} .
	 */
	@Test
	public void testCleanJson4() {
		assertEquals("{\"some\":\"test, \"}", JsonUtils.cleanJson("{\"some\":\"test, \"}"));
	}

	/**
	 * Test method for {@link com.spacehopperstudios.utility.JsonUtils#cleanJson(java.lang.String)} .
	 */
	@Test
	public void testCleanJson5() {
		assertEquals("{\"some\":\"test, \"}", JsonUtils.cleanJson("{\"some\"   :   \"test, \"}"));
	}

	/**
	 * Test method for {@link com.spacehopperstudios.utility.JsonUtils#cleanJson(java.lang.String)} .
	 */
	@Test
	public void testCleanJson6() {
		assertEquals("[\"som e\",\"test, \"]", JsonUtils.cleanJson("[\"som e\",\"test, \"]"));
	}

	/**
	 * Test method for {@link com.spacehopperstudios.utility.JsonUtils#cleanJson(java.lang.String)} .
	 */
	@Test
	public void testCleanJson7() {
		assertEquals("[\"som e\",\"test, \\\" \"]", JsonUtils.cleanJson("[\"som e\",\"test, \\\" \"]"));
	}
}
