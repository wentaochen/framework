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
public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1207893822352846506L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
