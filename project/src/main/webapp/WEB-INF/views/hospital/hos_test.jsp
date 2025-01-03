<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%@page import="com.pcwk.ehr.cmn.SearchVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>

<%
    
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
    String pageHtml = StringUtil.renderingPager(maxNum, pageNo, pageSize, bottomCount, cp+"/hospital/doRetrieve.do", "pageDoRetrieve");
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
<script src="/ehr/resources/assets/js/hospital/hospital_list.js?date=${sysDate}"></script>
<title>회원목록</title>
</head>
<body>
<div id="container">
    <!-- header-->
    <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
    <!--// header-------------------------------------------------->

    <!-- aside-->
    <aside id="sidebar">
	  <h2>aside</h2>
	  <ul>
	      <li><a href="/ehr/hospital/doRetrieve.do?pageNo=1&searchDiv=20&searchWord=한의원&pageSize=10">한의원</a></li>
		  <li><a href="/ehr/hospital/doRetrieve.do?pageNo=1&searchDiv=20&searchWord=의원&pageSize=10">의원</a></li>
		  <li><a href="/ehr/hospital/doRetrieve.do?pageNo=1&searchDiv=20&searchWord=치과병원&pageSize=10">치과병원</a></li>
		  <li><a href="/ehr/hospital/doRetrieve.do?pageNo=1&searchDiv=20&searchWord=치과의원&pageSize=10">치과의원</a></li>
		  <li><a href="/ehr/hospital/doRetrieve.do?pageNo=1&searchDiv=20&searchWord=한방병원&pageSize=10">한방병원</a></li>
		  <li><a href="/ehr/hospital/doRetrieve.do?pageNo=1&searchDiv=20&searchWord=종합병원&pageSize=10">종합병원</a></li>
	  </ul>
	</aside>
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
                    <option value="10" <c:if test="${ 10 ==search.searchDiv}">selected</c:if> >병원 이름</option>
                    <option value="20" <c:if test="${ 20 ==search.searchDiv}">selected</c:if> >진료과목</option>  
                    <option value="30" <c:if test="${ 30 ==search.searchDiv}">selected</c:if> >지역</option>
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
                <th class="table-head">병원 이름</th>
                <th class="table-head">병원 주소</th>
                <th class="table-head">전화번호</th>
                <th class="table-head">경도</th>
                <th class="table-head">위도</th>  
                <th class="table-head">진료과</th>
                <th class="table-head">설명</th>
                <th style="display:none;">병원번호</th>
            </thead>
            <tbody>
                <c:choose>
                   <c:when test="${list.size()>0}"> 
                   <c:forEach var="vo" items="${list}">
                     <tr title="더블클릭하면 상세 정보를 볼 수 있습니다.">
                        <td class="table-cell text-center"><c:out value="${vo.no}" escapeXml="true"/></td>
                        <td class="table-cell text-left highlight"><c:out value="${vo.hospital_name}" escapeXml="true"/></td>
                        <td class="table-cell text-center"><c:out value="${vo.hospital_addr}" escapeXml="true"/></td>
                        <td class="table-cell text-left"><c:out value="${vo.hospital_tel}" escapeXml="true"/></td>
                        <td class="table-cell text-center"><c:out value="${vo.hospital_lon}" escapeXml="true"/></td>
                        <td class="table-cell text-right"><c:out value="${vo.hospital_lat}" escapeXml="true"/></td>
                        <td class="table-cell text-center "><c:out value="${vo.hospital_div}" escapeXml="true"/></td>
                        <td class="table-cell text-center "><c:out value="${vo.hospital_etc}" escapeXml="true"/></td>
                        <td class="table-cell text-center" style="display:none;"><c:out value="${vo.hospital_id}" escapeXml="true"/></td>
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