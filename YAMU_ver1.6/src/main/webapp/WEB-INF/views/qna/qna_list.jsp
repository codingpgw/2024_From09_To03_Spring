<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%@page import="com.pcwk.ehr.cmn.SearchVO"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}" />
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<%
  /**
 * @param maxNum: 총 글수
 * @param currentPageNo: 현재 페이지 번호
 * @param rowPerPage: 페이지 사이즈(10, 20, ... 100)
 * @param bottomCount: 10/5
 * @param url: 서버 호출 URL
 * @param scriptName: 자바스크립트 함수명
 * @return "html 텍스트"
 */
 //public static String renderingPager(int maxNum, int currentPageNo, int rowPerPage, int bottomCount, String url, String scriptName) {
  int bottomCount = 10;
  int pageSize = 10;
  int pageNo = 1;
  
  int maxNum = Integer.parseInt(request.getAttribute("totalCnt").toString()); // 총 글수
  
  // out.print("****:" + maxNum);
  
  SearchVO paramVO = (SearchVO) request.getAttribute("search");
  pageSize = paramVO.getPageSize();
  pageNo = paramVO.getPageNo();
  
  //out.print("****:" + pageSize);
  //out.print("****:" + pageNo);
  String cp = request.getContextPath();
  //out.print("cp****" + cp);
  String pageHtml = StringUtil.renderingPager(maxNum, pageNo, pageSize, bottomCount, cp + "/qna/doRetrieve.do", "pageDoRetrieve");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="${CP}/resources/assets/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link rel="stylesheet" href="${CP}/resources/assets/css/main/home.css?date=<%=new Date()%>">
<link rel="stylesheet" href="${CP}/resources/assets/css/qna/qna.css?date=<%=new Date()%>">
<link rel="stylesheet" href="${CP}/resources/assets/css/community/community.css?date=<%=new Date()%>">
<title>FAQ</title>
<script src="${CP}/resources/assets/js/jquery_3_7_1.js"></script>
<script src="${CP}/resources/assets/js/cmn/common.js"></script>
<script src="${CP}/resources/assets/js/qna/qna.js?date=<%=new Date()%>"></script>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<h1>고객님, 무엇을 도와드릴까요?</h1>
		<!-- 검색 폼 -->
		<div class="search-container">
			<form action="#" class="qna-search-box"name="memberForm" id="memberForm" method="get" enctype="application/x-www-form-urlencoded">
				<input type="hidden" name="pageNo" id="pageNo">
          <div class="search-group">
            <select name="searchDiv" id="searchDiv">
              <option value="">전체</option>
              <option value="10" <c:if test="${10 ==search.searchDiv}">selected</c:if>>질문</option>
              <option value="20" <c:if test="${20 ==search.searchDiv}">selected</c:if>>답변</option>
              <option value="30" <c:if test="${30 ==search.searchDiv}">selected</c:if>>질문 + 답변</option>
            </select>
						<input type="search" name="searchWord" class="search-input" placeholder="궁금한 점을 검색해보세요." />
						<select name="pageSize" id="pageSize">
              <option value="5" <c:if test="${5 ==search.pageSize}">selected</c:if>>5</option>
              <option value="10" <c:if test="${10 ==search.pageSize}">selected</c:if>>10</option>
              <option value="20" <c:if test="${20 ==search.pageSize}">selected</c:if>>20</option>
            </select>
						<button type="submit" class="search-button">
							<i class="fas fa-search"></i>
						</button>
				</div>
			</form>
		</div>

		<!-- TOP Q&A 리스트 -->
		<div class="qna-container">
			<h2 class="top-title">Q&amp;A</h2>
			<c:if test="${not empty list}">
				<c:forEach var="item" items="${list}">
					<div class="qna">
						<div class="qna-title">${item.question}</div>
						<p class="qna-text">${item.answer}</p>
						<button class="qna-toggle">
							<i class="fas fa-chevron-down"></i>
							<i class="fas fa-chevron-up"></i>
						</button>
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${empty list}">
				<p>등록된 질문이 없습니다.</p>
			</c:if>
			<%out.print(pageHtml);%>
		</div>
	</div>
</body>
</html>