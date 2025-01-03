<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link rel="shortcut icon" href="${CP}/resources/assets/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="${CP}/resources/assets/css/member/form.css?date=<%=new Date()%>">
<link rel="stylesheet" href="${CP}/resources/assets/css/main/main.css?date=<%=new Date()%>">
<title>회원 등록</title>
<script src="${CP}/resources/assets/js/jquery_3_7_1.js"></script>
<script src="${CP}/resources/assets/js/cmn/common.js"></script>
<script src="${CP}/resources/assets/js/member/member_reg.js?date=<%=new Date()%>"></script>
<body>
  <div id="container">
    <!-- header---------------------------------------------------->
    <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
    <!--// header-------------------------------------------------->
    <!-- aside----------------------------------------------------->
    <jsp:include page="/WEB-INF/views/include/aside.jsp"></jsp:include>
    <!--// aside--------------------------------------------------->
    <main id="contents">
      <div class="form-container">
			  <h2>회원 등록</h2>
			  <hr class="title-underline"/>
			  <!-- Button area -->
			  <div class="button-area">
			    <input type="button" id="doSave" value="등록">
			    <input type="button" id="moveToList" value="목록">
			  </div>
			  <!-- // Button area -->
			
			  <!-- Form area -->
			  <form action="#" class="form">
		      <div class="form-group">
		        <label for="memId">아이디</label>
		        <input class="infoBox" type="text" name="memId" id="memId" maxlength="30"/>
		      </div>
		      <br>
		      <div class="form-group">
		        <label for="password">비밀번호</label>
		        <input class="infoBox" type="password" name="password" id="password" maxlength="20"/>
		      </div>
		      <br>
		      <div class="form-group">
		        <label for="name">이름</label>
		        <input class="infoBox" type="text" name="name" id="name" maxlength="7"/>
		      </div>
		      <br>
		      <div class="form-group">
		        <label for="email">이메일</label>
		        <input class="infoBox" type="email" name="email" id="email" maxlength="320"/>
		      </div>
		      <br>
		      <div class="form-group">
		        <label for="phone">전화번호</label>
		        <input class="infoBox" type="text" name="phone" id="phone" maxlength="15"/>
		      </div>
		      <br>
		      <div class="form-group">
		        <label for="jumin">주민번호</label>
		        <input class="infoBox" type="text" name="jumin" id="jumin" maxlength="15"/>
		      </div>
			  </form>
			  <!-- // Form area -->
	    </div>
	  </main>
	  <!-- footer --------------------------------------------------->
	  <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	  <!--// footer-------------------------------------------------->
  </div>
</body>
</html>
