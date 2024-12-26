<%@page import="com.pcwk.ehr.cmn.SearchVO"%>
<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    String pageHtml = StringUtil.renderingPager(maxNum, pageNo, pageSize, bottomCount, cp+"/user/doRetrieve.do", "pageDoRetrieve");
    //out.print("pageHtml:<br>"+pageHtml);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>hospital_list</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css" integrity="sha512-5Hs3dF2AEPkpNAR7UiOHba+lRSJNeM2ECkwxUIxC1Q/FLycGTbNapWXB4tP889k5T5Ju8fs4b1P5z/iB4nMfSQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" href="/ehr/resources/assets/css/hospital/holist_style_copy.css">
  <link rel="stylesheet" href="/ehr/resources/assets/css/hospital/nav.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
 <nav id="test"></nav>

 <div class="container_list">
  <div class="search-menu">
    <select class="local-select" aria-label="Default select example">
      <option selected>--지역 선택을 골라주세요.--</option>
      <option value="1">One</option>
      <option value="2">Two</option>
      <option value="3">Three</option>
    </select>
    <form action="" method="get">
      <input type="text" name="search-value" placeholder="병원 이름을 입력해주세요." class="csearch-value">

      <button class="detail-search" type="submit">
        <i class="fa-solid fa-magnifying-glass"></i>검색
      </button>
    </form>
     
  </div>
  <div class="department-list">
    <ul class="detail-list">
      <a href="#"><li>안과</li></a>
      <a href="#"><li>내과</li></a>
      <a href="#"><li>안과</li></a>
      <a href="#"><li>안과</li></a>
      <a href="#"><li>안과</li></a>
      <a href="#"><li>안과</li></a>
    </ul>
  </div>

  <div class="search-result">
  <c:forEach var="vo" items="${list }">
    <a href="#">
      <div class="detail-box">
        <h4>병원 이름</h4>
        <p>진료시간</p>
        <p>주소</p>
        <p>진료과목</p>
      </div>
    </a>
   </c:forEach>
   </div>
  </div>

  <%
    out.print(pageHtml);
    %>

 <script>
  fetch('nav.jsp')
  .then(response => response.text())
  .then(data => {
    document.getElementById('test').innerHTML = data;

    // 이벤트 리스너 추가
    const toggler = document.getElementsByClassName('navbar-toggler')[0];
    const userMenu = document.getElementsByClassName('user-menu')[0];

    if (toggler && userMenu) {
      toggler.addEventListener('click', function () {
        userMenu.classList.toggle('show');
      });
    }
  });
 </script>
</body>
</html>