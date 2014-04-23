<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html>
<head>
<head>
<c:set var="title" value="购物车" />
<%@include file="/WEB-INF/views/commons/meta.jsp"%>
</head>
<script type="text/javascript">
	function deleteCartItem(id) {
		var url = "${ctx}/cart/add/" + id;
		$.ajax({
			url : url,
			type : 'GET',
			dataType : "html",
			cache : false,
			success : function(html) {
				if (html == "ok") {
					$("#deleteSuccess").fadeIn("slow");
				}
			}
		});
	}
	
	function clearCart() {
		var url = "${ctx}/cart/clear";
		$.ajax({
			url : url,
			type : 'GET',
			dataType : "html",
			cache : false,
			success : function(html) {
				if (html == "ok") {
					alert("添加成功");
				}
			}
		});
	}
</script>
</head>
<body>
<%@include file="/WEB-INF/views/commons/header.jsp"%>
<div id="search_bar" data-reveal-id="myModal">
    <div class="searchBox">
        <i class="icon-search"></i>
        <input type="search" class="keywords" placeholder="请输入关键字">
    </div>
    <input name="完成" type="button" class="ok_butt" value="确定" >
</div>

<div class="posit_box">
 
 <!--公告开始-->
 <div id="notice">
    <i class="icon-volume-down float_left"></i> 
    <span>这里是公告文字</span> 
    <div id="ico_close"><i class="icon-remove"></i></div>
 </div>
 <!--公告end-->
 <!--左侧tab栏目分类代码开始-->
 <div class="left_tab" id="lanxx">
   <div id="search" data-reveal-id="search_bar"><i class="icon-search"></i></div> 
   <ul class="nav-list">
      <li class="active"><a href="#fl_list01">今日特惠<span class="navbar-unread">1</span></a></li>
      <li><a href="#fl_list02">店长推荐</a></li>
      <li><a href="#fl_list03">销售冠军</a></li>
      <li><a href="#fl_list04">奶茶</a></li>
      <li><a href="#fl_list05">西点/糕点</a></li>
   </ul>
 </div>
 <!--左侧tab栏目分类代码结束-->
 </div>
 <!--右侧列表代码开始-->
 <div class="right_list" id="wrapper">
    <div id="scroller">
    
        <section id="fl_list01">
         <h2 class="fltitle">今日特惠</h2>
         <ul id="thelist">
           
           <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>  
		</ul>
        </section>
        
        <section id="fl_list02">
        <h2 class="fltitle">店长推荐</h2>
         <ul id="thelist">
           
           <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />
  		        
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
            <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />       
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
            <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />      
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
            <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
            <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />      
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
		</ul>
        </section>
        
        <section id="fl_list03">
        <h2 class="fltitle">销售冠军</h2>
         <ul id="thelist">
           
           <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />
  		        
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
            <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />       
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
            <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />      
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
            <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
            <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />      
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
		</ul>
        </section>
        
       <section id="fl_list04">
        <h2 class="fltitle">奶茶</h2>
         <ul id="thelist">
           
           <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />
                 <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
            <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />       
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
            <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />      
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
            <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
            <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />      
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
		</ul>
        </section>
        
        <section id="fl_list05">
        <h2 class="fltitle">西点/糕点</h2>
         <ul id="thelist">
           
           <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
            <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />       
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
            <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />      
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
            <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
            <li>
              <div class="photo"><img src="images/list_img.jpg"></div>
              <h3>美味水果</h3>
              <p>已售12份<span>3人赞过</span></p>
              <p class="jiaqian">￥10<span>14</span></p>
              <div class="tjia">
                <input type="hidden" class="max" value="30" />      
                <div class="minus hide"><i class="icon-minus"></i></div>
                <div class="shuzi hide">0</div>
                <div class="plus"><i class="icon-plus"></i></div>
              </div>
            </li>
            
		</ul>
       </section>
     </div>
  </div>
 
