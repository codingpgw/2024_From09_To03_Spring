<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="CP" value="${pageContext.request.contextPath }" />
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="sysDate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd_HH:mm:ss" /></c:set> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="${CP}/resources/images/favicon.ico" type="image/x-icon" >
<title>Insert title here</title>
<link rel="stylesheet" href="${CP}/resources/assets/css/hospital/form.css?date=${sysDate}">
<link rel="stylesheet" href="/ehr/resources/assets/css/main/main.css?date=${sysDate}">

<script src="/${CP}/resources/assets/js/jquery_3_7_1.js"></script>  <!-- jquery    -->
<script src="/${CP}/resources/assets/js/cmn/common.js"></script>    <!-- 공통 util -->
<script src="${CP}/resources/assets/js/hospital/hospital_reg.js?date=${sysDate}"></script>
</head>
<body>

</body>
</html>