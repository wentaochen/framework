<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html>
<head>
<c:set var="title" value="晴天铺子" />
<%@include file="/WEB-INF/views/commons/meta.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/views/commons/header.jsp"%>
	<div class="stieBox" id="wrapper">
		<div id="gundongbox">
			<!--顶图-->
			<div class="home_top_tu">
				<img src="${ctx}/${static}/shop/images/home_top_tu.jpg">
			</div>
			<!--基础信息-->
			<div class="data_box">
				<div class="tuxiang">
					<img src="${ctx}/${static}/shop/images/tuxiang.jpg">
				</div>
				<h2 class="dp_name">Jet的水果铺</h2>
				<div class="xinxi">
					<p>口味：</p>
					<p class="fshu">
						<strong class="st-fs-3"></strong>
					</p>
				</div>
				<div class="xinxi">
					<p>送货速度：</p>
					<p class="shsd">快如闪电(10)速度还行(2)慢如蜗牛(0)</p>
				</div>
				<a href="pro-list.html" class="more_but">逛 逛</a>
			</div>

			<!--公告信息-->
			<div class="noticeBox baiBox">
				<div class="left_ioc">
					<i class="icon-volume-down float_left"></i>
				</div>
				<div class="gonggao">
					人人返50%红包第一家外送瓶漂流奶茶,全部都是上等的红茶，奶茶加雀巢的纯牛奶,人人返50%红包第一家外送瓶漂流奶茶,全部都是上等的红茶，奶茶加雀巢的纯牛奶
				</div>
				<div id="ico_zhankai">
					<i class="icon-caret-up"></i><i class="icon-caret-down"
						style="display:none;"></i>
				</div>
			</div>

			<div class="baiBox">
				<ul class="yanye_data">
					<li><i class="icon-phone"></i>外卖电话：87872800</li>
					<li>配送费用：￥0.0(￥20.0起送)</li>
					<li>营业时间：10：00~21:00</li>
				</ul>
			</div>

			<div class="baiBox">
				<div class="bkuai_tit">
					<a href="review_list.html">
						<p class="f_left">
							用户评价 <span class="hui_999">(12)</span>
						</p>
						<p class="f_right">
							<i class="icon-angle-right"></i>
						</p> </a>
				</div>

				<div class="pjlist">
					<p class="f_left hui_999">Jet.xue</p>
					<p class="f_right hui_999">2014-04-03</p>
					<p class="fshu xingxing">
						<strong class="st-fs-3"></strong>
					</p>
					<p class="zhenghang">外送速度很快，32个赞</p>
				</div>
			</div>

			<div class="baiBox">
				<div class="bkuai_tit">
					<p class="f_left">
						外卖历史订单 <span class="hui_999">(30)</span>
					</p>
					<p class="f_right">
						<i class="icon-angle-right"></i>
					</p>
				</div>

				<div class="dindan_t">学生套餐，黄焖鸭</div>
			</div>

		</div>
	</div>
	<script type="text/javascript">
		//滚动区的JS
		var myScroll;
		function loaded() {
			myScroll = new iScroll('wrapper');
		}

		document.addEventListener('touchmove', function(e) {
			e.preventDefault();
		}, false);

		/* * * * * * * *
		 *
		 * Use this for high compatibility (iDevice + Android)
		 *
		 */
		document.addEventListener('DOMContentLoaded', function() {
			setTimeout(loaded, 200);
		}, false);
		/*
		 * * 滚动区的JS* * */

		$("#ico_zhankai").click(function() {//展开公告jQuery

			if ($(".gonggao").css("height") == "42px") {
				$(".gonggao").css({
					"height" : "auto"
				});
				$(".icon-caret-up").css({
					"display" : "none"
				});
				$(".icon-caret-down").css({
					"display" : "block"
				});
			} else {
				$(".gonggao").css({
					"height" : "42px"
				});
				$(".icon-caret-up").css({
					"display" : "block"
				});
				$(".icon-caret-down").css({
					"display" : "none"
				});
			}
			;
		});
	</script>
</body>
</html>