<div id="foot_box">
  <ul class="bott_list">
    <li class="xzli">
      <span>菜单名字1</span>
       <div class="tjia2">
           <p class="jiage">￥10</p>
           <input type="hidden" class="max2" value="30" />
           <div class="minus2"><i class="icon-minus"></i></div>
           <div class="shuzi2">1</div>
           <div class="plus2"><i class="icon-plus"></i></div>
      </div>
    </li>
    <li class="xzli">
      <span>菜单名字2</span>
       <div class="tjia2">
           <p class="jiage">￥10</p>
           <input type="hidden" class="max2" value="30" />
           <div class="minus2"><i class="icon-minus"></i></div>
           <div class="shuzi2">1</div>
           <div class="plus2"><i class="icon-plus"></i></div>
      </div>
    </li>
    
    <li class="xzli">
      <span>菜单名字3</span>
       <div class="tjia2">
           <p class="jiage">￥10</p>
           <input type="hidden" class="max2" value="30" />
           <div class="minus2"><i class="icon-minus"></i></div>
           <div class="shuzi2">1</div>
           <div class="plus2"><i class="icon-plus"></i></div>
      </div>
    </li>
    
    <li class="xzli">
      <span>菜单名字4</span>
       <div class="tjia2">
           <p class="jiage">￥10</p>
           <input type="hidden" class="max2" value="30" />
           <div class="minus2"><i class="icon-minus"></i></div>
           <div class="shuzi2">1</div>
           <div class="plus2"><i class="icon-plus"></i></div>
      </div>
    </li>
    
    <li class="xzli">
      <span>菜单名字5</span>
       <div class="tjia2">
           <p class="jiage">￥10</p>
           <input type="hidden" class="max2" value="30" />
           <div class="minus2"><i class="icon-minus"></i></div>
           <div class="shuzi2">1</div>
           <div class="plus2"><i class="icon-plus"></i></div>
      </div>
    </li>
  </ul>
  
  <div class="heji_box">总计：￥132.0/5个菜</div>

  <div class="foot_bott">
    <a href="affirm.html" class="bott_ok_but">选好了</a>
    <div class="bott_zhankai"><i class="icon-angle-up"></i><i class="icon-angle-down" style="display:none;"></i></div>
  </div>
</div>

<script type="text/javascript">
//页脚展开关闭代码
if ( $("#foot_box").css("display")=="none" ){ 
		   $(".right_list").css({ "bottom": "0px"});
		}else{
		   $(".right_list").css({ "bottom": "50px"});
};
$("#ico_close").click(function(){
		$("#notice").css({ "display": "none"});
	    if ( $("#foot_box").css("display")=="none" ){ 
		   $(".right_list").css({ "top": "53px","bottom": "0px"});
		}else{
		   $(".right_list").css({ "top": "53px","bottom": "50px"});
		};
});

$("#search").click(function(){
			$("#foot_box").css({ "display": "none"});
			$(".right_list").css({ "bottom": "0px"});
});
$(".bott_zhankai").click(function(){
	         
		 if($(".bott_list").css("display")=="none"){
				 
				     $(".bott_list").css({ "display": "block"});
					 $(".icon-angle-up").css({ "display": "block"});
				     $(".icon-angle-down").css({ "display": "none"});
					 $(".heji_box").addClass("jiage2")
				}else{//否则就执行下面的语句
					 $(".bott_list").css({ "display": "none"});
					 $(".icon-angle-up").css({ "display": "none"});
					 $(".icon-angle-down").css({ "display": "block"});
                     $(".heji_box").removeClass("jiage2")
					 
				}
		
});


//滚动区的JS
var myScroll;
function loaded() {
	myScroll = new iScroll("wrapper");
}

document.addEventListener("touchmove", function (e) { e.preventDefault(); }, false);

/* * * * * * * *
 *
 * Use this for high compatibility (iDevice + Android)
 *
 */
