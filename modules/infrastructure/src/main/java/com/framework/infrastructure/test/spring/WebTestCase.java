package com.framework.infrastructure.test.spring;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerAdapter;

/**
 * <pre>
 * ר��������Ԫ����Controller
 * @author chenwentao
 *
 * @version 0.9
 *
 * �޸İ汾: 0.9
 * �޸�����: Dec 14, 2010
 * �޸��� :  chenwentao
 * �޸�˵��: �������
 * ������ ��
 * </pre>
 */
public class WebTestCase extends SpringContextTestCase {

	protected MockHttpServletRequest request;

	protected MockHttpServletResponse response;

	protected HandlerAdapter handlerAdapter;
	
	protected IMocksControl control = EasyMock.createControl();
}
