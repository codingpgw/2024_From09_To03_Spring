document.addEventListener('DOMContentLoaded',function(){
    console.log('DOMContentLoaded');
 
 
    const doSaveButton = document.querySelector("#doSave");
 
    
    const userIdInput = document.querySelector("#userId");//userId
    const nameInput   = document.querySelector("#name");//이름
    const passwordInput = document.querySelector("#password"); //비밀번호
    const loginInput = document.querySelector("#login"); //로그인
    const recommendInput = document.querySelector("#recommend"); //추천
    const emailInput = document.querySelector("#email"); //이메일일
    const gradeSelect = document.querySelector("#grade");//등급급
 
 
    doSaveButton.addEventListener("click",function(event){
     console.log('doSaveButton click');
    
     //Validation  
     console.log('userIdInput.value',userIdInput.value);
     console.log('gradeSelect.value',gradeSelect.value);
     
     if( isEmpty(userIdInput.value) === true ){
       alert('사용자 ID를 입력 하세요.');
       userIdInput.focus();
       return;
     }  
 
     if( isEmpty(nameInput.value) === true ){
       alert('이름을 입력 하세요.');
       nameInput.focus();
       return;
     }     
     
     if( isEmpty(passwordInput.value) === true ){
       alert('비밀번호를 입력 하세요.');
       passwordInput.focus();
       return;
     }  
 
     if( isEmpty(loginInput.value) === true ){
       alert('로그인 횟수를 입력 하세요.');
       loginInput.focus();
       return;
     } 
 
     if( isEmpty(recommendInput.value) === true ){
       alert('추천 횟수를 입력 하세요.');
       recommendInput.focus();
       return;
     } 
 
     if( isEmpty(emailInput.value) === true ){
       alert('이메일을 입력 하세요.');
       emailInput.focus();
       return;
     } 
     
     if(confirm('회원 등록 하시겠습니까?') === false)return;
   
     $.ajax({
       type: "POST",
       url: "/ehr/user/doSave.do",
       async: true,
       dataType: "html",
       data: {
           "userId"   : userIdInput.value,
           "name"     : nameInput.value,
           "password" : passwordInput.value,
           "login"    : loginInput.value,
           "recommend": recommendInput.value,
           "grade"    : gradeSelect.value,   
           "email"    : emailInput.value
       },
       success: function(response) {
           console.log("success response:" + response);
           const message = JSON.parse(response);
           if( 1 == message.messageId){//등록 성공
               alert(message.message);
               //목록으로 화면 이동.
               window.location.href = '/ehr/user/doRetrieve.do'; 
           }else{
             alert(message.message);
           }
 
       },
       error: function(response) {
           console.log("error:" + response);
       }
     });
 
 
    });
 
 
    const moveToListButton = document.querySelector("#moveToList"); 
 
    moveToListButton.addEventListener("click",function(event){
     console.log('moveToListButton click');
 
     if(confirm('회원 목록으로 이동 하시겠습니까?') === false)return;
 
     window.location.href = "/ehr/user/doRetrieve.do";
     
            
    });   
 
 });