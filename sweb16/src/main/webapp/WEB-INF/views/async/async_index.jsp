<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>async 제목</title>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> -->
<script src="/ehr/resources/assets/js/jquery_3_7_1.js"></script>
<script>
//DOM문서(HTML) 문서가 로드가 완료되면 함수 수행
document.addEventListener("DOMContentLoaded",function(){
	console.log("DOMContentLoaded@@@@@@@@@@@@@");
	
	let username = document.querySelector("#username");
	let userpass = document.querySelector("#userpass");
	
	console.log(username);
	console.log(userpass);
	
	//전송
	$("#sendBtn").on("click",function(){
		asyncSend();
	});
	
	function asyncSend(){
		console.log("username : "+username.value);
		console.log("userpass : "+userpass.value);
		$.ajax({
			type:"POST",
			url:"/ehr/async/async_result.do",
			asyn:"true",
			dataType:"html",
			data:{
				"username":username.value,
				"userpass":userpass.value
			},
			success:function(response){
				console.log("success:"+response);
				result.innerHTML = response;
			},
			error:function(response){
				console.log("error:"+response);
			}
		});
	}//--asyncSend()
});//--DOMContentLoaded
</script>
</head>
<body>
    <h1>안녕하세요. async_index입니다.</h1>
    <form id="asyncForm">
        <label for="username">아이디</label>
        <input type="text" name="username" id="username">
        <label for="userpass">비밀번호</label>
        <input type="password" name="userpass" id="userpass" ><br>
        <input type="button" id="sendBtn" value="전송" style="margin:20px; padding:10px;">   
    </form>
    <div id="result"></div>
</body>

</html>