<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="${CP}/resources/assets/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="${CP}/resources/assets/css/admin/admin.css?date=<%=new Date()%>">
<link rel="stylesheet" href="${CP}/resources/assets/css/admin/admin_form.css?date=<%=new Date()%>">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<title>회원 관리</title>
<script src="${CP}/resources/assets/js/jquery_3_7_1.js"></script>
<script src="${CP}/resources/assets/js/cmn/common.js"></script>
<script src="${CP}/resources/assets/js/admin/admin_cmn.js?date=<%=new Date()%>"></script>
<script src="${CP}/resources/assets/js/admin/admin_mng.js?date=<%=new Date()%>"></script>
</head>
<body>
  <div class="main-container">
    <div class="nav-container">
      <jsp:include page="/WEB-INF/views/admin_cmn/admin_top_nav.jsp"></jsp:include>
      <jsp:include page="/WEB-INF/views/admin_cmn/admin_left_nav.jsp"></jsp:include>
    </div>
    <div class="main-content-container">
      <div id="memberMngPage" class="content-page" style="display: block;">
				<main id="contents">
				 <div class="content-title">
				   <h2>회원 정보 수정</h2>
				   <div class="form-area">
					   <!-- Button area -->
					   <div class="button-area">
					     <input type="button" id="doUpdate" value="수정">
					     <input type="button" id="doDelete" value="삭제">
					   </div>
					   <!-- // Button area -->
					   <!-- Form area -->
					   <form action="#" class="form">
					     <div class="form-group">
					       <label for="memId">아이디</label>
					       <input type="text" name="memId" id="memId" maxlength="30" value="${vo.memId}" disabled="disabled"/>
					     </div>
					     <br>
					     <div class="form-group">
					       <label for="password">비밀번호</label>
					       <input type="password" name="password" id="password" maxlength="30" value="${vo.password}"/>
					     </div>
					     <br>
					     <div class="form-group">
					       <label for="name">이름</label>
					       <input type="text" name="name" id="name" maxlength="7" value="${vo.name}"/>
					     </div>
					     <br>
					     <div class="form-group">
					       <label for="email">이메일</label>
					       <input type="email" name="email" id="email" maxlength="320" value="${vo.email}">
					     </div>
					     <br>
					     <div class="form-group">
					       <label for="phone">전화번호</label>
					       <input type="text" name="phone" id="phone" maxlength="15" value="${vo.phone}">
					     </div>
					     <br>
					     <div class="form-group">
					       <label for="jumin">주민번호</label>
					       <input type="text" name="jumin" id="jumin" maxlength="15" value="${vo.jumin}">
					     </div>
					     <br>
					     <div class="form-group">
	               <label for="memDiv">사용자 구분</label>
	               <input type="text" name="memDiv" id="memDiv" maxlength="5" value="${vo.memDiv}">
	             </div>
					   </form>
				   </div>
				   <!-- // Form area -->
				  </div>
				</main>
	    </div>
	  </div>
	</div>
</body>
</html>