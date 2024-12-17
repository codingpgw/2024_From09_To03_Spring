<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등록</title>
<link href="" rel="stylesheet">
</head>
<body>
    <h2>회원등록</h2>
    
    <form action="ehr/user/doSave.do" method="post">
        <div>
	        <label for="userId">아이디</label>
	        <input type="text" name="userId" id="userId" maxlength="30">
	    </div>
	    <div>
	        <label for="name">이름</label>
            <input type="text" name="name" id="name" maxlength="7">
        </div>
        <div>
            <label for="password">비밀번호</label>
            <input type="password" name="password" id="password" maxlength="30">
        </div>
        <div>
            <label for="grade">등급</label>
            <select name="grade" id="grade">
                <option value="BAGIC">BAGIC</option>
                <option value="SILVER">SILVER</option>
                <option value="GOLD">GOLD</option>
            </select>
        </div>
        <div>
            <label for="login">로그인</label>
            <input type="number" name="login" id="login" min="0" max="1000000">
        </div>
        <div>
            <label for="recommend">추천</label>
            <input type="number" name="recommend" id="recommend" min="0" max="1000000">
        </div>
        <div>
            <label for="email">이메일</label>
            <input type="email" name="email" id="email" maxlength="320">
        </div>
    </form>
    
    <div>
        <input type="button" value="등록">
        <input type="button" value="목록">
    </div>
    
</body>
</html>