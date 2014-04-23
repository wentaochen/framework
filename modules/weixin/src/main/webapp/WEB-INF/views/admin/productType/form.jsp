<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html>
<head>
<%@include file="/WEB-INF/views/admin/commons/meta.jsp"%>
<script src="${ctx}/${static}/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/${static}/messages_cn.js" type="text/javascript"></script>
<title>添加产品分类</title>
</head>
<body>
	  <form action="${ctx}/admin/productype/add" method="post" id="addForm">
	  		<input type="hidden" name="id" value="${item.id}">
	  		<label>
	  			分类名称:
	  		</label>
	  		<br>
	  		<input type="text" name="name" value="${item.name}">
	  		<br>
	  		<label>
	  			排序:
	  		</label>
	  		<br>
	  		<input type="text" name="sort" value="${item.sort}">
	  		<br>
	  		<input type="submit" value="添加">
	  </form>
	  
	<script type="text/javascript">
		$().ready(function() {
			var validator = $("#addForm").validate({
				submitHandler : function(form) {
					$.ajax({
		                cache: true,
		                type: "POST",
		                url:"${ctx}/admin/productype/add",
		                data:$('#addForm').serialize(),
		                async: true,
		                error: function(request) {
		                    alert("提交失败请刷新后在提交");
		                },
		                success: function(data) {
		                     if("ok"==data){
		                    	 alert("操作成功");
		                    	// $('#addForm')[0].reset()  
		                     }else{
		                    	 alert("输入有误，请输入正确信息");
		                     }
		                }
		            });
				},
				rules: {
					   name: "required",
					   sort:{
						   required: true,
						   number:true
					   }
			    }
				,messages: {
					name: "请输入名称",
					sort:{
						required:"请输入数字",
						number:"必须输入数字"
					}
				}
			});
		});
	</script>
</body>
</html>
