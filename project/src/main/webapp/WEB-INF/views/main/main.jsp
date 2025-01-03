<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
  <link rel="stylesheet" href="main.css" />
  <script src="/project/js/main.js"></script>
  <title>제목</title>
</head>
<body>
  <div>
    <div class="nav-container">
      <ul class="top-nav">
        <li><a href="#" class="logo">병원모아</a></li>
        <li><a href="#" class="category-menu">공지사항</a></li>
        <li><a href="#" class="category-menu" >병원 목록</a></li>
        <li><a href="#" class="category-menu">우리동네인기병원</a></li>
        <li><a href="#" class="category-menu">커뮤니티</a></li>
        <li><a href="#" class="category-menu">Q&A</a></li>
        <li><a href="#" class="category-menu">마이페이지</a></li>
        <li><a href="#"><button type="button" class="login-button">로그인</button></a></li>
      </ul>
    </div>
    <div class="main-search-container">
      <div class="search-div">
        <button class="pl on" onclick="select();">통합검색</button>
        <ul class="listbox" id="listbox" style="display: none;">
          <li><button class="list" value="10">진료과</button></li>
          <li><button class="list" value="20">병원명</button></li>
        </ul>
      </div>
      <div class="search-box">
        <input type="text" class="search-input" placeholder="병원이름, 진료과를 검색해보세요." />
        <button type="submit" class="search-button">
          <i class="fas fa-search"></i>
        </button>
      </div>
    </div>
    <div class="slidebox">
      <input type="radio" name="slide" id="slide01" checked>
      <input type="radio" name="slide" id="slide02">
      <input type="radio" name="slide" id="slide03">

      <div class="slidelist-container">
			  <i class="fa-solid fa-angle-left fa-2xl"></i>
			  <ul class="slidelist">
			    <li class="slideitem">
			      <a><img src="/project/image/006.png"></a>
			    </li>
			    <li class="slideitem">
			      <a><img src="/project/image/004.jpg"></a>
			    </li>
			    <li class="slideitem">
			      <a><img src="/project/image/005.jpg"></a>
			    </li>
			  </ul>
			  <i class="fa-solid fa-angle-right fa-2xl"></i>
			</div>
    </div>
    <h2>진료과 빠르게 찾기</h2>
    <div class="hospital-list">
      <ul>
        <li class="z"><a href="#"><img src="/project/image/주사3.png"></a><span>이비인후과</span></li>
        <li class="x"><a href="#"><img src="/project/image/하트3-1.png"></a><span>내과소아과</span></li>
        <li class="y"><a href="#"><img src="/project/image/치아3.png"></a><span>치과</span></li>
        <li class="q"><a href="#"><img src="/project/image/병원가방3.png"></a><span>안과</span></li>
        <li class="w"><a href="#"><img src="/project/image/데일밴드3.png"></a><span>정형외과</span></li>
        <li class="e"><a href="#"><img src="/project/image/병원3.png"></a><span>한의원</span></li>
        <li class="r"><a href="#"><img src="/project/image/사람3.png"></a><span>산부인과</span></li>
        <li class="t"><a href="#"><img src="/project/image/링겔3.png"></a><span>피부과</span></li>
      </ul>
    </div>
    <div class="community-container">

    </div>
  </div>
</body>

</html>