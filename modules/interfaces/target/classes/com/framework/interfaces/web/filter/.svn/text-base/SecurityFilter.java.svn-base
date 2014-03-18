package com.framework.interfaces.web.filter;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.framework.infrastructure.utils.ServletUtils;

/**
 * <pre>
 * @author chenwentao
 *
 * @version 0.9
 *
 * 修改版本: 0.9
 * 修改日期: Feb 10, 2011
 * 修改人 :  chenwentao
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
public abstract class SecurityFilter extends OncePerRequestFilter {

	/**
	 * 日志对象
	 */
	protected static final Logger logger = LoggerFactory
			.getLogger(SecurityFilter.class);

	/**
	 * 不需要过滤的url集，逗号分割
	 */
	protected String notFilterList;

	/**
	 * 不需要过滤的url
	 */
	protected Set<String> notFilters = new HashSet<String>();

	/**
	 * 需要登录的url集，逗号分割
	 */
	protected String needLoginFilterList;

	/**
	 * 需要登录过滤的url
	 */
	protected Set<String> needLoginFilters = new HashSet<String>();

	public void setNotFilterList(String notFilterList) {
		this.notFilterList = notFilterList;
	}

	public void setNeedLoginFilterList(String needLoginFilterList) {
		this.needLoginFilterList = needLoginFilterList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.filter.GenericFilterBean#initFilterBean()
	 */
	@Override
	protected void initFilterBean() throws ServletException {
		if (notFilterList != null) {
			String[] urls = notFilterList.split("\\s*,\\s*");
			for (String url : urls) {
				notFilters.add(url);
			}
		}
		if (needLoginFilterList != null) {
			String[] urls = needLoginFilterList.split("\\s*,\\s*");
			for (String url : urls) {
				needLoginFilters.add(url);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.filter.OncePerRequestFilter#shouldNotFilter(javax
	 * .servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request)
			throws ServletException {
		String url = request.getServletPath();

		return notFilters.contains(url);

	}

	/**
	 * 用户未登录，跳转到登录页面
	 * 
	 * @param msg
	 *            消息内容
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @throws ServletException
	 */
	protected void promptTimout(String msg, HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/login.jsp");

		if (dispatcher != null) {
			request.setAttribute("lastPage", ServletUtils.findLastPage(request,
					response));
			request.setAttribute("loginMsg", msg);

			try {
				dispatcher.forward(request, response);
			}
			catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}

	/**
	 * 不具有访问权限，跳转到指定页面
	 * 
	 * @param msg
	 *            消息内容
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @throws ServletException
	 */
	protected void prompt(String msg, HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		// RequestDispatcher dispatcher = request
		// .getRequestDispatcher("/resultinfo.jsp");
		//
		// if (dispatcher != null) {
		// List<String> msgs = new ArrayList<String>(1);
		// msgs.add(msg);
		// List<Position> positions = new ArrayList<Position>(1);
		// Position p = new Position();
		//
		// p.setType(1);
		// p.setName("返回");
		// p.setUrl("history.back()");
		// positions.add(p);
		//
		// request.setAttribute("actionMessages", msgs);
		// request.setAttribute("positions", positions);
		//
		// try {
		// dispatcher.forward(request, response);
		// }
		// catch (Exception e) {
		// throw new ServletException(e);
		// }
		// }
	}

}
