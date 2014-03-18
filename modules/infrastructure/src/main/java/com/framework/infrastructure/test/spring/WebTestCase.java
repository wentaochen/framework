package com.framework.infrastructure.test.spring;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerAdapter;

/**
 * <pre>
 * 专门用来单元测试Controller
 * @author chenwentao
 *
 * @version 0.9
 *
 * 修改版本: 0.9
 * 修改日期: Dec 14, 2010
 * 修改人 :  chenwentao
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
public class WebTestCase extends SpringContextTestCase {

	protected MockHttpServletRequest request;

	protected MockHttpServletResponse response;

	protected HandlerAdapter handlerAdapter;
	
	protected IMocksControl control = EasyMock.createControl();
}
