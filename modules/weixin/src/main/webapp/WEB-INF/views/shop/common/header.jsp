<%@ page pageEncoding="UTF-8"%>

  <ul class="top_ico">
    <li><a href="${ctx}/"><i class="icon-home"></i></a></li>
    <!--  
    <li><a href="${ctx}/cart/list"><i class="icon-heart"></i></a></li>
    -->
    <li><a href="${ctx}/member/"><i class="icon-user"></i></a></li>
    <li><i class="icon-reorder"></i></li>
  </ul>
  <ul class="menu_zd">
    <li><a href="${ctx}/cart/list">购物车</a></li>
    <li><a href="${ctx}/order/history">我的订单</a></li>
    <c:if test="${not empty sessionScope.member}">
    	<li><a href="${ctx}/loginout">退出</a></li>
    </c:if>
  </ul>

<script type="text/javascript">
$(document).ready(function(){
	  $(".menu_zd li").eq(0).addClass("no-border");
});

//折叠菜单
$(".icon-reorder").click(function(){

	    if ( $(".menu_zd").css("display")=="block" ){ 
		   $(".menu_zd").css({ "display": "none"});
		   
		}else{ 
		   $(".menu_zd").css({ "display": "block"});
		};
});
</script>