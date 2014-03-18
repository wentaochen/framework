package com.framework.interfaces.web.spring.common;

/**
 * <pre>
 * @author chenwentao
 *
 * @version 0.9
 *
 * 修改版本: 0.9
 * 修改日期: Dec 28, 2010
 * 修改人 :  chenwentao
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
public class SecurityServiceException extends RuntimeException {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1674525511298858251L;

	public SecurityServiceException() {
		super();
	}

	public SecurityServiceException(String message) {
		super(message);
	}

	public SecurityServiceException(Throwable cause) {
		super(cause);
	}

}
