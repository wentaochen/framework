<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/views/admin/commons/meta.jsp"%>
<!--layout-->
<script src="${ctx}/${static}/layout/jquery.layout.js"
	type="text/javascript"></script>
<script src="${ctx}/${static}/layout/jquery.ui.all.js"
	type="text/javascript"></script>
<title>管理页面</title>
<script>
	$(function() {
		//layout使用
		$('body').layout({
			applyDefaultStyles : true,
			north__spacing_open : 0,//跨行分隔
			south__spacing_open : 0,//跨行分隔
			west__spacing_open : 0,//跨行分隔
			center__spacing_open : 0
		//跨行分隔
		});
	});

	function show() {
		$("#more").empty();
		document.getElementById("morediv").style.display = 'block';
		/*
		$("moreDiv").show();
		$("moreDiv")[0].style.display = 'block';
		 */
	}

	function gotoUrl(url) {
		window.location = url;
	}
</script>
<style type="text/css">
.nav .nav-header {
	font-size: 20px;
}
</style>
</head>
<body>
	<!--center-->
	<iframe id="mainFrame" name="mainFrame" class="ui-layout-center"
		width="100%" height="100%" frameborder="0" scrolling="auto"
		src="${ctx}/admin/product/list"></iframe>
	<!--header-->
	<DIV class="ui-layout-north">
		<div id="headeDiv" style="width: 100%;height: 100%;">
			<div style="display: inline;">
				<!-- 
				<img src="http://www.wrisc.cn/wrisc/static/v2/images/logo.png"
					style="width: 130px" />
				 -->
			</div>
			<div
				style="display: inline;margin-right: 5%;float: right;font-size:18px;">
				管理员,欢迎您 |
				<!--  <a href="${ctx}/" class="btn">退出</a>-->
			</div>
		</div>
	</DIV>

	<!-- left -->
	<DIV class="ui-layout-west">
		<!-- class="well" -->
		<div style="padding: 8px 0;">
			<ul class="nav nav-list" id="identifier">
				<li class="nav-header"><a href="${ctx}/admin/product/list"
					target="mainFrame">主页</a>
				</li>
				<li class="divider"></li>
				<li class="nav-header">常用功能</li>
				<li>
					<a  target="mainFrame" style="font-size: 18px;">&nbsp;&nbsp;&nbsp;店铺管理</a>
				</li>

				<li><a href="${ctx}/admin/product/list" target="mainFrame"
					style="font-size: 18px;color: red;">&nbsp;&nbsp;&nbsp;产品管理</a>
				</li>
				<li><a href="${ctx}/admin/productype/list" target="mainFrame"
					style="font-size: 18px;color: red;">&nbsp;&nbsp;&nbsp;产品分类管理</a>
				</li>
				<li><a href="${ctx}/admin/order/list" target="mainFrame"
					style="font-size: 18px;">&nbsp;&nbsp;&nbsp;订单管理</a>
				</li>
				<li><a href="${ctx}/admin/member/list" target="mainFrame"
					style="font-size: 18px;">&nbsp;&nbsp;&nbsp;会员管理</a>
				</li>

				<li class="divider"></li>
				<%--
				<li id="more"><a href="#" class="btn" onclick="show();">更多...</a>
				</li>
				<div id="morediv" style="display:none;">
					<li class="nav-header">高级功能</li>
					
					<li><a href="#" target="mainFrame" style="font-size: 18px;">&nbsp;&nbsp;&nbsp;支付管理</a>
					</li>
					<li><a href="#" target="mainFrame" style="font-size: 18px;">&nbsp;&nbsp;&nbsp;物流管理</a>
					</li>
					<!--
								<li>
									<a  href="${ctx}/bound/hotjewelery" target="mainFrame"  style="font-size: 18px;">&nbsp;&nbsp;&nbsp;导入淘宝数据</a>
								</li>
								-->
					<li class="divider"></li>
					<li class="nav-header">报表</li>

					<li><a href="#" target="mainFrame" style="font-size: 18px;">&nbsp;&nbsp;&nbsp;库存报表</a>
					</li>
					<li><a href="#" target="mainFrame" style="font-size: 18px;">&nbsp;&nbsp;&nbsp;产品销量报表</a>
					</li>
					<li><a href="#" target="mainFrame" style="font-size: 18px;">&nbsp;&nbsp;&nbsp;销售趋势报表</a>
					</li>

					<li class="divider"></li>
					<li class="nav-header">其他功能</li>
					<li><a href="#" target="mainFrame" style="font-size: 18px;">&nbsp;&nbsp;&nbsp;设备管理</a>
					</li>
					<li><a href="#" target="mainFrame" style="font-size: 18px;">&nbsp;&nbsp;&nbsp;接口状态</a>
					</li>
				</div>
				<li class="divider"></li>
				<li><a href="#" target="mainFrame" style="font-size: 20px;">帮助</a>
				</li>
				 --%>
			</ul>
		</div>
	</DIV>

	<!--footer-->
	<DIV class="ui-layout-south">
		<div style="margin-left: 35%">Copyright 2013 - 2014
			移动电商新力量</div>
	</DIV>
</body>
</html>

<%--
<head>
<%@include file="/WEB-INF/views/commons/meta.jsp"%>
<title>晴天铺子后台管理</title>
</head>
<body>
	<h3>晴天铺子，后台管理页面！</h3>
	<table class="table table-hover">
		<tr>
			<td><a href="${ctx}/admin/product/list">产品管理</a></td>
		</tr>
		<tr>
			<td><a href="${ctx}/admin/order/list">订单管理</a></td>
		</tr>
		<tr>
			<td><a href="${ctx}/admin/member/list">会员管理</a></td>
		</tr>
		<tr>
			<td><a href="${ctx}/admin/product/list">店铺管理</a></td>
		</tr>
		<tr>
			<td><a href="${ctx}/admin/product/list">支付管理</a>
			</td>
		</tr>
		<tr>
			<td><a href="${ctx}/admin/product/list">物流管理管理</a>
			</td>
		</tr>
	</table>
</body>
</html>
 --%>
