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
    background-color: #f9f9f9;
    color: #333;
}

h2 {
    text-align: center;
    color: #4CAF50;
    margin: 20px 0;
}

div {
    margin: 10px 0;
    padding: 0 15px;
}

form {
    width: 400px;
    margin: 0 auto;
    padding: 20px;
    background-color: #ffffff;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}

input[type="text"],
input[type="password"],
input[type="number"],
input[type="email"],
select {
    width: calc(100% - 10px);
    padding: 8px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 14px;
    box-sizing: border-box;
}
.btn-box{
    display: flex;
    align-items: center;
    justify-content: center;
}
input[type="button"] {
    padding: 10px 15px;
    margin: 5px;
    border: none;
    border-radius: 4px;
    background-color: #4CAF50;
    color: white;
    font-size: 14px;
    cursor: pointer;
}

input[type="button"]:hover {
    background-color: #45a049;
}

#moveToList {
    background-color: #008CBA;
}

#moveToList:hover {
    background-color: #007BB5;
}

</style>
<meta charset="UTF-8">
<title>회원등록</title>

<script src="/ehr/resources/assets/js/jquery_3_7_1.js"></script>  <!-- jquery    -->
<script src="/ehr/resources/assets/js/cmn/common.js"></script>    <!-- 공통 util -->
<script src="/ehr/resources/assets/js/user/user_reg.js"></script> <!-- 서버 전송 -->
</head>
<body>  
    <h2>회원등록</h2>        
    <!-- Button area -->
    <div class="btn-box">
        <input type="button" id="doSave"     value="등록">
        <input type="button" id="moveToList" value="목록">
    </div>
    <!--// Button area -->
    
    <!-- form area -->
    <form action="/ehr/user/doSave.do" method="post">
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
</body>
</html>