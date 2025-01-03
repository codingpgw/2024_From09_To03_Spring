<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<header id="header">
  <h2>YAMU</h2>
  <nav>
    <ul>
      <li><a href="${CP}/member/doRetrieve.do">회원관리</a></li>
      <li><a href="${CP}/resources/assets/js/member/list_template.html#">목록-템플릿</a></li>
      <li><a href="${CP}/resources/assets/js/member/reg_template.html#">등록-템플릿</a></li>
	    <li><a href="#">게시판</a></li>
	    <c:choose>
		    <c:when test="${empty sessionScope.member.name}">
		      <li><a href="${CP}/login/login_index.do">로그인</a></li>
		    </c:when>
		    <c:otherwise>
		      <li><a href="${CP}/login/logout.do">로그아웃</a></li>
		    </c:otherwise>
	    </c:choose>
    </ul>
  </nav>
</header>