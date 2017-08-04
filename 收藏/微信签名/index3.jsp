<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>
<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="mystatic/css1/commonwode.css">
<link rel="stylesheet" type="text/css" href="mystatic/css1/home.css">
<link rel="stylesheet" type="text/css" href="mystatic/css1/footer.css">
<link rel="stylesheet" type="text/css" href="mystatic/css1/public.css">
<link rel="stylesheet" type="text/css" href="mystatic/css1/styleshouye.css">

<title>首&nbsp;&nbsp;&nbsp;页5555</title>
<script src="mystatic/js/mui.min.js"></script>
<script type="text/javascript">
	window.jQuery
			|| document
					.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");
</script>

<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="mystatic/js/sha1.js"></script>
<script src="mystatic/js/wechat-cfg.js"></script>

</head>

<body>
	<div style="overflow:hidden;">
	   <div class="clearboth wrap-logo-zn "><img src="mystatic/image/logo-zn.png" style="height: auto;
    width: 100%;"></div>
       <div class="searchnew" id="searchContainer">
	       <div class="products-search-item">
		       <div class="main-nav-search ">
		       <form action="appProduct/toProductList" method="post" class="search">
			       <div class="input-group">
			       		<!--  
				       <form action="appProduct/toProductList" method="post" class="search">
						<input type="text" class="seaText" name="key"> 
						<input type="submit" value="搜索" style="background-color: #FF3D40;color: #fff;width:18%;height:30px;">
					   </form>
					   -->
				     			       
			       <input autocomplete="off" id="input-main-nav-search" class="form-control" name="key" title="产品搜索" placeholder="输入你搜索的东西" type="search">
				   <span class="input-group-btn">
				       		<button class="btn5 submit" style="background-color:#1080c4; border:#1080c4;width:50px;height:33px;margin-left:5px">
				       			<em class="search-icon" style="color:white;">搜索</em>
				       		</button>
				   </span>
				  		     
			       </div>
			       </form>	
		       </div>
	       </div>
       </div>
	</div>
	
<div class="container">
		    <div class="row">
        <div id="slide">
            <div class="hd">
                <ul><li class="on">1</li><li class="">2</li><li class="">3</li></ul>
            </div>
            <div class="bd">
                <div class="tempWrap" style="overflow:hidden; position:relative;">
                	<div class="tempWrap" style="overflow:hidden; position:relative;"><ul style="width: 1125px; position: relative; overflow: hidden; padding: 0px; margin: 0px; transition-duration: 200ms; transform: translate(0px, 0px) translateZ(0px);">
	                	<li style="display: table-cell; vertical-align: top; width: 375px;">
	   	                    	<a target="_blank">
	   	                    		<img src="mystatic/image/300.jpg">
	   	                    	</a>
	   	                    </li>
	                	<li style="display: table-cell; vertical-align: top; width: 375px;">
	   	                    	<a target="_blank">
	   	                    		<img src="mystatic/image/400.png">
	   	                    	</a>
	   	                    </li>
	                	<li style="display: table-cell; vertical-align: top; width: 375px;">
	   	                    	<a href="http://m.legendshop.cn/m_search/list?categoryId=38" target="_blank">
	   	                    		<img src="mystatic/image/400.png">
	   	                    	</a>
	   	                    </li>
	                	</ul></div>
                </div>
            </div>
        </div>
    </div>
 </div>
<script charset="utf-8" src="mystatic/css1/TouchSlide.js"></script>

<script type="text/javascript">
	
	TouchSlide({
		slideCell:"#slide",
		titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
		mainCell:".bd ul",
		effect:"left",
		autoPlay:true,//自动播放
		autoPage:true, //自动分页
		switchLoad:"_src" //切换加载，真实图片路径为"_src"
	});
	</script>
    
    
    
    
<div class="mban mar_3">

</div>       
    <div class="prefecture clearfix openwebview" style="margin-bottom: 8px;">
		<a class="cool">
        	<img class="fadeInImg" src="mystatic/image/2.jpg">
        	<i style="font-size:14px; color:#009BFF;"></i>
     	</a>
		<div class="prefecture_r">
    		<a href="javascript:;" class="sale" page_click_button="特卖汇">
        		<img class="fadeInImg" src="mystatic/image/3.jpg">
        		<i>每日抢购</i>
        		<span>1月10日更新</span>
    		</a>
		</div>

		<div class="prefecture_rb clearfix">
    		<a href="javascript:;" class="soon" data-url="/popular/list">
        		<img class="fadeInImg" src="mystatic/image/4.jpg">
        		<i><b></b></i>
    		</a>
    		<a href="javascript:;" data-url="/popular/list" class="popular">
        		<img class="fadeInImg" src="mystatic/image/5.jpg">
        		<i></i>
    		</a>
		</div>
	</div>
    
    
    <div class="recommend openwebview">
		<div class="wrap">
	        	
		<!--Begin 热门主题 Begin-->
			<div class="item" style=" margin-bottom:0px;">
				<div class="caption">
			        	<h3 class="caption_digital"><a>商品分类</a></h3>
			       		<span class="browse"><!--(3.6万人正在浏览)--></span>
			    </div>
			
			
				<!--End 限时特卖 End-->
				<div class="mban mar_3" style=" padding-bottom:45px;">
				   <a href="<%=basePath %>appProduct/toProductType"><img src="mystatic/image/mban.png" width="640"></a>
				   <a href="<%=basePath %>appProduct/toProductType"><img src="mystatic/image/mban1.png" width="640"></a>
				   <a href="<%=basePath %>appProduct/toProductType"><img src="mystatic/image/mban2.png" width="640"></a>
				   <a href="<%=basePath %>appProduct/toProductType"><img src="mystatic/image/mban3.png" width="640"></a>
				   <a href="<%=basePath %>appProduct/toProductType"><img src="mystatic/image/mban4.png" width="640"></a>
				   
				</div>		
			</div>
		</div>
	</div>
	
			
	<div class="recommend openwebview">
		<div class="wrap">

		</div>
		<div class="copyright">Copyright © 2016 E直通 版权所有</div>

		<div class="wx_nav">
			<a class="nav_index on">微店</a>
			<a class="nav_search"  href="<%=basePath%>appProduct/toProductType">分类</a>
			<a class="nav_shopcart" href='<%=basePath%>appBuycar/toBuycarDetail' >购物车</a> 
			<a class="nav_me" href="<%=basePath%>appMyuser/getAppMyuserByOpenid">我的</a>
		</div>

	</div>
	
	
	</body>
	</html>
