<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0"/>
<meta name="Description" content="网站描述">
<meta name="Keywords" content="关键字">
<meta name="copyright" content="版权声明" />
<title>外卖</title>
<!--[if lt IE 9]>
    <script src="${ctx}/static/shop/js/css3-mediaqueries.js"></script>
    <script src="${ctx}/static/shop/js/html5.js"></script>
<![endif]-->

<link href="${ctx}/static/shop/css/main.css" rel="stylesheet" type="text/css">
<link href="${ctx}/static/shop/css/font-awesome.min.css" rel="stylesheet" >
<!--[if IE 7]>
  <link rel="stylesheet" href="${ctx}/static/shop/css/font-awesome-ie7.min.css">
<![endif]-->
<script src="${ctx}/static/shop/js/jquery.min.1.8.3.js" type="text/javascript"></script>
<%-- <script src="${ctx}/${static}/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/${static}/messages_cn.js" type="text/javascript"></script> --%>
</head>

<body>
 <header>
  <h1 class="toptitle"><a href="javascript:void(0);" onclick="history.go(-1)"><i class="icon-angle-left"></i>意见反馈</a></h1>
  <ul class="top_ico">
    
    <li style=" font-size:1.2em;"><a href="javascript:void(0);" onclick="submitComment()">提交</a></li>
  </ul>
 
 </header>

<div class="stieBox" id="wrapper">
	<form id="commentForm" action="${ctx}/comment/save" method="post">
 		<div id="gundongbox">     
      		<div class="back-box">
      			<textarea id="comment" name="comment" cols="500" rows="" class="feedback-text" onkeyup="words_deal();"></textarea>
      			<div class="xdzf"><span class="zfsz">500</span>字</div>
      		</div>
   		</div>
	</form>    
</div>

<script type="text/javascript">
/* $().ready(function() {
	
	var validator = $("#commentForm").validate({
		submitHandler : function(form) {
			$.ajax({
                cache: true,
                type: "POST",
                url:"${ctx}/comment/save",
                data:$('#commentForm').serialize(),
                async: true,
                error: function(request) {
                    alert("提交失败请刷新后在提交");
                },
                success: function(data) {
                     if("ok"==data){
                    	 window.location.href = "${ctx}/member/";
                     }else{
                    	 alert("提交意见错误");
                     }
                }
            });
		},
		rules: {
			   comment: "required",
	    }
		,messages: {
			comment: "请输入意见",
		}
	});
}); */

function submitComment(){
	if($("#comment").val() == "" || $("#comment").val() == null ){
		alert("评论不能为空");
		return;
	}	
	$.ajax({
	    cache: true,
	    type: "POST",
	    url:"${ctx}/comment/save",
	    data:$('#commentForm').serialize(),
	    async: true,
	    error: function(request) {
	        alert("提交失败请刷新后在提交");
	    },
	    success: function(data) {
	         if("ok"==data){
	        	 window.location.href = "${ctx}/member/";
	         }else{
	        	 alert("提交意见错误");
	         }
	    }
	});
}
</script>
</body>

<script type="text/javascript">
//折叠菜单
$(".icon-reorder").click(function(){

	    if ( $(".menu_zd").css("display")=="block" ){ 
		   $(".menu_zd").css({ "display": "none"});
		   
		}else{ 
		   $(".menu_zd").css({ "display": "block"});
		};
});
$(document).ready(function(){
 $(".menu_zd li").eq(0).addClass("no-border");

});

function words_deal()
{
   var curLength=$(".feedback-text").val().length;
   if(curLength>500)
   {
        var num=$(".feedback-text").val().substr(0,500);
        $(".feedback-text").val(num);
        alert("超过字数限制，多出的字将被截断！" );
   }
   else
   {
        $(".zfsz").text(500-$(".feedback-text").val().length);
   }
}
</script> 
</html>
 