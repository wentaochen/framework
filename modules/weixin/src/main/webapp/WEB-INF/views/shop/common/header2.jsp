<%@ page pageEncoding="UTF-8"%>
<header>
  <h1 class="toptitle"><a href="#" onclick=""><i class="icon-angle-left"></i>联系我们</a></h1>
  <ul class="top_ico">
    <li><a href="${ctx}/"><i class="icon-home"></i></a></li>
    <li><a href="${ctx}/member"><i class="icon-user"></i></a></li>
    <li><i class="icon-reorder"></i></li>
  </ul>
  <ul class="menu_zd">
    <li><a href="#">展开菜单1</a></li>
    <li><a href="#">展开菜单2</a></li>
  </ul>
 </header>

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