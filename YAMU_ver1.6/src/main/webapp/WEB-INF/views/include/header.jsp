<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<div class="nav-container">
  <ul class="top-nav">
    <li><a href="${CP}/main/main.do" class="logo">병원모아</a></li>
    <li><a href="${CP}/community/doRetrieve.do" class="category-menu">공지사항</a></li>
    <li><a href="${CP}/hospital/doRetrieve.do" class="category-menu" >병원 목록</a></li>
    <!-- <li><a href="#" class="category-menu">우리동네인기병원</a></li> -->
    <li><a href="${CP}/community/doRetrieve.do" class="category-menu">커뮤니티</a></li>
    <li><a href="${CP}/qna/doRetrieve.do" class="category-menu">Q&amp;A</a></li>
    <li><a href="${CP}/member/doSelectOne.do" class="category-menu">마이페이지</a></li>
    <c:choose>
        <c:when test="${empty sessionScope.member}">
          <li><a href="${CP}/login/login_index.do">로그인</a></li>
        </c:when>
        <c:otherwise>
          <li><a href="${CP}/login/logout.do">로그아웃</a></li>
        </c:otherwise>
      </c:choose>
  </ul>
</div>