<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/unstandard-1.0" prefix="un" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mobile/party/enroll/css/resource.css?v=2014-05-21" media="all" />
<script type="text/javascript" src="${pageContext.request.contextPath}/mobile/party/enroll/js/map.js?v=2014-05-21"></script>
<script src="http://api.map.baidu.com/api?key=24ffad3855e675265336a4cfb46d32b4&v=1.1&services=true" type="text/javascript"></script>
<script>
	if(rid.length > 0){
       var op = {
       latitude: latitude,
       longitude: longitude,
       hostaddress: hostaddress
       };
       baidu_map(op);
    }else{
      baidu_map();
    }
</script>