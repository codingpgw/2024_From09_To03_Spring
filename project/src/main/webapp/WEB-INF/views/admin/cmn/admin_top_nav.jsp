<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<nav class="top-nav-container">
  <div class="admin-name">
    <span id="admin-name">관리자: ${sessionScope.member.name}님</span>
  </div>
</nav>