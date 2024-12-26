<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <div class="signup-container">
        <h2>회원가입</h2>
        <form action="/doSave.do" method="post">
            <label for="username">아이디</label>
            <input type="text" id="username" name="username" required>

            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" required>
            
            <label for="displayname">이름</label>
            <input type="text" id="displayname" name="displayname" required>

            <label for="email">이메일</label>
            <input type="email" id="email" name="email" required>
            
            <label for="userphone">핸드폰 번호</label>
            <input type="number" id="userphone" name="userphone" required>
            
            <label for="jumin">주민번호</label>
            <input type="number" id="jumin" name="jumin" required>

            <button type="submit" id="registerBtn">가입하기</button>
        </form>
    </div>
</body>
</html>