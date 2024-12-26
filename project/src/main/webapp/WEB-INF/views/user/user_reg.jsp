<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>  
<head>
<link rel="shortcut icon" href="${CP}/resources/images/favicon.ico" type="image/x-icon" >
<link rel="stylesheet" href="/ehr/resources/assets/css/user/reg.css?=<%=new Date()%>">
<link rel="stylesheet" href="/ehr/resources/assets/css/main/main.css?=<%=new Date()%>">
<meta charset="UTF-8">
<title>회원등록</title>

<script src="/ehr/resources/assets/js/jquery_3_7_1.js"></script>  <!-- jquery    -->
<script src="/ehr/resources/assets/js/cmn/common.js"></script>    <!-- 공통 util -->
<script src="/ehr/resources/assets/js/user/user_reg.js"></script> <!-- 서버 전송 -->
</head>
<body> 
<div id="container">
    <!-- header-->
    <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
    <!--// header-------------------------------------------------->

    <!-- aside-->
    <jsp:include page="/WEB-INF/views/include/aside.jsp"></jsp:include>
    <!--// aside--------------------------------------------------->
    
    <main id="contents">
    <h2>회원등록</h2>        
    
    <!-- form area -->
    <form action="/ehr/user/doSave.do" method="post">
         <!-- Button area -->
        <div class="btn-box">
            <input type="button" id="doSave"     value="등록">
            <input type="button" id="moveToList" value="목록">
        </div>
        <!--// Button area -->
        <div>
            <label for="userId">아이디</label>
            <input type="text"  maxlength="30" name="userId" id="userId" >
        </div>
        
        <div>
            <label for="name">이름</label>
            <input type="text"  maxlength="7" name="name" id="name" >
        </div>   
             
        <div>
            <label for="password">비밀번호</label>
            <input type="password"  maxlength="30" name="password" id="password" >
        </div>
   
        <!-- 등급 -->        
        <div>  
            <label for="grade">등급</label>
            <select name="grade" id="grade" >
                <option value="BASIC">BASIC</option>
                <option value="SILVER">SILVER</option>
                <option value="GOLD">GOLD</option>
            </select>
        </div>  
                
                
        <div>
            <label for="login">로그인</label>
            <input type="number"  min="0" max="1000000" step="1" name="login" id="login" >
        </div>      
        
        <div>
            <label for="recommend">추천</label>
            <input type="number"  min="0" max="1000000" step="1" name="recommend" id="recommend" >
        </div>    
        <div>
            <label  for="email">이메일</label>
            <input type="email"  maxlength="320" name="email" id="email" >
        </div>                                
    </form>
    <!--// form area -->
    </main>
    
    <!-- footer-->
    <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
    <!--// footer-------------------------------------------------->
</div> 
</body>
</html>