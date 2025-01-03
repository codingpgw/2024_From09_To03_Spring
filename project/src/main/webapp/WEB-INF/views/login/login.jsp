<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="${CP}/resources/assets/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="${CP}/resources/assets/css/login/login.css?date=<%=new Date()%>">
<title>로그인</title>
<script src="${CP}/resources/assets/js/jquery_3_7_1.js"></script>
<script src="${CP}/resources/assets/js/cmn/common.js"></script>
<script src="${CP}/resources/assets/js/login/login.js?date=<%=new Date()%>"></script>
</head>
<body>
  <c:if test="${not empty loginMessage}">
	  <div class="loginMessage">
	    ${loginMessage}
	  </div>
  </c:if>
	<div class="login-container">
		<h2>로그인</h2>
		<form action="#" method="post">
			<div class="form-group">
				<label for="memId">아이디</label>
				<input type="text" name="memId" id="memId" placeholder="아이디를 입력하세요" maxlength="30">
			</div>
			<div class="form-group">
				<label for="password">비밀번호</label>
				<input type="password" name="password" id="password" placeholder="비밀번호를 입력하세요" maxlength="30">
			</div>
		</form>
		<div class="form-actions btn-group">
			<button type="button" id="loginBtn" class="login-btn">로그인</button>
			<div class="regBtn">
			  <p id="registerBtn" class="register-btn">회원가입</p>
			</div>
			<!-- <button type="button" id="registerBtn" class="register-btn">회원가입</button> -->
		</div>
	</div>
</body>
</html>