<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/admin/top.jsp"%>  
	</head>
<body>
		
		
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">
	
			<!-- 检索  -->
			<form action="fileupload/list" method="post" name="feedbackForm" id="feedbackForm">
			<%--  <table border='0'>
				<tr>
				
					<td>
						<span class="input-icon">
							<input autocomplete="off" type="text" name="title" value="${pd.title }" placeholder="视频标题" />
						</span>
					</td>
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
				</tr>
			</table>  --%>
			<!-- 检索  -->
		
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th>序号</th>
						<th>文件名称</th>
						<th>文件地址</th>
						<th class="center">操作</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty dataList}">
						<c:if test="${QX.cha == 1 }">
						<c:forEach items="${dataList}" var="file" varStatus="vs">
									
							<tr>
								<td>${vs.index+1}</td>
								<td>${file.filename }</td>
								<td>${file.file_url}</td>
								<td style="width: 30px;" class="center">
									<div class='hidden-phone visible-desktop btn-group'>
										<c:if test="${QX.edit != 1 && QX.del != 1 }">
											<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="icon-lock" title="无权限"></i></span>
										</c:if>
										<div class="inline position-relative">
										<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>
										<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">
											<c:if test="${QX.edit == 1 }">
												<li><a style="cursor:pointer;" title="修改" onclick="update(${file.id})" class="tooltip-success" data-rel="tooltip" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>
												<li><a style="cursor:pointer;" title="删除" onclick="del(${file.id})" class="tooltip-error" data-rel="tooltip" data-placement="left"><span class="red"><i class="icon-trash"></i></span></a></li>
												<li><a style="cursor:pointer;" title="下载" onclick="filedown('${file.file_url}','${file.filename}');" class="tooltip-error" data-rel="tooltip" data-placement="left"><span class="blue"><i class="icon-download"></i></span></a></li>
											</c:if>
										</ul>
										</div>
										
									</div>
								</td>
							</tr>
						
						</c:forEach>
						</c:if>
						
						<c:if test="${QX.cha == 0 }">
							<tr>
								<td colspan="100" class="center">您无权查看</td>
							</tr>
						</c:if>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
					
				
				</tbody>
			</table>
			
		<div class="page-header position-relative">
		<table style="width:100%;">
			<tr>
				<td style="vertical-align:top;">
				<c:if test="${QX.add == 1 }">
						<a class="btn btn-small btn-success" onclick="add()">新增</a>
					</c:if>
				</td>
				<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
			</tr>
		</table>
		</div>
		</form>
	</div>
 
 
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript">
		
		$(top.hangge());
		//检索
		function search(){
			top.jzts();
			$("#feedbackForm").submit();
		}
		
		//新增
		function add(){
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增文件信息";
			 diag.URL = '<%=basePath%>fileupload/goAdd';
			 diag.Width = 850;
			 diag.Height = 555;
			 diag.CancelEvent = function(){ //关闭事件
				if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					if(typeof(nextPage) == "undefined"){
						window.location = "<%=basePath%>fileupload/list";
					}else{
						nextPage(${page.currentPage});
					}
				}
				diag.close();
			 };
			 diag.show();
		}
		
		 function update(id){
			var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="修改文件信息";
			 diag.URL = "<%=basePath%>fileupload/goUpdate?id="+id;
			 diag.Width = 850;
			 diag.Height = 555;
			 diag.CancelEvent = function(){ //关闭事件
				diag.close();
				nextPage(${page.currentPage});
			 };
			 diag.show();
			 //window.location = "<%=basePath%>fileupload/list";
		}
		//删除
		function del(id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					$.get('<%=basePath%>fileupload/del',{id:id},function(data){ 				  		
						if(data == '01'){
							alert("删除成功！");
							nextPage(${page.currentPage});
						}else{
							alert("删除失败");
						}						
					});
				}
			});			
		}
		//下载
		function filedown(file_url,filename){
		window.location = "<%=basePath%>fileupload/downDoc?file_url="+file_url+"&filename="+filename;
		}
		</script>
		
	</body>
</html>

