<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="CP" value="${pageContext.request.contextPath }" />
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="sysDate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd_HH:mm:ss" /></c:set> 

<!DOCTYPE html>
<html>  
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="${CP}/resources/images/favicon.ico" type="image/x-icon" >
<title>게시글 등록</title>
<link rel="stylesheet" href="${CP}/resources/assets/css/community/form.css?date=${sysDate}">
<link rel="stylesheet" href="/ehr/resources/assets/css/main/main.css?date=${sysDate}">

<script src="${CP}/resources/assets/js/jquery_3_7_1.js"></script>
<script src="${CP}/resources/assets/js/cmn/common.js"></script>
<script src="${CP}/resources/assets/js/community/community_mng.js?date=${sysDate}"></script>
</head>
<body>     
    <div class="form-container">
    <h2>게시글 등록</h2>     
    <hr class="title-underline">
       
    <!-- Button area -->
    <div  class="button-area">
        <input type="button" id="moveToList"    value="목록">
        <input type="button" id="doUpdateBtn"   value="수정">
        <input type="button" id="doDeleteBtn"   value="삭제">
    </div>
    <!--// Button area -->

    <!-- form area -->
    <form action="#" method="post" >
        <input type="hidden" name="cmnNo" id="cmnNo" value="${vo.cmnNo}">
        <input type="hidden" name="cmn_div" id="cmn_div" value="${vo.cmn_div}">
        <div class="form-group">
            <label for="userId">제목</label>
            <input value="${vo.cmnTitle }" type="text"  maxlength="50" name="cmnTitle" id="cmnTitle" >
        </div>
             
         <div class="form-group">
            <label for="readCnt">조회수</label>
            <input value="${vo.cmnView }"  type="text"  maxlength="9" name="cmnView" id="cmnView" disabled="disabled" >
        </div>   
        
        <div class="form-group">
            <label for="name">등록자</label>
            <input value="${vo.memId }" type="text"  maxlength="30" name="memId" id="memId" disabled="disabled">
        </div>   
        
        <div class="form-group">
            <label for="regDt">등록일</label>
            <input value="${vo.cmnRegDt }" type="text"  maxlength="13" name="cmnRegDt" id="cmnRegDt" disabled="disabled">
        </div>   
        
        <div class="form-group">
            <label for="modDt">수정일</label>
            <input value="${vo.cmnModDt }" type="text"  maxlength="13" name="cmnModDt" id="cmnModDt" disabled="disabled">
        </div>   
        
    
        <div class="form-group">
            <label  for="cmnContent">내용</label>
            <textarea class="contents" id="cmnContent" name="cmnContent" placeholder="내용을 입력하세요"><c:out value="${vo.cmnContent}"></c:out></textarea>
        </div>                                
    </form>
    <!--// form area -->
  </div>  
</body>
</html>