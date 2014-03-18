package com.framework.infrastructure.utils;

import org.junit.Assert;
import org.junit.Test;

public class HtmlUtilsTest extends Assert {

	@Test
	public void testHtmlspecialchars() {
		// 转译字符: \需要转换为\"
		String testContent = "&,<,>,\"";
		String willBeData = "&amp;,&lt;,&gt;,&quot;";
		String afterFiflterStr = HtmlUtils.htmlspecialchars(testContent);
		assertEquals(afterFiflterStr, willBeData);
	}

}
