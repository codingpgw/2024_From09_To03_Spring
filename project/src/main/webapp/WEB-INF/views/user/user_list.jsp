<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="/ehr/resources/images/favicon.ico" type="image/x-icon" >
<script src="/ehr/resources/assets/js/jquery_3_7_1.js"></script>
<script src="/ehr/resources/assets/js/cmn/common.js"></script>
<script src="/ehr/resources/assets/js/user/user_list.js"></script>

<style> 
table {
    width: 720px;
    border-collapse: collapse;
    font-size: 16px;
}

thead th {
    background-color: #007bff;
    color: white;
    padding: 10px;
    text-align: center;
}

tbody td {
    border: 1px solid #dddddd;
    padding: 10px;
}

.text-left {
    text-align: left;
}

.text-center {
    text-align: center;
}

.text-right {
    text-align: right;
}

.highlight {
    font-weight: bold;
    color: #d9534f;
}
</style>
<title>회원목록</title>
</head>
<body>
    <h2>회원목록</h2>
    <div>
        <input type="button" value="조회" id="doRetrieveBtn"> 
        <input type="button" value="등록" id="moveToRegBtn">
    </div>    
    <!-- 검색조건   이름  이메일-->  
    <form action="#" name="userForm" id="userForm" method="get" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="pageNo" id="pageNo" >
        <div>
            <label for="searchDiv">구분</label>
            <select name="searchDiv" id="searchDiv">  
                <option value="">전체</option>
                <option value="10" <c:if test="${ 10 ==search.searchDiv}">selected</c:if> >회원ID</option>
                <option value="20" <c:if test="${ 20 ==search.searchDiv}">selected</c:if> >이름</option>  
                <option value="30" <c:if test="${ 30 ==search.searchDiv}">selected</c:if> >이메일</option>
            </select>
            <input type="search" name="searchWord" id="searchWord" value="${search.searchWord}">
            <select name="pageSize" id="pageSize">
                <option value="10"  <c:if test="${ 10 ==search.pageSize}">selected</c:if>  >10</option>
                <option value="20"  <c:if test="${ 20 ==search.pageSize}">selected</c:if>  >20</option>
                <option value="30"  <c:if test="${ 30 ==search.pageSize}">selected</c:if>  >30</option>
                <option value="50"  <c:if test="${ 50 ==search.pageSize}">selected</c:if>  >50</option>
                <option value="100" <c:if test="${ 100 ==search.pageSize}">selected</c:if>  >100</option>
            </select>            
        </div>            
    </form>
    <!--// 검색조건 -->
    <table border="1" id="listTable">
        <thead>
            <th>번호</th>
            <th>회원ID</th>
            <th>비밀번호</th>
            <th>이름</th>
            <th>이메일</th>
            <th>전화번호</th>
            <th>주민번호</th>
            <th>구분</th>
            <th>등록일</th>
        </thead>
        <tbody>
            <c:forEach var="vo" items="${list }">
                <tr>
                    <td class="text-center">${vo.getNo() }</td>
                    <td class="text-left highlight">${vo.mem_id }</td>
                    <td class="text-center">${vo.mem_password}</td>
                    <td>${vo.mem_name }</td>
                    <td class="text-center">${vo.mem_email}</td>
                    <td class="text-right">${vo.mem_phonenum}</td>
                    <td class="text-right">${vo.mem_jumin}</td>
                    <td class="text-center ">${vo.mem_div }</td>
                    <td class="text-center ">${vo.mem_regdt }</td>
                </tr>
            </c:forEach>
        </tbody>

    </table>

</body>
</html>