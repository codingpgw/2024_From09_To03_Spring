<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f9;
    color: #333;
}

h2 {
    text-align: center;
    color: #3b5998;
    margin: 20px 0;
}

div {
    margin: 10px 0;
    padding: 0 15px;
    display: flex;
    justify-content: center;
    gap: 10px;
}

form {
    width: 500px;
    margin: 20px auto;
    padding: 20px;
    background-color: #fff;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

label {
    display: inline-block;
    width: 120px;
    margin-bottom: 5px;
    font-weight: bold;
    color: #555;
    vertical-align: middle;
}

input[type="text"],
input[type="password"],
input[type="number"],
input[type="email"],
select {
    width: calc(100% - 140px);
    padding: 8px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 14px;
    box-sizing: border-box;
    vertical-align: middle;
}

input[type="button"] {
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    background-color: #3b5998;
    color: white;
    font-size: 14px;
    cursor: pointer;
    transition: background-color 0.3s;
}

input[type="button"]:hover {
    background-color: #2d4373;
}

#moveToList {
    background-color: #8b9dc3;
}

#moveToList:hover {
    background-color: #7a89a8;
}
</style>
<meta charset="UTF-8">
<link rel="shortcut icon" href="/ehr/resources/images/favicon.ico" type="image/x-icon" >
<script src="/ehr/resources/assets/js/jquery_3_7_1.js"></script>
<script src="/ehr/resources/assets/js/cmn/common.js"></script>
<script src="/ehr/resources/assets/js/user/user_mng.js"></script>

<title>회원 관리</title>
</head>
<body>
 <h2>회원관리</h2>  
 <div>
    <input type="button" id="moveToList" value="목록">
    <input type="button" id="doUpdate"     value="수정">
    <input type="button" id="doDelete"     value="삭제">
  </div>
    
    ${vo}
    <!-- form area -->
    <form action="#">
        <div>
            <label for="userId">아이디</label>
            <input type="text"  maxlength="30" name="userId" id="userId"
            value="${vo.userId}" disabled="disabled">
        </div>
        
        <div>
            <label for="name">이름</label>
            <input type="text"  maxlength="7" name="name" id="name" 
            value="${vo.name}">
        </div>   
             
        <div>
            <label for="password">비밀번호</label>
            <input type="password"  maxlength="30" name="password" id="password" 
            value="${vo.password}">
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
            <input type="number"  min="0" max="1000000" step="1" name="login" id="login" 
            value="${vo.login}">
        </div>      
        
        <div>
            <label for="recommend">추천</label>
            <input type="number"  min="0" max="1000000" step="1" name="recommend" id="recommend" 
            value="${vo.recommend }">
        </div>    
        <div>
            <label  for="email">이메일</label>
            <input type="email"  maxlength="320" name="email" id="email" 
            value="${vo.email }">
        </div>                                
    </form>
    <!--// form area -->
    
</body>
</html>