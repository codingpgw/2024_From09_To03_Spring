<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<%
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    String today = sdf.format(new java.util.Date());
%>
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
      
        <!-- Form area -->
        <form action="#" class="form">
          <div class="form-group" id="inputBox">
            <label for="memId">아이디</label>
            <div class="input-btn-group">
	            <input class="infoBox infoId" type="text" name="memId" id="memId" maxlength="30" placeholder="아이디">
	            <button type="button" id="checkIdBtn">중복 확인</button>
            </div>
            <div><span id="idCheckMessage" style="color: red; font-size: 0.9em;"></span></div>
          </div>
          <br>
          <div class="form-group">
            <label for="password">비밀번호</label>
            <input class="infoBox infoPw" type="password" name="password" id="password" maxlength="20" placeholder="비밀번호">
          </div>
          <br>
          <div class="form-group">
            <label for="passwordCheck">비밀번호 확인</label>
            <div class="input-btn-group">
              <input class="infoBox infoCheckPw" type="password" name="passwordCheck" id="passwordCheck" maxlength="20" placeholder="비밀번호 확인">
            </div>
            <div><span id="pwCheckMessage" style="color: red; font-size: 0.9em;"></span></div>
          </div>
          <br>
          <div class="form-group">
            <label for="name">이름</label>
            <input class="infoBox" type="text" name="name" id="name" maxlength="7" placeholder="이름">
          </div>
          <br>
          <div class="form-group">
            <label for="phone">전화번호</label>
            <input class="infoBox" type="text" name="phone" id="phone" maxlength="13" placeholder="전화번호">
          </div>
          <br>
          <div class="form-group">
            <label for="birthDt">생년월일</label>
            <input type="date" name="birthDt" id="birthDt" min="1900-01-01" max="<%= today %>" value="<%= today %>">
          </div>
          <br>
          <div class="form-group" id="inputBox">
				    <label for="email">이메일</label>
				    <div class="input-btn-group">
				      <input class="infoBox infoEmail" type="email" name="email" id="email" maxlength="30" placeholder="이메일">
				      <span>@</span>
				      <input type="text" id="email-custom-domain" placeholder="직접 입력">
				      <select id="email-domain">
				        <option value="">직접 입력</option>
				        <option value="naver.com">naver.com</option>
				        <option value="google.com">google.com</option>
				        <option value="daum.net">daum.net</option>
				        <option value="yahoo.com">yahoo.com</option>
				        <option value="outlook.com">outlook.com</option>
				      </select>
				      <button type="button" id="sendEmailBtn">인증번호 받기</button>
				    </div>
				    <div><span id="sendCheckMessage" style="color: red; font-size: 0.9em;"></span></div>
				  </div>
				  <br>
          <div class="form-group" id="inputBox">
            <div class="input-btn-group">
	            <input class="infoBox" type="text" name="authCode" id="authCode" maxlength="6" placeholder="인증 번호" disabled="disabled">
	            <button type="button" id="checkCodeBtn">인증번호 확인</button>
            </div>
            <div><span id="codeCheckMessage" style="color: red; font-size: 0.9em;"></span></div>
          </div>
        </form>
        <!-- // Form area -->
        
        <!-- Button area -->
        <div class="button-area">
          <input type="button" id="doSave" value="회원가입">
          <input type="button" id="moveToLogin" value="뒤로가기">
        </div>
        <!-- // Button area -->
      </div>
    </main>
  </div>
</body>
</html>