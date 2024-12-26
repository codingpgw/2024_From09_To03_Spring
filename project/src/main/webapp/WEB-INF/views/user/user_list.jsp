<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%@page import="com.pcwk.ehr.cmn.SearchVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>

<%
/**
 * 
 * @param maxNum : 총 글수
 * @param currentPageNo : 현재 페이지 번호
 * @param rowPerpage : 페이지 사이즈(10,20,...100)
 * @param bottomCount : 10/5
 * @param url : 서버 호출 URL
 * @param scriptName : 자바스크립트 함수명
 * @return "html 텍스트"
 */
//public static String renderingPager(int maxNum, int currentPageNo, int rowPerpage, int bottomCount, String url, String scriptName) {
    
    int bottomCount = 10;
    int pageSize    = 10;
    int pageNo      = 1;
    
    int maxNum      = Integer.parseInt(request.getAttribute("totalCnt").toString());//총 글수
    
    //out.print("****:"+maxNum);
    SearchVO paramVO  = (SearchVO)request.getAttribute("search");//search
    pageSize = paramVO.getPageSize();
    pageNo = paramVO.getPageNo();
    
    //out.print("pageSize****:"+pageSize);
    //out.print("pageNo****:"+pageNo);
    
    String cp = request.getContextPath();
    String pageHtml = StringUtil.renderingPager(maxNum, pageNo, pageSize, bottomCount, cp+"/user/doRetrieve.do", "pageDoRetrieve");
    //out.print("pageHtml:<br>"+pageHtml);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/ehr/resources/assets/css/main/main.css">
<link rel="stylesheet" href="/ehr/resources/assets/css/user/list.css">
<script src="/ehr/resources/assets/js/jquery_3_7_1.js"></script>
<script src="/ehr/resources/assets/js/cmn/common.js"></script>
<script src="/ehr/resources/assets/js/user/user_list.js"></script>
<title>회원목록</title>
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
      <div  class="main-container">
        <h2>회원목록</h2>
        <hr class="title-underline">
       
        <!-- 검색조건   이름  이메일-->  
        <form action="#" class="search-form" name="userForm" id="userForm" method="get" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="pageNo" id="pageNo" >
            <div class="search-group">
                <label for="searchDiv">구분</label>
                <select name="searchDiv" id="searchDiv">  
                    <option value="">전체</option>
                    <option value="10" <c:if test="${ 10 ==search.searchDiv}">selected</c:if> >회원ID</option>
                    <option value="20" <c:if test="${ 20 ==search.searchDiv}">selected</c:if> >이름</option>  
                    <option value="30" <c:if test="${ 30 ==search.searchDiv}">selected</c:if> >이메일</option>
                </select>
                <input type="search" name="searchWord" id="searchWord" value="${search.searchWord}">
                <select name="pageSize" id="pageSize">
                    <option value="10"  <c:if test="${ 10 ==search.pageSize}">selected</c:if>  >10</option>
                    <option value="20"  <c:if test="${ 20 ==search.pageSize}">selected</c:if>  >20</option>
                    <option value="30"  <c:if test="${ 30 ==search.pageSize}">selected</c:if>  >30</option>
                    <option value="50"  <c:if test="${ 50 ==search.pageSize}">selected</c:if>  >50</option>
                    <option value="100" <c:if test="${ 100 ==search.pageSize}">selected</c:if>  >100</option>
                </select>            
                <input type="button" value="조회" id="doRetrieveBtn"> 
                <input type="button" value="등록" id="moveToRegBtn">
            </div>            
        </form>
        <table border="1" id="listTable" class="table">
            <thead>
                <th class="table-head">번호</th>
                <th class="table-head">회원ID</th>
                <th class="table-head">비밀번호</th>
                <th class="table-head">이름</th>
                <th class="table-head">이메일</th>
                <th class="table-head">전화번호</th>  
                <th class="table-head">주민번호</th>
                <th class="table-head">구분</th>
                <th class="table-head">등록일</th>
            </thead>
            <tbody>
                <c:choose>
                   <c:when test="${not empty list}"> 
                   <c:forEach var="vo" items="${list}">
                     <tr title="더블클릭하면 상세 정보를 볼 수 있습니다.">
                        <td class="table-cell text-center">${vo.getNo() }</td>
                        <td class="table-cell text-left highlight">${vo.mem_id }</td>
                        <td class="table-cell text-center">${vo.mem_password}</td>
                        <td class="table-cell text-left">${vo.mem_name }</td>
                        <td class="table-cell text-center">${vo.mem_email}</td>
                        <td class="table-cell text-right">${vo.mem_phonenum}</td>
                        <td class="table-cell text-right">${vo.mem_jumin}</td>
                        <td class="table-cell text-center ">${vo.mem_div }</td>
                        <td class="table-cell text-center ">${vo.mem_regdt }</td>
                    </tr>
                   </c:forEach>
                   </c:when>
                   
                   <c:otherwise>
                       <tr>
                           <td colspan="99" class="table-cell text-center">조회된 데이터가 없습니다.</td>
                       </tr>
                   </c:otherwise>
                </c:choose>
                
            </tbody>
    
        </table>
        <!-- paging -->
        <%
        out.print(pageHtml);
        %>
        <!-- //paging -->
      </div>
    </main>
    <!-- footer-->
    <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
    <!--// footer-------------------------------------------------->
</div>
</body>
</html>