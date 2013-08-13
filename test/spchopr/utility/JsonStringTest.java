//
//  JsonStringTest.java
//  spchopr.utility
//
//  Created by billy1380 on 13 Aug 2013.
//  Copyright © 2013 Spacehopper Studios Ltd. All rights reserved.
//

package spchopr.utility;

import static org.junit.Assert.*;

import org.junit.Test;

import com.spacehopperstudios.utility.JsonUtils;

/**
 * @author billy1380
 * 
 */
public class JsonStringTest {

	/**
	 * Test method for
	 * {@link com.spacehopperstudios.utility.JsonUtils#cleanJson(java.lang.String)}
	 * .
	 */
	@Test
	public void testCleanJson() {
		// assertEquals("{\"some\":\"test, \"}",
		// JsonUtils.cleanJson("{\"some\":\"test, \"}"));
		// assertEquals("{\"some\":\"test, \"}",
		// JsonUtils.cleanJson("{\"some\"   :   \"test, \"}"));
		// assertEquals("[\"som e\",\"test, \"]",
		// JsonUtils.cleanJson("[\"som e\",\"test, \"]"));
		// assertEquals("[\"som e\",\"test, \\\" \"]",
		// JsonUtils.cleanJson("[\"som e\",\"test, \\\" \"]"));
		assertEquals("[null,\"som e\",\"test, \\\" \"]", JsonUtils.cleanJson("[ null ,  \"som e\"   ,   \"test, \\\" \"]   "));
		assertEquals("[\"som e\",\"test, \\\" \"]", JsonUtils.cleanJson("[  ,  \"som e\"   ,   \"test, \\\" \"]   "));
		assertEquals("{\"test\":[\"som e\",\"test, \\\" \"]}", JsonUtils.cleanJson("{\"test\":[  ,  \"som e\"   ,   \"test, \\\" \"]   ,\"test1\" :  null, \"test3\":}"));
	}
}