document.addEventListener("DOMContentLoaded", function () { setTimeout(loaded, 200); }, false);
/*
 * * 滚动区的JS* * */
 
 
</script>

<script type="text/javascript">
/**
加减运算
**/  
  	$(".plus,.plus2").click(function(){
        changeNum(this,"+");
    })
  	$(".minus,.minus2").click(function(){
        changeNum(this,"-");
    })
  	function filterNum(_parent,num,min,max,handle){
  		if(isNaN(num)){
  			num=0;
  		}
  		$(".minus,.shuzi,.plus",_parent).removeClass("hide");
  		if(num<=min){//判断值
			
  			$(".minus,.shuzi",_parent).addClass("hide");
		    $(".minus2",_parent).parent().parent().remove();//删除当前对象的祖父级
			
                  			  
				   if ( $(".bott_list li").length < 1 ){//判断对象是否为空
	   	                   $(".bott_list").css({ "display": "none"});
						   $("#foot_box").css({ "display": "none"});
						   $(".right_list").css({ "bottom": "0px"});						   
	               }else {
                           $(".bott_list").css({ "display": "block"});
						   $("#foot_box").css({ "display": "block"});
						   $(".right_list").css({ "bottom": "50px"});	
                   };//判断选中外卖总合的一些条件逻辑
				   
				  if ( $("#foot_box").css("display")=="none" ){//判断对象是否为空
	   	                 
						  if ( $("#notice").css("display")=="none" ){//判断对象是否为空
    							 $(".right_list").css({ "top": "53px","bottom": "0px"});
	                      }else {
 							     $(".right_list").css({ "top": "94px","bottom": "0px"});
	                      }; 
						
	               }else {
					     if ( $("#notice").css("display")=="none" ){//判断对象是否为空
    							 $(".right_list").css({ "top": "53px","bottom": "50px"});
	                      }else {
 							      $(".right_list").css({ "top": "94px","bottom": "50px"});
	                      }; 
			  
                   };//
	        
			 
  			num = min;
  		}
  		if(num>=max){
  			//$(".plus",_parent).addClass("hide");
  			num =max;
  		}
  		handle&&handle.call(this,num);
  	}
  	function changeNum(self,type){
       var self = $(self);
       var _parent = self.parents(".tjia,.tjia2");
       var shuziElem = _parent.find(".shuzi,.shuzi2");
       var max = _parent.find(".max,.max2").val();
       var _shuzi = parseInt(shuziElem.html());
      console.log(type);
      if(type=="+"){
        _shuzi++;
      }else{
         _shuzi--;
      }
     
      filterNum(_parent,_shuzi,0,max,function(num){
        shuziElem.html(num);
      });
  	}
  </script>
	<!--  
	<div class="bs-docs-example">
	    <c:choose>
	   <c:when test="${sessionScope.cart.products!=null && fn:length(sessionScope.cart.products)>0}">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>名称</th>
					<th>数量</th>
					<th>价格</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sessionScope.cart.products}" var="entry">
					<c:set value="${entry.value}" var="item"/>
					<tr>
						<td>
							${item.product.name}
						</td>
						<td>
							 ${item.count}
						</td>
						<td>
							 ${item.count*item.product.price}/元
						</td>
						<td>
							<input type="button" class="btn btn-primary" onclick="deleteCartItem('${item.id}')"
							value="删除">
						</td>
					</tr>
				</c:forEach>
					<tr>
						<td>
							 总价:${sessionScope.cart.totalCount}
						</td>
						<td>
						</td>
						<td>
							<a href="${ctx}/order/confirm" class="btn btn-primary">确认订单</a>
						</td>
						<td>
								<a href="${ctx}/cart/clear" class="btn btn-primary">清空购物车</a>
						</td>
					</tr>
			</tbody>
		</table>
		 </c:when>
		 		   <c:otherwise>
			         	 尊敬的用户您好，你还没有下单哦！
			       </c:otherwise>
		</c:choose>
		
	</div>
	-->
</body>
</html>


 
