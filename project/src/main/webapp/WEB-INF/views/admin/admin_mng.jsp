<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${CP}/resources/assets/css/admin/admin_form.css?date=<%=new Date()%>">
<script src="${CP}/resources/assets/js/admin/admin_mng.js?date=<%=new Date()%>"></script>
<div id="memberModal" class="modal">
  <div class="modal-content">
    <i class="fa-solid fa-xmark close-btn"></i>
    <h2>회원 정보 수정</h2>
    <div class="form-area">
      <!-- Form area -->
      <form action="#" class="form">
        <div class="form-group">
          <label for="memId">아이디</label>
          <input type="text" name="memId" id="memId" maxlength="30" disabled="disabled" />
        </div>
        <br>
        <div class="form-group">
          <label for="password">비밀번호</label>
          <input type="password" name="password" id="password" maxlength="30" />
        </div>
        <br>
        <div class="form-group">
          <label for="name">이름</label>
          <input type="text" name="name" id="name" maxlength="7" />
        </div>
        <br>
        <div class="form-group">
          <label for="email">이메일</label>
          <input type="email" name="email" id="email" maxlength="320" >
        </div>
        <br>
        <div class="form-group">
          <label for="phone">전화번호</label>
          <input type="text" name="phone" id="phone" maxlength="15" >
        </div>
        <br>
        <div class="form-group">
          <label for="jumin">주민번호</label>
          <input type="text" name="jumin" id="jumin" maxlength="15" >
        </div>
        <br>
        <div class="form-group">
          <label for="memDiv">사용자 구분</label>
          <input type="text" name="memDiv" id="memDiv" maxlength="5" >
        </div>
      </form>
      <!-- Button area -->
      <div class="button-area">
        <input type="button" id="doUpdate" value="수정">
        <input type="button" id="doDelete" value="삭제">
      </div>
      <!-- // Button area -->
    </div>
    <!-- // Form area -->
  </div>
</div>