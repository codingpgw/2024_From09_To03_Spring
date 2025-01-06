<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${CP}/resources/assets/css/admin/admin_form.css?date=<%=new Date()%>">
<script src="${CP}/resources/assets/js/admin/hospital/admin_hospital_reg.js?date=<%=new Date()%>"></script>
<div id="hospitalRegmodal" class="modal">
  <div class="modal-content">
    <i class="fa-solid fa-xmark close-btn"></i>
    <h2>병원 등록</h2>
    <div class="form-area">
      <!-- Form area -->
      <form action="#" class="form">
        <div class="form-group">
          <label for="hospital_id">병원 아이디</label>
          <input type="text" name="hospital_id" id="hospital_id" maxlength="30">
        </div>
        <br>
        <div class="form-group">
          <label for="hospital_name">병원 이름</label>
          <input type="text" name="hospital_name" id="hospital_name" maxlength="30">
        </div>
        <br>
        <div class="form-group">
          <label for="hospital_addr">병원 주소</label>
          <input type="text" name="hospital_addr" id="hospital_addr" maxlength="100">
        </div>
        <br>
        <div class="form-group">
          <label for="hospital_div">병원 분류</label>
          <input type="text" name="hospital_div" id="hospital_div" maxlength="20">
        </div>
        <br>
        <div class="form-group">
          <label for="hospital_etc">특이사항</label>
          <input type="text" name="hospital_etc" id="hospital_etc" maxlength="100">
        </div>
        <br>
        <div class="form-group">
          <label for="hospital_mapimg">간이 약도</label>
          <input type="text" name="hospital_mapimg" id="hospital_mapimg" maxlength="100">
        </div>
        <br>
        <div class="form-group">
          <label for="hospital_tel">전화번호</label>
          <input type="text" name="hospital_tel" id="hospital_tel" maxlength="20">
        </div>
        <div class="form-group">
          <label for="hospital_lon">위도</label>
          <input type="text" name="hospital_lon" id="hospital_lon" maxlength="30">
        </div>
        <div class="form-group">
          <label for="hospital_lat">경도</label>
          <input type="text" name="hospital_lat" id="hospital_lat" maxlength="30">
        </div>
        <div class="form-group">
          <label for="hospital_time_mon">진료시간 - 월요일</label>
          <input type="text" name="hospital_time_mon" id="hospital_time_mon" maxlength="30">
        </div>
        <div class="form-group">
          <label for="hospital_time_tue">진료시간 - 화요일</label>
          <input type="text" name="hospital_time_tue" id="hospital_time_tue" maxlength="30">
        </div>
        <div class="form-group">
          <label for="hospital_time_wed">진료시간 - 수요일</label>
          <input type="text" name="hospital_time_wed" id="hospital_time_wed" maxlength="30">
        </div>
        <div class="form-group">
          <label for="hospital_time_thu">진료시간 - 목요일</label>
          <input type="text" name="hospital_time_thu" id="hospital_time_thu" maxlength="30">
        </div>
        <div class="form-group">
          <label for="hospital_time_fri">진료시간 - 금요일</label>
          <input type="text" name="hospital_time_fri" id="hospital_time_fri" maxlength="30">
        </div>
        <div class="form-group">
          <label for="hospital_time_sat">진료시간 - 토요일</label>
          <input type="text" name="hospital_time_sat" id="hospital_time_sat" maxlength="30">
        </div>
        <div class="form-group">
          <label for="hospital_time_sun">진료시간 - 일요일</label>
          <input type="text" name="hospital_time_sun" id="hospital_time_sun" maxlength="30">
        </div>
        <div class="form-group">
          <label for="hospital_time_hol">진료시간 - 공휴일</label>
          <input type="text" name="hospital_time_hol" id="hospital_time_hol" maxlength="30">
        </div>
      </form>
      <!-- Button area -->
      <div class="button-area">
        <input type="button" id="doSave" value="등록">
      </div>
      <!-- // Button area -->
    </div>
    <!-- // Form area -->
  </div>
</div>