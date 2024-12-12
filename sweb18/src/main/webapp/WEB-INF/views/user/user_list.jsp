<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Document</title>
  <style>
    table{
      width: 720px;
      border-collapse: collapse;
      font-size: 16px;
    }
    th{
      text-align: center;
      background-color: skyblue;
      padding: 10px;
    }
    td{
      padding: 5px;
    }
    .highlight{
      font-weight: bold;
      color:#d9534f;
    }
  </style>
</head>
<body>
    <table style="margin-bottom: 30px;" border="1">
      <thead align="center">
        <td style="padding-right:20px; padding-left: 20px;">50</td>
        <td>전체</td>
        <td style="padding-left: 20px; padding-right: 20px;"> </td>
    </thead>
    </table>
    <table border="1">
      <thead>
        <th>번호</th>
        <th>회원ID</th>
        <th>이름</th>
        <th>이메일</th>
        <th>등급</th>
        <th>로그인</th>
        <th>추천</th>
        <th>등록일</th>
      </thead>
      <tbody align="center">
        <c:forEach var="vo" items="${list}">
	        <tr>
	          <td>${vo.getNo()}</td>
	          <td class="highlight">${vo.userId}</td>
	          <td>${vo.name}</td>
	          <td>${vo.email }</td>
	          <td>${vo.grade }</td>
	          <td>${vo.login }</td>
	          <td>${vo.recommend }</td>
	          <td>${vo.regDt }</td>
	        </tr>
        </c:forEach>
      </tbody>
    </table>
</body>
</html>