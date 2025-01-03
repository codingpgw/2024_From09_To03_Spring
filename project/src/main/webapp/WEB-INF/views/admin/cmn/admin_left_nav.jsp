<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<nav class="left-nav">
  <div class="admin-page">
    <h2>관리자 페이지</h2>
  </div>
  <div class="left-nav-menu active" id="showHospitalPage" data-path="adminRetrieveHospital">
    <i class="fa-regular fa-hospital"></i>
    <p>병원 관리</p>
  </div> 
  <div class="left-nav-menu" id="showMemberPage" data-path="adminRetrieveMember">
    <i class="fa-regular fa-user"></i>
    <p>회원 관리</p>
  </div>
  <div class="left-nav-menu" id="showCommunityPage" data-path="adminRetrieveCommunity">
    <i class="fa-regular fa-newspaper"></i>
    <p>커뮤니티 관리</p>
  </div>
  <div class="left-nav-menu logout-btn" id="logOutBtn">
    <i class="fa-solid fa-arrow-right-from-bracket"></i>
    <p>로그아웃</p>
  </div>
</nav>