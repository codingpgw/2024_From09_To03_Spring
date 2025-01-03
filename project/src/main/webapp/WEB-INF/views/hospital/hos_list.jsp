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
    SearchVO paramVO  = (SearchVO)request.getAttribute("search");
    pageSize = paramVO.getPageSize();
    pageNo = paramVO.getPageNo();
    
    //out.print("pageSize****:"+pageSize);
    //out.print("pageNo****:"+pageNo);
    
    String cp = request.getContextPath();
    String pageHtml = StringUtil.renderingPager(maxNum, pageNo, pageSize, bottomCount, cp+"/hospital/doRetrieve.do", "pageDoRetrieve");
    //out.print("pageHtml:<br>"+pageHtml);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>hospital_list</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css" integrity="sha512-5Hs3dF2AEPkpNAR7UiOHba+lRSJNeM2ECkwxUIxC1Q/FLycGTbNapWXB4tP889k5T5Ju8fs4b1P5z/iB4nMfSQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" href="/ehr/resources/assets/css/hospital/holist_list.css?date=${sysDate}">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  
  <script src="/ehr/resources/assets/js/hospital/hospital_list.js?date=${sysDate}"></script>
</head>
<body>
 
</body>
</html>