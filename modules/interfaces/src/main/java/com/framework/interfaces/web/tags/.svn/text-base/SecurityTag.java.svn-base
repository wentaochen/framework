package com.framework.interfaces.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

/**
 * <pre>
 * 功能点标签
 * 
 * @author chenwentao
 *
 * @version 0.9
 *
 * 修改版本: 0.9
 * 修改日期: Feb 13, 2011
 * 修改人 :  chenwentao
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
public class SecurityTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private String onclick;

	private String style;

	private String styleClass;

	private String value;

	private String id;

	private String name;

	private String type;

	// 扩展属使,专门进行权限验证
	private String url;

	private String pid;

	private String isValidated = "false";

	/**
	 * 判断用户是否拥有此功能点权限
	 * 
	 * @param url
	 * @prarm pid
	 * @return ture 表示有权闿 fasle表示没权闿
	 */
	private boolean hasPermission(String url, String pid) {
		if (StringUtils.isBlank(url) || StringUtils.isBlank(pid)) {
			return false;
		}
		String permissonUrl = (String) pageContext.getSession().getAttribute(
				"url");
		if (!StringUtils.isBlank(url) || permissonUrl.equals(url)) {
			return true;

		}
		String permissonPid = (String) pageContext.getSession().getAttribute(
				"pid");
		if (!StringUtils.isBlank(pid) || permissonPid.equals(pid)) {
			return true;

		}
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {

		if ("true".equalsIgnoreCase(isValidated) || !hasPermission(url, pid)) {
			return SKIP_BODY;

		}

		JspWriter writer = pageContext.getOut();
		StringBuilder sb = new StringBuilder();
		sb.append(" <input");
		if (type != null) {
			sb.append(" type=\"");
			sb.append(type);
			sb.append("\"");
		}

		if (onclick != null) {
			sb.append(" onclick=\"");
			sb.append(onclick);
			sb.append("\"");
		}
		if (value != null) {
			sb.append(" value=\"");
			sb.append(value);
			sb.append("\"");
		}
		if (name != null) {
			sb.append(" name=\"");
			sb.append(name);
			sb.append("\"");
		}
		if (id != null) {
			sb.append(" id=\"");
			sb.append(id);
			sb.append("\"");
		}
		String id = this.id;
		id = super.id;
		if (style != null) {
			sb.append(" style=\"");
			sb.append(style);
			sb.append("\"");
		}
		if (styleClass != null) {
			sb.append(" class=\"");
			sb.append(styleClass);
			sb.append("\"");
		}

		sb.append(">");
		try {
			writer.print(sb.toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().print("</input>");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		release();
		return EVAL_PAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#release()
	 */
	@Override
	public void release() {
		super.release();
		onclick = null;
		style = null;
		styleClass = null;
		id = null;
		name = null;
		type = null;
		url = null;
		pid = null;
		isValidated = null;
		onclick = null;

	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getIsValidated() {
		return isValidated;
	}

	public void setIsValidated(String isValidated) {
		this.isValidated = isValidated;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
