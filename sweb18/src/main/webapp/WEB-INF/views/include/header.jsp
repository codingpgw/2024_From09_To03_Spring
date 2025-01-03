<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<!-- 회원관리 게시판 목록-템플릿 리스트-템플릿 -->
<header id="header">
       <h2>PCWK</h2>
       <nav>
        <ul>
          <li><a href="${CP}/user/doRetrieve.do">회원관리</a></li>
          <li><a href="${CP}/board/doRetrieve.do?div=10">공지사항</a></li>
          <li><a href="${CP}/board/doRetrieve.do?div=20">게시판</a></li>
          <li><a href="/ehr/resources/assets/user/reg_template.html">등록-템플릿</a></li>
          <li><a href="${CP}/resources/assets/user/list_template.html">리스트-템플릿</a></li>
          <c:choose>
            <c:when test="${empty sessionScope.user.name}">
                <li><a href="${CP}/login/login_index.do">로그인</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${CP}/login/logout.do">로그아웃</a></li>
            </c:otherwise>
          </c:choose>
        </ul>
        
       </nav>
</header>