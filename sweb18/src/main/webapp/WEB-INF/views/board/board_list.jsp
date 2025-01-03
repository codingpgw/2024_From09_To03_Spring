<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%@page import="com.pcwk.ehr.cmn.SearchVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="CP" value="${pageContext.request.contextPath }" />
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="sysDate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd_HH:mm:ss" /></c:set> 
<%  
    int bottomCount = 10;
    int pageSize    = 10;
    int pageNo      = 1;
    
    int maxNum      = Integer.parseInt(request.getAttribute("totalCnt").toString());//총 글수
    
    //out.print("****:"+maxNum);
    SearchVO paramVO  = (SearchVO)request.getAttribute("search");
    pageSize = paramVO.getPageSize();
    pageNo = paramVO.getPageNo();
    
    //out.print("pageSize****:"+pageSize);
    //out.print("pageNo****:"+pageNo);
    
    String cp = request.getContextPath();
    String pageHtml = StringUtil.renderingPager(maxNum, pageNo, pageSize, bottomCount, cp+"/board/doRetrieve.do", "pageDoRetrieve");
    //out.print("pageHtml:<br>"+pageHtml);
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${CP}/resources/assets/css/board/list.css?date=${sysDate}">
<link rel="stylesheet" href="/ehr/resources/assets/css/main/main.css?date=${sysDate}">
<meta charset="UTF-8">
<link rel="shortcut icon" href="${CP}/resources/images/favicon.ico" type="image/x-icon" >
<script src="${CP}/resources/assets/js/jquery_3_7_1.js"></script>
<script src="${CP}/resources/assets/js/cmn/common.js?date=${sysDate}"></script>
<script src="${CP}/resources/assets/js/board/board_list.js?date=${sysDate}"></script>


<title>게시판 목록</title>
</head>
<body>
<div id="container">
    <!-- header-->
    <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
    <!--// header-------------------------------------------------->

    <!-- aside-->
    <jsp:include page="/WEB-INF/views/include/aside.jsp"></jsp:include>
    <!--// aside--------------------------------------------------->
    <main  id="contents">
        <c:choose>
            <c:when test="${'10' == board_div}"><h2>공지사항</h2></c:when>
            <c:when test="${'20' == board_div}"><h2>게시판</h2></c:when>
            <c:otherwise><h2>공지사항</h2></c:otherwise>
        </c:choose>
	<div class="container">
	    <hr class="title-underline">
	   
	    <!-- 검색조건   이름  이메일-->  
	    <form action="#" name="userForm" id="userForm" method="get" class="search-form" enctype="application/x-www-form-urlencoded">
	        <input type="hidden" name="pageNo" id="pageNo" >
	        <input type="hidden" name="div" id="div" value="${board_div}">
	        <div class="search-group">
	            <label for="searchDiv">구분</label>
	            <select name="searchDiv" id="searchDiv">
	                <option value="">전체</option>
	                <option value="10" <c:if test="${ 10 ==search.searchDiv}">selected</c:if>>제목</option>
	                <option value="20" <c:if test="${ 20 ==search.searchDiv}">selected</c:if> >내용</option>  
	                <option value="30" <c:if test="${ 30 ==search.searchDiv}">selected</c:if> >제목+내용</option>
	            </select>
	            <input type="search" name="searchWord" id="searchWord" value="${search.searchWord}">
	            <select name="pageSize" id="pageSize">
	                <option value="10"  <c:if test="${ 10 ==search.pageSize}">selected</c:if> >10</option>
	                <option value="20"  <c:if test="${ 20 ==search.pageSize}">selected</c:if>  >20</option>
	                <option value="30"  <c:if test="${ 30 ==search.pageSize}">selected</c:if>  >30</option>
	                <option value="50"  <c:if test="${ 50 ==search.pageSize}">selected</c:if> >50</option>
	                <option value="100" <c:if test="${ 100 ==search.pageSize}">selected</c:if> >100</option>
	            </select> 
	            <input type="button" value="조회" id="doRetrieveBtn"> 
	            <input type="button" value="등록" id="moveToRegBtn">                       
	        </div>            
	    </form>
	    <!--// 검색조건 -->
	    <%--java comment : html 소스보기(안 보임)
	    /ehr/board/doSelectOne.do?div= &seq=
	     --%>
	    <table border="1"  id="listTable"  class="table">
	        <colgroup>
	           <col width="10%">
	           <col width="60%">
	           <col width="10%">
	           <col width="10%">
	           <col width="10%">
	           <col width="0%">
	        </colgroup>
	        <thead>
	            <th class="table-head">번호</th>
	            <th class="table-head">제목</th>
	            <th class="table-head">글쓴이</th>
	            <th class="table-head">작성일</th>
	            <th class="table-head">조회수</th>
	            <th style="display:none;">SEQ</th>
	        </thead>
	        <tbody>  
	                <c:choose>
	                    <c:when test="${list.size()>0}">
	                        <c:forEach var="vo" items="${list}">
								<tr>
									<td class="table-cell text-center"><c:out value="${vo.no}" escapeXml="true"/></td>
									<td class="table-cell text-left highlight"><a href="/ehr/board/doSelectOne.do?div=${board_div}&seq=${vo.seq}"><c:out value="${vo.title}" escapeXml="true"/></a></td>
									<td class="table-cell text-center"><c:out value="${vo.modId}" escapeXml="true"/></td>
									<td class="table-cell text-left"><c:out value="${vo.modDt}" escapeXml="true"/></td>
									<td class="table-cell text-right"><c:out value="${vo.readCnt}" escapeXml="true"/></td>
									<td class="table-cell text-right" style="display:none;"><c:out value="${vo.seq}" escapeXml="true"/></td>
								</tr>
							</c:forEach>               
	                    </c:when>
	                    
	                    <c:otherwise>
	                        <td colspan="99" class="table-cell text-center">조회된 데이터가 없습니다.</td>
	                    </c:otherwise>
	                </c:choose>
	        </tbody>
	
	    </table>
	</div>
	    <%
        out.print(pageHtml);
        %>	
	</main>
<!-- footer-->
    <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
    <!--// footer-------------------------------------------------->
</div>
</body>
</html>