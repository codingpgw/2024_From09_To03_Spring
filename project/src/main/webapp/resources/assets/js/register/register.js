document.addEventListener("DOMContentLoaded", function () {
  console.log("DOMContentLoaded");

  const doSaveButton = document.querySelector("#doSave");
  const moveToLoginButton = document.querySelector("#moveToLogin");
  const emailAuthBtn = document.querySelector("#emailAuth");

  const memIdInput = document.querySelector("#memId");                  // 아이디
  const nameInput = document.querySelector("#name");                    // 이름
  const passwordInput = document.querySelector("#password");            // 비밀번호
  const passwordCheckInput = document.querySelector("#passwordCheck");  // 비밀번호 확인
  const emailInput = document.querySelector("#email");                  // 이메일
  const phoneInput = document.querySelector("#phone");                  // 전화번호
  const juminInput = document.querySelector("#jumin");                  // 주민번호

  const checkIdButton = document.querySelector("#checkIdBtn");          // 아이디 중복 확인
  const idCheckMessage = document.querySelector("#idCheckMessage");     // 아이디 중복 확인 메시지
  const pwCheckMessage = document.querySelector("#pwCheckMessage");     // 비밀번호 일치 확인 메시지

  const idCheckInput = document.querySelector(".infoId");               // 아이디 입력 필드
  const pwInput = document.querySelector(".infoPw");                    // 비밀번호 입력 필드
  const pwCheckInput = document.querySelector(".infoCheckPw");          // 비밀번호 확인 입력 필드

  let isIdValid = false;    // 아이디 중복 여부
  let pwCheckValid = false; // 비밀번호 중복 여부

  function deleteCode(){
    $.ajax({
      type: "POST",
      url: "/ehr/email/doDelete.do",
      async: true,
      dataType: "json",
      data: {
          "memEmail": emailInput.value
      },
      success: function(response) {
          console.log("success response:" + response);
      },
      error: function(response) {
          console.log("error:" + response);
      }
    });
  }

  function retryCode(){
    $.ajax({
      type: "POST",
      url: "/ehr/email/doSave.do",
      async: true,
      dataType: "json",
      data: {
          "memEmail": emailInput.value
      },
      success: function(response) {
          console.log("success response:" + response);
          sendMail();
          $("#authCode").attr("disabled", false);
          $("#emailAuth").attr("disabled", true);
          alert("인증 코드가 입력하신 이메일로 전송 되었습니다.");
      },
      error: function(response) {
          console.log("error:" + response);
      }
    });
  }

  // 아이디 중복 확인
  checkIdButton.addEventListener("click", function (event) {
    event.preventDefault();
    console.log("memIdInput blur");

    if (!memIdInput.value) {
      isIdValid = false;
      idCheckMessage.textContent = "아이디를 입력하세요.";
      idCheckMessage.style.color = "red";
      idCheckInput.style.borderColor = "red";
      return;
    }

    $.ajax({
      type: "POST",
      url: "/ehr/register/idCheck.do", // 중복체크 API URL
      async: true,
      dataType: "html",
      data: {
        "memId": memIdInput.value
      },
      success: function (response) {
        console.log("success response: " + response);
        const message = JSON.parse(response);

        if (message.messageId === 10) { // 중복된 ID
          idCheckMessage.textContent = "중복된 아이디입니다.";
          idCheckMessage.style.color = "red";
          idCheckInput.style.borderColor = "red";
          isIdValid = false;
      } else {
          idCheckMessage.textContent = "사용 가능한 아이디입니다.";
          idCheckMessage.style.color = "green";
          idCheckInput.style.borderColor = "green";
          isIdValid = true;
      }
      },
      error: function (response) {
        console.log("error:" + response);
        alert("아이디 중복 체크 중 오류가 발생했습니다.");
        memIdCheckMessage.className = "error-message";
        idCheckMessage.style.color = "red";
        idCheckInput.style.borderColor = "red";
        isIdValid = false;
      }
    });
  });

  passwordInput.addEventListener("input", checkPasswordMatch);
  passwordCheckInput.addEventListener("input", checkPasswordMatch);

  function checkPasswordMatch() {
    if (!passwordCheckInput.value) {
      pwCheckMessage.textContent = "";
      pwCheckMessage.style.color = "";
      return;
    }

    if (passwordInput.value === passwordCheckInput.value) {
      pwCheckMessage.textContent = "비밀번호가 일치합니다.";
      pwCheckMessage.style.color = "green";
      pwInput.style.borderColor = "green";
      pwCheckInput.style.borderColor = "green";
      pwCheckValid = true;
    } else if (passwordInput.value !== passwordCheckInput.value) {
      pwCheckMessage.textContent = "비밀번호가 일치하지 않습니다.";
      pwCheckMessage.style.color = "red";
      pwInput.style.borderColor = "red";
      pwCheckInput.style.borderColor = "red";
      pwCheckValid = false;
    }
  }

  passwordCheckInput.addEventListener("input", function (event) {
    
  });

  doSaveButton.addEventListener("click", function (event) {
    console.log("doSaveButton click");
    
    // Validation  
    console.log("memIdInput.value", memIdInput.value);

    if (isEmpty(memIdInput.value) === true) {
      alert("사용자 ID를 입력하세요.");
      memIdInput.focus();
      return;
    }

    if (isEmpty(nameInput.value) === true) {
      alert("이름을 입력하세요.");
      nameInput.focus();
      return;
    }

    if (isEmpty(passwordInput.value) === true) {
      alert("비밀번호를 입력하세요.");
      passwordInput.focus();
      return;
    }

    if (isEmpty(passwordInput.value) === true) {
      alert("비밀번호를 입력하세요.");
      passwordInput.focus();
      return;
    }

    if (isEmpty(emailInput.value) === true) {
      alert("이메일을 입력하세요.");
      emailInput.focus();
      return;
    }

    if (isEmpty(phoneInput.value) === true) {
      alert("전화번호를 입력하세요.");
      phoneInput.focus();
      return;
    }

    if (isEmpty(juminInput.value) === true) {
      alert("주민번호를 입력하세요.");
      juminInput.focus();
      return;
    }

    if (!isIdValid) {
      alert("아이디 중복을 확인하세요.");
      return;
    }

    if (!passwordCheck) {
      pwCheckMessage.textContent = "";
      pwCheckMessage.style.color = "";
      return;
    }

    if (passwordInput.value !== passwordCheckInput.value) {
      pwCheckMessage.textContent = "비밀번호가 일치하지 않습니다.";
      pwCheckMessage.style.color = "red";
      pwCheckInput.style.borderColor = "red";
      passwordCheckInput.focus();
      return;
    } else if (passwordInput.value === passwordCheckInput.value) {
      pwCheckMessage.textContent = "비밀번호가 일치합니다.";
      pwCheckMessage.style.color = "green";
      pwCheckInput.style.borderColor = "green";
    }

    if (confirm("회원 가입 하시겠습니까?") === false) return;

    $.ajax({
      type: "POST",
      url: "/ehr/register/doRegister.do",
      async: true,
      dataType: "html",
      data: {
        "memId": memIdInput.value,
        "password": passwordInput.value,
        "name": nameInput.value,
        "email": emailInput.value,
        "phone": phoneInput.value,
        "jumin": juminInput.value
      },
      success: function (response) {
        console.log("success response: " + response);
        const message = JSON.parse(response);

        if (1 == message.messageId) { //등록 성공
          alert(message.message);
          //목록으로 화면 이동.
          deleteCode(); //등록 후 이메일 및 코드 삭제
          window.location.href = "/ehr/login/login_index.do";
        } else {
          alert(message.message);
        }
      },
      error: function (response) {
        console.log("error:" + response);
      }
    });
  });

  function sendMail(){
    $.ajax({
      url : '/ehr/register/emailAuth.do',
      data : {
        email : emailInput.value
      },
      type : 'POST',
      dataType : 'json',
      success : function(result) {
        console.log("result : " + result);
       }
    });
  }

  emailAuthBtn.addEventListener('click',function(e){
    console.log(emailInput.value);

    $.ajax({
      type: "POST",
      url: "/ehr/email/doSave.do",
      async: true,
      dataType: "json",
      data: {
          "memEmail": emailInput.value
      },
      success: function(response) {
          console.log("success response:" + response);
          sendMail();
          $("#authCode").attr("disabled", false);
          $("#emailAuth").attr("disabled", true);
          alert("인증 코드가 입력하신 이메일로 전송 되었습니다.");
      },
      error: function(response) {
          console.log("error:" + response);
      }
    });

    const timerElement = document.getElementById('timer');
    timerElement.style.display = 'block'; // 타이머 표시
    let timeRemaining = 300; // 5분 = 300초
    document.getElementById('verifyCode').disabled = false;

    const interval = setInterval(function() {
        const minutes = Math.floor(timeRemaining / 60);
        const seconds = timeRemaining % 60;
        timerElement.textContent = `남은 시간: ${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;

        if (timeRemaining <= 0) {
            clearInterval(interval);
            timerElement.textContent = '인증 시간이 만료되었습니다.';
            document.getElementById('authCode').disabled = true;
            document.getElementById('doSave').disabled = true;
        }

        timeRemaining--;
    }, 1000);

  });

  document.getElementById('retry').addEventListener('click',function(){
    $.ajax({
      type: "POST",
      url: "/ehr/email/doDelete.do",
      async: true,
      dataType: "json",
      data: {
          "memEmail": emailInput.value
      },
      success: function(response) {
          console.log("success response:" + response);
          retryCode();
      },
      error: function(response) {
          console.log("error:" + response);
      }
    });

  });

  document.getElementById('verifyCode').addEventListener('click', function() {
      const inputCode = document.getElementById('authCode').value; //인증번호 입력 칸에 작성한 내용 가져오기
      const email = emailInput.value; // 이메일 입력 칸에 작성한 내용 가져오기

      console.log("입력코드 : " + inputCode);

      $.ajax({
          type: 'POST',
          url: '/ehr/register/verifyCode.do',
          data: { email: email, inputCode: inputCode },
          success: function(response) {
              if(response) {
                  $("#emailAuthWarn").html('인증번호가 일치합니다.');
                  $("#emailAuthWarn").css('color', 'green');
                  $('#emailAuth').attr('disabled', true);
                  $('#authCode').attr('disabled', true);
                  $('#verifyCode').attr('disabled', true);
                  $('#retry').attr('disabled', true);
                  $('#email').attr('readonly', true);
                  $('#doSave').prop('disabled', false);
              } else {
                  $("#emailAuthWarn").html('인증번호가 불일치 합니다. 다시 확인해주세요!');
                  $("#emailAuthWarn").css('color', 'red');
                  $('#doSave').prop('disabled', true);
              }
          },
          error: function() {
              $("#emailAuthWarn").html('서버와의 통신 중 오류가 발생했습니다. 다시 시도해 주세요.');
              $("#emailAuthWarn").css('color', 'red');
          }
      });
  });


//   const codeInput = document.querySelector('#authCode');
//   codeInput.addEventListener('click', function() {

//     console.log("입력코드 : " + codeInput.value);

//     $.ajax({
//         type: 'POST',
//         url: '/ehr/register/verifyCode.do',
//         data: { email: emailInput.value, inputCode: codeInput.value },
//         success: function(response) {
//             if(response) {
//                 $("#emailAuthWarn").html('인증번호가 일치합니다.');
//                 $("#emailAuthWarn").css('color', 'green');
//                 $('#emailAuth').attr('disabled', true);
//                 $('#email').attr('readonly', true);
//                 $('#doSave').prop('disabled', false);
//             } else {
//                 $("#emailAuthWarn").html('인증번호가 불일치 합니다. 다시 확인해주세요!');
//                 $("#emailAuthWarn").css('color', 'red');
//                 $('#doSave').prop('disabled', true);
//             }
//         },
//         error: function() {
//             $("#emailAuthWarn").html('서버와의 통신 중 오류가 발생했습니다. 다시 시도해 주세요.');
//             $("#emailAuthWarn").css('color', 'red');
//         }
//     });
// });

  

  moveToLoginButton.addEventListener("click", function (event) {
    console.log("moveToLoginButton click");

    window.location.href = "/ehr/login/login_index.do";
  });
});