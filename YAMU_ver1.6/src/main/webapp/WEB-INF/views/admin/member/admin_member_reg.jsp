<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<%
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    String today = sdf.format(new java.util.Date());
%>
<link rel="stylesheet" href="${CP}/resources/assets/css/admin/admin_form.css?date=<%=new Date()%>">
<script src="${CP}/resources/assets/js/admin/member/admin_member_reg.js?date=<%=new Date()%>"></script>
<div id="memberRegModal" class="modal">
  <div class="modal-content">
    <i class="fa-solid fa-xmark close-btn"></i>
    <h2>회원 등록</h2>
    <div class="form-area">
      <!-- Form area -->
      <form action="#" class="form">
        <div class="form-group">
          <label for="memIdReg">아이디</label>
          <input type="text" name="memIdReg" id="memIdReg" maxlength="30">
        </div>
        <br>
        <div class="form-group">
          <label for="passwordReg">비밀번호</label>
          <input type="password" name="passwordReg" id="passwordReg" maxlength="30">
        </div>
        <br>
        <div class="form-group">
          <label for="nameReg">이름</label>
          <input type="text" name="nameReg" id="nameReg" maxlength="7">
        </div>
        <br>
        <div class="form-group">
          <label for="emailReg">이메일</label>
          <input type="email" name="emailReg" id="emailReg" maxlength="320">
        </div>
        <br>
        <div class="form-group">
          <label for="phoneReg">전화번호</label>
          <input type="text" name="phoneReg" id="phoneReg" maxlength="15">
        </div>
        <br>
        <div class="form-group">
          <label for="birthDtReg">생년월일</label>
          <input type="date" name="birthDtReg" id="birthDtReg" min="1900-01-01" max="<%= today %>" value="<%= today %>">
        </div>
        <br>
        <!-- <div class="form-group">
          <label for="memDivReg">사용자 구분</label>
          <input type="text" name="memDivReg" id="memDivReg" maxlength="5">
        </div> -->
      </form>
      <!-- Button area -->
      <div class="button-area">
        <input type="button" id="doSave" value="등록">
      </div>
      <!--/ Button area -->
    </div>
    <!--/ Form area -->
  </div>
</div>