document.addEventListener('DOMContentLoaded', function() {
  console.log('DOMContentLoaded');
  
  const loginBtn = document.querySelector('#loginBtn');
  const userIdInput = document.querySelector('#userId');
  const passwordInput = document.querySelector('#password');

  loginBtn.addEventListener('click',function(e){
    console.log('loginBtn');

    //필수처리
    if(isEmpty(userIdInput.value) == true){
      alert('ID를 입력해주세요.');
      userIdInput.focus();

      return;
    }

    if(isEmpty(passwordInput.value) == true){
      alert('비밀번호를 입력해주세요.');
      passwordInput.focus();

      return;
    }


    $.ajax({
      type: "POST",
      url: "/ehr/login/login.do",
      async: true,
      dataType: "html",
      data: {
          "userId" : userIdInput.value,
          "password" : passwordInput.value
      },
      success: function(response) {
          console.log("success response:" + response);
          const message = JSON.parse(response);

          if(10 == message.messageId){
            alert(message.message);
            userIdInput.focus();
          }else if(20 == message.messageId){
            alert(message.message);
            passwordInput.focus();
          }else if(30 == message.messageId){
            alert(message.message);
            
            window.location.href = '/ehr/main/main.do';
          }else{
            alert(message.message);
          }
      },
      error: function(response) {
          console.log("error:" + response);
      }
  });            
      


  });

});