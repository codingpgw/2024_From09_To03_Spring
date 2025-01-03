<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="${CP}/resources/assets/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="${CP}/resources/assets/css/member/form.css?date=<%=new Date()%>">
<title>회원 가입</title>
<script src="${CP}/resources/assets/js/jquery_3_7_1.js"></script>
<script src="${CP}/resources/assets/js/cmn/common.js"></script>
<script src="${CP}/resources/assets/js/register/register.js?date=<%=new Date()%>"></script>
</head>
<body>
  <div id="container">
    <main id="contents">
      <div class="form-container">
        <h2>회원 가입</h2>
        <hr class="title-underline"/>
        <!-- Button area -->
        <div class="button-area">
          <input type="button" id="doSave" value="회원가입" disabled="disabled">
          <input type="button" id="moveToLogin" value="뒤로가기">
        </div>
        <!-- // Button area -->
      
        <!-- Form area -->
        <form action="#" class="form">
          <div class="form-group">
            <label for="memId">아이디</label>
            <input class="infoBox" type="text" name="memId" id="memId" maxlength="30"/>
            <div><span id="memIdCheckMessage" style="color: red; font-size: 0.9em;"></span></div>
          </div>
          <br>
          <div class="form-group">
            <label for="password">비밀번호</label>
            <input class="infoBox" type="password" name="password" id="password" maxlength="20"/>
          </div>
          <br>
          <div class="form-group">
            <label for="passwordCheck">비밀번호 확인</label>
            <input class="infoBox" type="password" name="passwordCheck" id="passwordCheck" maxlength="20"/>
          </div>
          <br>
          <div class="form-group">
            <label for="name">이름</label>
            <input class="infoBox" type="text" name="name" id="name" maxlength="7"/>
          </div>
          <br>
          <div class="form-group">
            <label for="email">이메일</label><!--변경 사항 추가  -->
            <input class="infoBox" type="email" name="email" id="email" maxlength="320"/>
             <div style="display: block; text-align: right;">
              <input type="button" value="인증하기" id="emailAuth"> 
            </div>
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
          
           <!--변경 사항 추가  -->
          <input class="form-control" placeholder="인증 코드 6자리를 입력해주세요." maxlength="6" disabled="disabled" name="authCode" id="authCode" type="text" autofocus>
          <span id="emailAuthWarn"></span>
        </form>
        <!-- // Form area -->
      </div>
    </main>
  </div>
</body>
</html>