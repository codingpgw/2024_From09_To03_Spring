<%@page import="java.util.Date"%>
<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%@page import="com.pcwk.ehr.cmn.SearchVO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
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
  String pageHtml = StringUtil.renderingPager(maxNum, pageNo, pageSize, bottomCount, cp + "/community/doRetrieve.do", "pageDoRetrieve");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="${CP}/resources/assets/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="${CP}/resources/assets/css/main/home.css?date=<%=new Date()%>">
<link rel="stylesheet" href="${CP}/resources/assets/css/community/community.css?date=<%=new Date()%>">
<title>Insert title here</title>
<script src="${CP}/resources/assets/js/jquery_3_7_1.js"></script>
<script src="${CP}/resources/assets/js/cmn/common.js"></script>
<script src="${CP}/resources/assets/js/community/community_list.js?date=<%=new Date()%>"></script>
</head>
<body>
  <div class="main-container">
    <div class="nav-container">
      <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
    </div>
    <div class="main-content-container">
      <div id="memberPage" class="content-page" style="display: block;">
        <div class="content-title">
          <c:choose>
            <c:when test="${cmn_div == '10'}"><h2>공지사항</h2></c:when>
            <c:when test="${cmn_div == '20'}"><h2>커뮤니티</h2></c:when>
          </c:choose>
        </div>
        <!-- 검색조건 이름 이메일 -->
        <form action="#" class="search-form" name="memberForm" id="memberForm" method="get" enctype="application/x-www-form-urlencoded">
          <input type="hidden" name="pageNo" id="pageNo">
          <div class="search-group">
            <select name="searchDiv" id="searchDiv">
              <option value="">전체</option>
              <option value="10" <c:if test="${10 ==search.searchDiv}">selected</c:if>>제목</option>
              <option value="20" <c:if test="${20 ==search.searchDiv}">selected</c:if>>내용</option>
              <option value="30" <c:if test="${30 ==search.searchDiv}">selected</c:if>>제목 + 내용</option>
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
        <table id="listTable" class="data-table">
          <colgroup>
            <col width="10%">
            <col width="60%">
            <col width="10%">
            <col width="10%">
            <col width="10%"> 
            <col width="0%">           
          </colgroup>
          <thead>
            <tr>
              <th>번호</th>
              <th>제목</th>
              <th>글쓴이</th>
              <th>등록일</th>
              <th>수정일</th>
              <th>조회수</th>
              <th style="display: none;">CMN_NO</th>
            </tr>
          </thead>
          <tbody>
            <c:choose>
              <c:when test="${list.size() > 0}">
                <c:forEach var="vo" items="${list}">
                  <tr title="더블클릭하면 상세 정보를 볼 수 있습니다.">
                    <th class="table-cell text-center">${vo.getNo()}</th>
                    <td class="table-cell text-left highlight">${vo.title}</td>
                    <td class="table-cell text-center">${vo.memId}</td>
                    <td class="table-cell text-center">${vo.regDt}</td>
                    <td class="table-cell text-center">${vo.modDt}</td>
                    <td class="table-cell text-right">${vo.view}</td>
                    <td class="table-cell text-right" style="display: none;">${vo.cmnNo}</td>
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
        <%out.print(pageHtml);%>
      </div> <!-- //mng-user-container -->
    </div> <!-- //main-content-container -->
  </div> <!-- //main-container -->
</body>
</html>