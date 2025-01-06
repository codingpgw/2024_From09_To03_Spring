<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${CP}/resources/assets/css/admin/admin_form.css?date=<%=new Date()%>">
<script src="${CP}/resources/assets/js/admin/community/admin_community_mng.js?date=<%=new Date()%>"></script>
<div id="communityMngModal" class="modal">
  <div class="modal-content">
    <i class="fa-solid fa-xmark close-btn"></i>
    <h2>커뮤니티 정보 수정</h2>
    <div class="form-area">
      <!-- Form area -->
      <form action="#" class="form">
        <input type="hidden" name="setCmnCategory" id="setCmnCategory" value="${cmn_category}">
        <input type="hidden" name="setCmnDiv" id="setCmnDiv" value="${cmn_div}">
        <div class="form-group">
          <label for="cmnNo">번호</label>
          <input type="text" name="cmnNo" id="cmnNo" maxlength="30" disabled="disabled">
        </div>
        <br>
        <div class="form-group">
          <label for="title">제목</label>
          <input type="text" name="title" id="title" maxlength="30">
        </div>
        <br>
        <div class="form-group">
          <label for="view">조회수</label>
          <input type="text" name="view" id="view" maxlength="20" disabled="disabled">
        </div>
        <br>
        <div class="form-group">
          <label for="memId">등록자</label>
          <input type="email" name="memId" id="memId" maxlength="10" disabled="disabled">
        </div>
        <br>
        <div class="form-group">
          <label for="regDt">등록일</label>
          <input type="text" name="regDt" id="regDt" maxlength="20" disabled="disabled">
        </div>
        <br>
        <div class="form-group">
          <label for="modDt">수정일</label>
          <input type="text" name="modDt" id="modDt" maxlength="20" disabled="disabled">
        </div>
        <br>
        <div class="form-group">
          <label for="content">내용</label>
          <textarea class="content" name="content" id="content" placeholder="내용을 입력하세요"></textarea>
        </div>
      </form>
      <!-- Button area -->
      <div class="button-area">
        <input type="button" id="doUpdate" value="수정">
        <input type="button" id="doDelete" value="삭제">
      </div>
      <!--/ Button area -->
    </div>
    <!--/ Form area -->
  </div>
</div>