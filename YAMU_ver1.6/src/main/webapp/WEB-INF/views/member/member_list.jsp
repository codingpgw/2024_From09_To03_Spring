<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%@page import="com.pcwk.ehr.cmn.SearchVO"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}"/>
<%
  /**
 * @param maxNum: 총 글수
 * @param currentPageNo: 현재 페이지 번호
 * @param rowPerPage: 페이지 사이즈(10, 20, ... 100)
 * @param bottomCount: 10/5
 * @param url: 서버 호출 URL
 * @param scriptName: 자바스크립트 함수명
 * @return "html 텍스트"
 */
 //public static String renderingPager(int maxNum, int currentPageNo, int rowPerPage, int bottomCount, String url, String scriptName) {
  int bottomCount = 10;
  int pageSize = 10;
  int pageNo = 1;
  
  int maxNum = Integer.parseInt(request.getAttribute("totalCnt").toString()); // 총 글수
  
  // out.print("****:" + maxNum);
  
  SearchVO paramVO = (SearchVO) request.getAttribute("search");
  pageSize = paramVO.getPageSize();
  pageNo = paramVO.getPageNo();
  
  //out.print("****:" + pageSize);
  //out.print("****:" + pageNo);
  String cp = request.getContextPath();
  //out.print("cp****" + cp);
  String pageHtml = StringUtil.renderingPager(maxNum, pageNo, pageSize, bottomCount, cp + "/member/doRetrieve.do", "pageDoRetrieve");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="${CP}/resources/assets/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="${CP}/resources/assets/css/member/list.css?date=<%=new Date()%>">
<link rel="stylesheet" href="${CP}/resources/assets/css/main/home.css?date=<%=new Date()%>">
<title>회원 목록</title>
<script src="${CP}/resources/assets/js/jquery_3_7_1.js"></script>
<script src="${CP}/resources/assets/js/cmn/common.js"></script>
<script src="${CP}/resources/assets/js/member/member_list.js?date=<%=new Date()%>"></script>
</head>
<body>
  <div id="container">
    <!-- header---------------------------------------------------->
    <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
    <!--// header-------------------------------------------------->
    <div class="main-container">
      <!-- 정보 관리 -->
      <div class="form-container">
        <h2>회원관리</h2>
        <hr class="title-underline" />
        <img src="https://via.placeholder.com/80" alt="프로필 사진" id="profile-img" />
        <div class="upload-btn">
          <input type="file" id="profile-upload" accept="image/*" onchange="previewImage(event)" />
        </div>
        <!-- form area -->
        <form action="#" class="form">
          <div class="form-group">
            <label for="memId">아이디</label>
            <input type="text" maxlength="30" name="memId" id="memId" value="${vo.memId}" disabled />
          </div>
          <div class="form-group">
            <label for="memname">이름</label>
            <input type="text" maxlength="7" name="memname" id="memname" value="${vo.memname}" />
          </div>
          <div class="form-group">
            <label for="mempassword">비밀번호</label>
            <input type="password" maxlength="30" name="mempassword" id="mempassword" value="${vo.mempassword}" />
          </div>
          <div class="form-group">
            <label for="mememail">이메일</label>
            <input type="email" maxlength="320" name="mememail" id="mememail" value="${vo.mememail}" />
          </div>
          <div class="form-group">
            <label for="memphonenum">전화번호</label>
            <input type="tel" maxlength="11" name="memphonenum" id="memphonenum" value="${vo.memphonenum}" />
          </div>
        </form>
        <div class="button-area">
          <input type="button" id="doUpdate" value="수정" />
          <input type="button" id="doCancel" value="취소" />
        </div>
      </div>

      <!-- 예약 관리 -->
      <div class="reservation">
        <table>
          <tr>
            <th>BOOK DT</th>
            <td>EX)2024-12-25</td>
          </tr>
          <tr>
            <th>HOSPITAL ID</th>
            <td>Acorn병원</td>
          </tr>
        </table>
      </div>
    </div>
    <!-- footer --------------------------------------------------->
    <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
    <!--// footer-------------------------------------------------->
	</div>
</body>
</html>