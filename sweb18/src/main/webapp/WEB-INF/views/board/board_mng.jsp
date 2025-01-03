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
<link rel="stylesheet" href="${CP}/resources/assets/css/simplemde.min.css">
<title>게시글 등록</title>
<link rel="stylesheet" href="${CP}/resources/assets/css/board/form.css?date=${sysDate}">
<link rel="stylesheet" href="/ehr/resources/assets/css/main/main.css?date=${sysDate}">

<script src="${CP}/resources/assets/js/simplemde.min.js"></script>
<script src="${CP}/resources/assets/js/jquery_3_7_1.js"></script>
<script src="${CP}/resources/assets/js/cmn/common.js"></script>
<script src="${CP}/resources/assets/js/board/board_mng.js?date=${sysDate}"></script>
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
        <input type="button" id="moveToList"    value="목록">
        <input type="button" id="doUpdateBtn"   value="수정">
        <input type="button" id="doDeleteBtn"   value="삭제">
    </div>
    <!--// Button area -->

    <!-- form area -->
    <form action="#" method="post" >
        <input type="hidden" name="seq" id="seq" value="${vo.seq }">
        <input type="hidden" name="div" id="div" value="${vo.getDiv()}">
        <div class="form-group">
            <label for="userId">제목</label>
            <input value="${vo.title }" type="text"  maxlength="50" name="title" id="title" >
        </div>
             
         <div class="form-group">
            <label for="readCnt">조회수</label>
            <input value="${vo.readCnt }"  type="text"  maxlength="9" name="readCnt" id="readCnt" disabled="disabled" >
        </div>   
        
        <div class="form-group">
            <label for="name">등록자</label>
            <input value="${vo.regId }" type="text"  maxlength="30" name="regId" id="regId" disabled="disabled">
        </div>   
        
        <div class="form-group">
            <label for="regDt">등록일</label>
            <input value="${vo.regDt }" type="text"  maxlength="13" name="regDt" id="regDt" disabled="disabled">
        </div>   
        
        
        <div class="form-group">
            <label for="modId">수정자</label>
            <input value="${vo.modId }" type="text"  maxlength="30" name="modId" id="modId" disabled="disabled">
        </div>   
        
        <div class="form-group">
            <label for="modDt">수정일</label>
            <input value="${vo.modDt }" type="text"  maxlength="13" name="modDt" id="modDt" disabled="disabled">
        </div>   
        
    
        <div class="form-group">
            <label  for="contentsTextArea">내용</label>
            <div style="width:90%;">
            <textarea class="contents" id="contentsTextArea" name="contentsTextArea" placeholder="내용을 입력하세요"><c:out value="${vo.contents}"></c:out></textarea>
            </div>
        </div> 
                  
        <div class="form-group">
            <label>내용(html)</label>
            <div style="width:90%;">${markdownToHtml}</div>

        </div>                        
    </form>
    </div>
    <!--// form area -->
    </main>
    
    <!-- footer-->
    <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
    <!--// footer-------------------------------------------------->
    <script>
    var simplemde = new SimpleMDE({ element: document.getElementById("contentsTextArea") });
    </script>
</div> 
</body>
</html>