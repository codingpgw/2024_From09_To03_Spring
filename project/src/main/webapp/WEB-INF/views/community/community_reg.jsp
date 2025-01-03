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
<script src="${CP}/resources/assets/js/community/community_reg.js?date=${sysDate}"></script>
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
    <div class="form-container">
    <h2>게시글 등록</h2>     
    <hr class="title-underline">
       
    <!-- Button area -->
    <div  class="button-area">
        <input type="button" id="moveToList" value="목록">
        <input type="button" id="doSave"     value="등록">
    </div>
    <!--// Button area -->
    
    <!-- form area -->
    <form action="/ehr/user/doSave.do" method="post" >
        <input type="hidden" name="cmn_div" id="cmn_div" value="${cmnDiv}">
        <div class="form-group">
            <label for="cmnTitle">제목</label>
            <input type="text" maxlength="50" name="cmnTitle" id="cmnTitle" >
        </div>
        
        <div class="form-group">
            <label for="memId">등록자</label>
            <input type="text" value="${sessionScope.user.memId}" maxlength="30" name="memId" id="memId" disabled="disabled" >
        </div>   
             
   
        <div class="form-group">
            <label  for="cmnContent">내용</label>
            <textarea class="contents" id="contentsTextArea" name="cmnContent" placeholder="내용을 입력하세요"></textarea>
        </div>                                
    </form>
    <!--// form area -->
  </div>  
  </main>
    <!-- footer-->
    <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
    <!--// footer-------------------------------------------------->
</div>
</body>
</html>