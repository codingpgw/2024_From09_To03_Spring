<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="shortcut icon" href="${CP}/resources/assets/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link rel="stylesheet" href="${CP}/resources/assets/css/main/home.css?date=<%=new Date()%>">
<script src="${CP}/resources/assets/js/main/main.js?date=<%=new Date()%>"></script>
<title>병원모아</title>
</head>
<body>
  <div class="main-container">
    <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
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
			      <a><img src="${CP}/resources/assets/images/006.png"></a>
			    </li>
			    <li class="slideitem">
			      <a><img src="${CP}/resources/assets/images/004.jpg"></a>
			    </li>
			    <li class="slideitem">
			      <a><img src="${CP}/resources/assets/images/005.jpg"></a>
			    </li>
			  </ul>
			  <i class="fa-solid fa-angle-right fa-2xl"></i>
			</div>
    </div>
    <h2 id="divH2">진료과 빠르게 찾기</h2>
    <div class="hospital-list">
      <ul>
        <li class="z"><a href="#"><img src="${CP}/resources/assets/images/injection.png"></a><span>이비인후과</span></li>
        <li class="x"><a href="#"><img src="${CP}/resources/assets/images/heart.png"></a><span>내과소아과</span></li>
        <li class="y"><a href="#"><img src="${CP}/resources/assets/images/teeth.png"></a><span>치과</span></li>
        <li class="q"><a href="#"><img src="${CP}/resources/assets/images/bag.png"></a><span>안과</span></li>
        <li class="w"><a href="#"><img src="${CP}/resources/assets/images/band.png"></a><span>정형외과</span></li>
        <li class="e"><a href="#"><img src="${CP}/resources/assets/images/hospital.png"></a><span>한의원</span></li>
        <li class="r"><a href="#"><img src="${CP}/resources/assets/images/person.png"></a><span>산부인과</span></li>
        <li class="t"><a href="#"><img src="${CP}/resources/assets/images/iv_gel.png"></a><span>피부과</span></li>
      </ul>
    </div>
    <div class="community-container">
      <table id="listTable" class="data-table">
          <colgroup>
            <col width="10%">
            <col width="70%">
            <col width="20%">          
          </colgroup>
          <thead>
            <tr>
              <th>번호</th>
              <th>제목</th>
              <th>글쓴이</th>
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
    </div>
  </div>
</body>

</html>