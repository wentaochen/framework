<%@ page pageEncoding="UTF-8"%>
<%
	// 将过期日期设置为一个过去时间  
	response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
	// 设置 HTTP/1.1 no-cache 头  
	response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
	// 设置 IE 扩展 HTTP/1.1 no-cache headers， 用户自己添加,设置标准 HTTP/1.0 no-cache header.  
	response.addHeader("Cache-Control", "post-check=0, pre-check=0");
	response.setHeader("Pragma", "no-cache");
%>

<script src="${ctx}/static/js/highcharts/highcharts.js" type="text/javascript"></script>
<script src="${ctx}/static/js/highcharts/modules/exporting.js" type="text/javascript"></script>
