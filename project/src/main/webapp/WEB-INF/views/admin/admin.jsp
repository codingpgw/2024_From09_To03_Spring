<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%@page import="com.pcwk.ehr.cmn.SearchVO"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="${CP}/resources/assets/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="${CP}/resources/assets/css/admin/admin.css?date=<%=new Date()%>">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<title>관리자 페이지</title>
<script src="${CP}/resources/assets/js/jquery_3_7_1.js"></script>
<script src="${CP}/resources/assets/js/cmn/common.js"></script>
<script src="${CP}/resources/assets/js/admin/admin_cmn.js?date=<%=new Date()%>"></script>
</head>
<body>
  <div class="main-container">
    <div class="nav-container">
      <jsp:include page="/WEB-INF/views/admin/cmn/admin_top_nav.jsp"></jsp:include>
      <jsp:include page="/WEB-INF/views/admin/cmn/admin_left_nav.jsp"></jsp:include>
    </div>
    <div class="main-content-container">
      
    </div> <!-- //main-content-container -->
  </div> <!-- //main-container -->
</body>
</html>