package com.framework.interfaces.web.spring.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

/**
 * <pre>
 * 专门处理多种Exception
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
public abstract class AbstractController {

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractController.class);

	private static final String COMMON_ERROR = "/common/error";

	private static final String COMMON_ERROR_ATTRIBUTE = "commonError";

	@InitBinder
	public void initDataBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat(
				"yyyy-MM-dd"), true);// "yyyy-MM-dd HH:mm:ss"
		binder.registerCustomEditor(Date.class, editor);
	}

	@ExceptionHandler( { NoSuchRequestHandlingMethodException.class })
	public ModelAndView handleNotFound(Exception ex, HttpServletRequest request) {
		String path = getServletPath(request);
		logger.warn("The page not found: " + path, ex);
		ModelAndView mav = composeModelAndView(path, ex);

		return mav;

	}

	@ExceptionHandler( { ServiceException.class })
	public ModelAndView handleServiceException(Exception ex,
			HttpServletRequest request) {
		String path = getServletPath(request);
		logger.warn(
				"Can't accomplish the request because of service layer error when access to: "
						+ path, ex);
		ModelAndView mav = composeModelAndView(path, ex);

		return mav;
	}

	@ExceptionHandler( { SecurityServiceException.class })
	public ModelAndView handleSecurityServiceException(Exception ex,
			HttpServletRequest request) {
		String path = getServletPath(request);
		logger
				.warn(
						"Can't accomplish the request because of SecurityService Exception when access to: "
								+ path, ex);
		ModelAndView mav = composeModelAndView(path, ex);

		return mav;
	}

	@ExceptionHandler( { DataAccessException.class })
	public ModelAndView handleDataAccessException(Exception ex,
			HttpServletRequest request) {
		String path = getServletPath(request);
		logger.warn(
				"Can't accomplish the request because of data layer error when access to: "
						+ path, ex);
		ModelAndView mav = composeModelAndView(path, ex);

		return mav;
	}

	/**
	 * 
	 * This is the fallback for all other UncaughtException.
	 * 
	 * @param ex
	 * 
	 * @param request
	 * 
	 * @return
	 * 
	 */
	@ExceptionHandler( { Exception.class })
	public ModelAndView handleUncaughtException(Exception ex,
			HttpServletRequest request) {
		String path = getServletPath(request);
		logger.warn(
				"Can't accomplish the request because an unexpected error when access to: "
						+ path, ex);
		ModelAndView mav = composeModelAndView(path, ex);

		return mav;
	}

	private String getServletPath(HttpServletRequest request) {
		return request.getPathInfo();
	}

	private ModelAndView composeModelAndView(String path, Exception ex) {
		ModelAndView mav = new ModelAndView(COMMON_ERROR);
		CommonError commonError = new CommonError();
		commonError.setPath(path);
		// only output exception information when debug
		if (logger.isDebugEnabled()) {
			commonError.setException(ex.toString());
		}
		mav.addObject(COMMON_ERROR_ATTRIBUTE, commonError);

		return mav;
	}
}
