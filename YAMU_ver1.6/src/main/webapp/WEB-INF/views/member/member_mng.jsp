<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="${CP}/resources/assets/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="${CP}/resources/assets/css/member/form.css?v=<%=new Date()%>">
<link rel="stylesheet" href="${CP}/resources/assets/css/main/home.css?date=<%=new Date()%>">
<title>회원 관리</title>
<script src="${CP}/resources/assets/js/jquery_3_7_1.js"></script>
<script src="${CP}/resources/assets/js/cmn/common.js"></script>
<script src="${CP}/resources/assets/js/member/member_mng.js?date=<%=new Date()%>"></script>
</head>
<body>
  <div id="main-container">
    <div class="nav-container">
      <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
    </div>
    <div class="main-content-container">
      <!-- 정보 관리 -->
      <div class="form-container">
        <h2>회원관리</h2>
        <hr class="title-underline" />
        <!-- <img src="https://via.placeholder.com/80" alt="프로필 사진" id="profile-img" />
        <div class="upload-btn">
          <input type="file" id="profile-upload" accept="image/*" onchange="previewImage(event)" />
        </div> -->
        <!-- form area -->
        <form action="#" class="form">
          <div class="form-group">
            <label for="memId">아이디</label>
            <input type="text" maxlength="30" name="memId" id="memId" value="${vo.memId}" disabled />
          </div>
          <div class="form-group">
            <label for="memname">이름</label>
            <input type="text" maxlength="7" name="memname" id="memname" value="${vo.name}" />
          </div>
          <div class="form-group">
            <label for="mempassword">비밀번호</label>
            <input type="password" maxlength="30" name="mempassword" id="mempassword" value="${vo.password}" />
          </div>
          <div class="form-group">
            <label for="mememail">이메일</label>
            <input type="email" maxlength="320" name="mememail" id="mememail" value="${vo.email}" />
          </div>
          <div class="form-group">
            <label for="memphonenum">전화번호</label>
            <input type="tel" maxlength="11" name="memphonenum" id="memphonenum" value="${vo.phone}" />
          </div>
        </form>
        <div class="button-area">
          <input type="button" id="doUpdate" value="수정" />
          <input type="button" id="doDelete" value="삭제" />
        </div>
      </div>

      <!-- 예약 관리 -->
      <div class="reservation">
        <table>
          <tr>
            <th>BOOK DT</th>
            <td>EX)2024-12-25</td>
          </tr>
          <tr>
            <th>HOSPITAL ID</th>
            <td>Acorn병원</td>
          </tr>
        </table>
      </div>
    
	  </div>
  </div>
</body>
</html>