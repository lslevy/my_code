<%@page import="java.util.Date"%>
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
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title></title>
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="static/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="static/js/jquery.tips.js"></script>
</head>
<body>
	<form class="form-horizontal" style="margin-top: 20px;" id="productForm" method="post" action="fileupload/${msg}" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${pd.id}">
		<div id="zhongxin">
		<div class="form-group" style="margin-top: 5px;">
			<label for="title" class="col-sm-2 control-label"><font color="red">*</font>文件名称：
			</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="filename" id="filename"  value="${pd.filename}"
					placeholder="文件名称">
			</div>
		</div>
		<div class="form-group" style="margin-top: 5px;">
			<label for="file_url" class="col-sm-2 control-label"><font color="red">*</font>文件上传：
			</label>
			 <input type="hidden" name="before_file_url" value="${pd.file_url}" />
			      ${pd.file_url}
			<div class="col-sm-10">
				<input type="file"  class="form-control" id="file_url"  name="file_url" >
			</div>
		</div>
	
		<div class="form-group" style="margin-top: 5px;">
			<label for="saveBtn" class="col-sm-2 control-label"></label>
			<div class="col-sm-10">
				<a class="btn btn-primary" id="saveBtn" onclick="save()">保存</a>
			</div>
		</div>
		</div>
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
	</form>
	<!-- 引入 -->
	<script type="text/javascript">
		
			//保存
	function save() {
				var product_name = $("#filename").val();
				if(product_name == null || product_name.trim() == ''){
					$("#filename").tips({
						side:3,
			            msg:'文件名称不能为空',
			            bg:'#AE81FF',
			            time:3
			        })
			        $("#filename").focus();
					return;
				}
				 
				$("#productForm").submit();
				$("#zhongxin").hide();
				$("#zhongxin2").show();
			
		}
	</script>
</body>
</html>