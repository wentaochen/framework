package com.weixin.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.weixin.domain.model.shop.Member;
import com.weixin.infra.Config;

/**
 * <pre>
 * 权限过滤器
 * 
 * @author chenwentao
 * 
 * @version 0.9
 * </pre>
 */
public class RightFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = 5162189625393315379L;

	/**
	 * 配置允许的角色
	 */
	private List<String> notAllowUrlList = new ArrayList<String>();

	/**
	 * 重定向的URL
	 */
	private String redirectURl = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		// 得到允许的角色,这个参数是由web.xml里的allowRole所指定
		String allowUrl = filterConfig.getInitParameter("notAllowUrl");
		String[] notAllowUrlArray = allowUrl.split(",");
		for (String url : notAllowUrlArray) {
			notAllowUrlList.add(url);
		}
		// 指定要重定向的页面
		redirectURl = filterConfig.getServletContext().getContextPath()
				+ "/login";
	}

	/**
	 * 在过滤器中实现权限控制
	 */
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) sRequest;
		HttpServletResponse response = (HttpServletResponse) sResponse;
		HttpSession session = request.getSession();

		String url = request.getServletPath();
		for (String notAllowUrl : notAllowUrlList) {
			if(url.startsWith(notAllowUrl)){
				Member memeber = (Member) session
						.getAttribute(Config.SESSION_USER);
				// 如果回话中的用户为空,页面重新定向到登陆页面
				if (memeber == null) {
					response.sendRedirect(redirectURl);
					return;
				}
			}
		}

		try {
			filterChain.doFilter(sRequest, sResponse);
		} catch (Throwable e) {
			throw new RuntimeException("权限过滤时候出现错误"+url, e);
		}
	}

	public void destroy() {
	}

}
