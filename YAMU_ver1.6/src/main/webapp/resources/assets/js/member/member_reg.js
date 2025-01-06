document.addEventListener("DOMContentLoaded", function() {
  console.log("DOMContentLoaded");

  const doSaveButton = document.querySelector("#doSave");
  
  const memIdInput = document.querySelector("#memId");        // 아이디
  const nameInput   = document.querySelector("#name");        // 이름
  const passwordInput = document.querySelector("#password");  // 비밀번호
  const emailInput = document.querySelector("#email");        // 이메일
  const phoneInput = document.querySelector("#phone");        // 전화번호
  const juminInput = document.querySelector("#jumin");        // 주민번호

  doSaveButton.addEventListener("click", function(event){
    console.log("doSaveButton click");

    // Validation  
    console.log("memIdInput.value",memIdInput.value);
    
    if( isEmpty(memIdInput.value) === true ){
      alert("사용자 ID를 입력 하세요.");
      memIdInput.focus();
      return;
    }

    if( isEmpty(nameInput.value) === true ){
      alert("이름을 입력 하세요.");
      nameInput.focus();
      return;
    }
   
    if( isEmpty(passwordInput.value) === true ){
      alert("비밀번호를 입력 하세요.");
      passwordInput.focus();
      return;
    }

    if( isEmpty(emailInput.value) === true ){
      alert("이메일을 입력 하세요.");
      emailInput.focus();
      return;
    }

    if( isEmpty(phoneInput.value) === true ){
      alert("전화번호를 입력 하세요.");
      phoneInput.focus();
      return;
    }

    if( isEmpty(juminInput.value) === true ){
      alert("주민번호를 입력 하세요.");
      juminInput.focus();
      return;
    }
   
    if(confirm("회원 가입 하시겠습니까?") === false) return;
 
    $.ajax({
      type: "POST",
      url: "/ehr/member/doSave.do",
      async: true,
      dataType: "html",
      data: {
          "memId"    : memIdInput.value,
          "password" : passwordInput.value,
          "name"     : nameInput.value,
          "email"    : emailInput.value,
          "phone"    : phoneInput.value,
          "jumin"    : juminInput.value
      },
      success: function(response) {
          console.log("success response: " + response);
          const message = JSON.parse(response);

          if( 1 == message.messageId){ //등록 성공
              alert(message.message);
              //목록으로 화면 이동.
              window.location.href = "/ehr/member/doRetrieve.do"; 
          } else {
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
   console.log("moveToListButton click");

   if(confirm("회원 목록으로 이동 하시겠습니까?") === false) return;

   window.location.href = "/ehr/member/doRetrieve.do";
  });
});