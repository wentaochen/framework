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
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<!-- iPhone保持原始尺寸及比例 -->
<meta name="viewport" content="width=device-width,initial-scale=1.0">

<script src="${ctx}/${static}/jquery-1.7.1.js" type="text/javascript"></script>
<script src="${ctx}/${static}/bootstrap/js/bootstrap.js" type="text/javascript"></script>

<link   href="${ctx}/${static}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" type="text/css"/>
<link   href="${ctx}/${static}/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>