document.addEventListener("DOMContentLoaded", function () {
  console.log("DOMContentLoaded");

  const loginButton = document.querySelector("#loginBtn");
  const registerButton = document.querySelector("#registerBtn");

  const memIdInput = document.querySelector("#memId");
  const passwordIdInput = document.querySelector("#password");

  function handleLoginEvent() {
    console.log("loginButton click");

    if (isEmpty(memIdInput.value) === true) {
      alert('사용자 ID를 입력 하세요.');
      memIdInput.focus();
      return;
    }

    if (isEmpty(passwordIdInput.value) === true) {
      alert('비밀번호를 입력 하세요.');
      passwordIdInput.focus();
      return;
    }

    $.ajax({
      type: "POST",
      url: "/ehr/login/login.do",
      async: true,
      dataType: "html",
      data: {
        "memId": memIdInput.value,
        "password": passwordIdInput.value
      },
      success: function (response) {
        console.log("success response: " + response);
        const message = JSON.parse(response);
        
        // id 이상
        if (10 == message.messageId) {
          alert(message.message);
          memIdInput.focus();
        // pw 이상
        } else if (20 == message.messageId) {
          alert(message.message);
          passwordIdInput.focus();
        // 로그인 성공
        } else if (30 == message.messageId) {
          alert(message.message);
          if (memIdInput.value == "admin") {
            window.location.href = "/ehr/admin/adminRetrieveHospital.do";
          } else {
            window.location.href = "/ehr/main/main.do";
          }
          
        // 이외의 오류
        } else {
          alert(message.message);
        }
      },
      error: function (response) {
        console.log("error:" + response);
      }
    });
  }

  loginButton.addEventListener("click", handleLoginEvent);

  [memIdInput, passwordIdInput].forEach(input => {
    input.addEventListener("keypress", function (event) {
      if (event.key === "Enter") {
        handleLoginEvent();
      }
    });
  });

  const params = new URLSearchParams(window.location.search);
  if (params.get('alert') === 'needLogin') {
    alert('로그인이 필요합니다.');
  }

  registerButton.addEventListener("click", function (event) {
    console.log("registerButton click");

    window.location.href = "/ehr/register/register.do";
  });
});

