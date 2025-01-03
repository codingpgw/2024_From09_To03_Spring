<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%@page import="com.pcwk.ehr.cmn.SearchVO"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
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
  String pageHtml = StringUtil.renderingPager(maxNum, pageNo, pageSize, bottomCount, cp + "/member/doRetrieve.do", "pageDoRetrieve");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="${CP}/resources/assets/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="${CP}/resources/assets/css/member/list.css?date=<%=new Date()%>">
<link rel="stylesheet" href="${CP}/resources/assets/css/main/main.css?date=<%=new Date()%>">
<title>회원 목록</title>
<script src="${CP}/resources/assets/js/jquery_3_7_1.js"></script>
<script src="${CP}/resources/assets/js/cmn/common.js"></script>
<script src="${CP}/resources/assets/js/member/member_list.js?date=<%=new Date()%>"></script>
</head>
<body>
  <div id="container">
    <!-- header---------------------------------------------------->
    <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
    <!--// header-------------------------------------------------->
    <!-- aside----------------------------------------------------->
    <jsp:include page="/WEB-INF/views/include/aside.jsp"></jsp:include>
    <!--// aside--------------------------------------------------->
    <main id="contents">
      <div class="main-container">
				<h2>회원 목록</h2>
				<hr class="title-underline" />
				<!-- 검색조건 이름 이메일 -->
				<form action="#" class="search-form" name="memberForm" id="memberForm" method="get" enctype="application/x-www-form-urlencoded">
		      <input type="hidden" name="pageNo" id="pageNo">
		      <div class="search-group">
		        <label for="searchDiv">구분</label>
		        <select name="searchDiv" id="searchDiv">
		          <option value="">전체</option>
		          <option value="10" <c:if test="${10 ==search.searchDiv}">selected</c:if>>회원ID</option>
		          <option value="20" <c:if test="${20 ==search.searchDiv}">selected</c:if>>이름</option>
		          <option value="30" <c:if test="${30 ==search.searchDiv}">selected</c:if>>이메일</option>
		        </select>
		        <input type="search" name="searchWord" id="searchWord" value="${search.searchWord}">
		        <select name="pageSize" id="pageSize">
		          <option value="10" <c:if test="${10 ==search.pageSize}">selected</c:if>>10</option>
		          <option value="20" <c:if test="${20 ==search.pageSize}">selected</c:if>>20</option>
		          <option value="30" <c:if test="${30 ==search.pageSize}">selected</c:if>>30</option>
		          <option value="50" <c:if test="${50 ==search.pageSize}">selected</c:if>>50</option>
		          <option value="100" <c:if test="${100 ==search.pageSize}">selected</c:if>>100</option>
		        </select>
		        <input type="button" value="조회" id="doRetrieveBtn">
		        <input type="button" value="등록" id="moveToRegBtn">
		      </div>
		    </form>
				<!-- // 검색조건 -->
				<table border="1" id = "listTable" class="table">
					<thead>
						<tr>
							<th class="table-head">번호</th>
							<th class="table-head">회원ID</th>
							<th class="table-head">이름</th>
							<th class="table-head">이메일</th>
							<th class="table-head">전화번호</th>
							<th class="table-head">주민번호</th>
							<th class="table-head">사용자 구분</th>
							<th class="table-head">등록일</th>
						</tr>
					</thead>
					<tbody>
					  <c:choose>
					    <c:when test="${list.size() > 0}">
								<c:forEach var="vo" items="${list}">
									<tr title="더블클릭하면 상세 정보를 볼 수 있습니다.">
										<td class="table-cell text-center">${vo.getNo()}</td>
										<td class="table-cell text-left highlight">${vo.memId}</td>
										<td class="table-cell text-center">${vo.name}</td>
										<td class="table-cell text-left">${vo.email}</td>
										<td class="table-cell text-center">${vo.phone}</td>
										<td class="table-cell text-right">${vo.jumin}</td>
										<td class="table-cell text-right">${vo.memDiv}</td>
										<td class="table-cell text-center">${vo.regDt}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
							  <tr>
							    <td class="table-cell text-center" colspan="99">조회된 데이터가 없습니다.</td>
							  </tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<!-- paging ------------------------------------------------->
		    <%out.print(pageHtml);%>
		    <!-- //paging ----------------------------------------------->
	    </div>
    </main>
    <!-- footer --------------------------------------------------->
    <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
    <!--// footer-------------------------------------------------->
	</div>
</body>
</html>