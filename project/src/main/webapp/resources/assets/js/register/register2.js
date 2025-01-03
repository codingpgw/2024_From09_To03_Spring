document.addEventListener("DOMContentLoaded", function () {
  console.log("DOMContentLoaded");

  const doSaveButton = document.querySelector("#doSave");
  const moveToLoginButton = document.querySelector("#moveToLogin");

  const memIdInput = document.querySelector("#memId");                  // 아이디
  const nameInput = document.querySelector("#name");                  // 이름
  const passwordInput = document.querySelector("#password");            // 비밀번호
  const passwordCheckInput = document.querySelector("#passwordCheck");  // 비밀번호 확인
  const emailInput = document.querySelector("#email");                  // 이메일
  const phoneInput = document.querySelector("#phone");                  // 전화번호
  const juminInput = document.querySelector("#jumin");                  // 주민번호

  const idCheckMessage = document.querySelector("#idCheckMessage");
  const isCheckInput = document.querySelector(".infoBox");
  let isIdValid = false;

  memIdInput.addEventListener("blur", function () {
    console.log("memIdInput blur");

    if (!memIdInput.value) {
      isIdValid = false;
      idCheckMessage.textContent = "아이디를 입력하세요.";
      idCheckMessage.style.color = "red";
      isCheckInput.style.borderColor = "red";
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
          isCheckInput.style.borderColor = "red";

          isIdValid = false;
          // memIdInput.focus(); // 중복된 경우 입력창에 포커스 유지
        } else {
          idCheckMessage.textContent = "사용 가능한 아이디입니다.";
          idCheckMessage.style.color = "green";
          isCheckInput.style.borderColor = "green";
          isIdValid = true;
        }
      },
      error: function (response) {
        console.log("error:" + response);
        alert("아이디 중복 체크 중 오류가 발생했습니다.");
        idCheckMessage.style.color = "red";
        isCheckInput.style.borderColor = "red";
        isIdValid = false;
      }
    });
  }); //memIdInput end

  //이메일 인증 버튼 입력시 메일로 이메일 전송
  document.querySelector('#emailAuth').addEventListener('click',function(){
    const email = emailInput.value; //사용자가 입력한 이메일 값 얻어오기
      
    //Ajax로 전송
      $.ajax({
        url : '/ehr/register/emailAuth.do',
        data : {
          email : email
        },
        type : 'POST',
        dataType : 'json',
        success : function(result) {
          console.log("result : " + result);
          $("#authCode").attr("disabled", false);
          code = result;
          alert("인증 코드가 입력하신 이메일로 전송 되었습니다.");
         }
      }); //End Ajax
  });

  doSaveButton.addEventListener("click", function (event) {
    console.log("doSaveButton click");

    if (!isIdValid) {
      alert("중복된 아이디입니다.");
      memIdInput.focus();
      return;
    }

    // Validation  
    console.log("memIdInput.value", memIdInput.value);

    if (isEmpty(memIdInput.value) === true) {
      alert("사용자 ID를 입력 하세요.");
      memIdInput.focus();
      return;
    }

    if (isEmpty(nameInput.value) === true) {
      alert("이름을 입력 하세요.");
      nameInput.focus();
      return;
    }

    if (isEmpty(passwordInput.value) === true) {
      alert("비밀번호를 입력 하세요.");
      passwordInput.focus();
      return;
    }

    if (isEmpty(passwordInput.value) === true) {
      alert("비밀번호를 입력 하세요.");
      passwordInput.focus();
      return;
    }

    if (isEmpty(emailInput.value) === true) {
      alert("이메일을 입력 하세요.");
      emailInput.focus();
      return;
    }

    if (isEmpty(phoneInput.value) === true) {
      alert("전화번호를 입력 하세요.");
      phoneInput.focus();
      return;
    }

    if (isEmpty(juminInput.value) === true) {
      alert("주민번호를 입력 하세요.");
      juminInput.focus();
      return;
    }

    if (passwordInput.value !== passwordCheckInput.value) {
      alert("비밀번호가 일치하지 않습니다.");
      passwordCheckInput.focus();
      return;
    }
    //인증하기 버튼을 눌렀을 때 동작
 
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
          window.location.href = "/ehr/login/login_index.do";
        } else {
          alert(message.message);
        }
      },
      error: function (response) {
        console.log("error:" + response);
      }
    });
  }); //doSaveEnd

  //입력된 이메일 검사하는 로직 
  const codeInput = document.querySelector('#authCode');
  codeInput.addEventListener('click',function(){
    const inputCode = codeInput.value; //인증번호 입력 칸에 작성한 내용 가져오기
    
    console.log("입력코드 : " + inputCode);
    console.log("인증코드 : " + code);
      
    if(Number(inputCode) === code){
        $("#emailAuthWarn").html('인증번호가 일치합니다.');
        $("#emailAuthWarn").css('color', 'green');
        $('#emailAuth').attr('disabled', true);
       $('#email').attr('readonly', true);
      $('#doSave').prop('disabled', false);
    }else{
        $("#emailAuthWarn").html('인증번호가 불일치 합니다. 다시 확인해주세요!');
        $("#emailAuthWarn").css('color', 'red');
        $('#doSave').prop('disabled', true);
    }
  });
  
  moveToLoginButton.addEventListener("click", function (event) {
    console.log("moveToLoginButton click");

    if (confirm("로그인 화면으로 이동 하시겠습니까?") === false) return;

    window.location.href = "/ehr/login/login_index.do";
  });
});