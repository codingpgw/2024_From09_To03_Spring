<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${CP}/resources/assets/css/admin/admin_form.css?date=<%=new Date()%>">
<script src="${CP}/resources/assets/js/admin/community/admin_community_reg.js?date=<%=new Date()%>"></script>
<div id="communityRegModal" class="modal">
  <div class="modal-content">
    <i class="fa-solid fa-xmark close-btn"></i>
    <h2>커뮤니티 글 등록</h2>
    <div class="form-area">
      <!-- Form area -->
      <form action="#" class="form">
        <input type="hidden" name="setCmnCategory" id="setCmnCategory" value="${cmn_category}">
        <input type="hidden" name="setCmnDiv" id="setCmnDiv" value="${cmn_div}">
        <div class="form-group">
          <label for="titleReg">제목</label>
          <input type="text" name="titleReg" id="titleReg" maxlength="30">
        </div>
        <div class="form-group">
          <label for="memIdReg">등록자</label>
          <input type="text" name="memIdReg" id="memIdReg" maxlength="10" disabled="disabled" value="${sessionScope.member.memId}">
        </div>
        <div class="form-group">
          <label for="contentReg">내용</label>
          <textarea class="content" name="contentReg" id="contentReg" placeholder="내용을 입력하세요"></textarea>
        </div>
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