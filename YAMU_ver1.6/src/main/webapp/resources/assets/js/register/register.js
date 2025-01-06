document.addEventListener("DOMContentLoaded", function () {
  console.log("DOMContentLoaded");

  const doSaveButton = document.querySelector("#doSave");
  const moveToLoginButton = document.querySelector("#moveToLogin");

  const memIdInput = document.querySelector("#memId");                    // 아이디
  const nameInput = document.querySelector("#name");                      // 이름
  const passwordInput = document.querySelector("#password");              // 비밀번호
  const passwordCheckInput = document.querySelector("#passwordCheck");    // 비밀번호 확인
  const emailInput = document.querySelector("#email");                    // 이메일
  const phoneInput = document.querySelector("#phone");                    // 전화번호
  const birthDtInput = document.querySelector("#birthDt");                // 생년월일

  const emailDomain = document.querySelector("#email-domain");
  const customDomain = document.querySelector("#email-custom-domain");

  const checkIdButton = document.querySelector("#checkIdBtn");            // 아이디 중복 확인
  const idCheckMessage = document.querySelector("#idCheckMessage");       // 아이디 중복 확인 메시지
  const pwCheckMessage = document.querySelector("#pwCheckMessage");       // 비밀번호 일치 확인 메시지

  const sendEmailBtn = document.querySelector("#sendEmailBtn");           // 이메일 인증 번호 전송 버튼
  const authCodeInput = document.querySelector("#authCode");              // 입력한 인증 번호
  const checkCodeBtn = document.querySelector("#checkCodeBtn");           // 인증 번호 확인 버튼
  const sendCheckMessage = document.querySelector("#sendCheckMessage");   // 이메일 입력 메시지
  const codeCheckMessage = document.querySelector("#codeCheckMessage");   // 인증 번호 입력 메시지

  let code;                     // 전송된 인증 번호
  let isIdValid = false;        // 아이디 중복 여부
  let pwCheckValid = false;     // 비밀번호 중복 여부
  let emailCheckValid = false;  // 이메일 인증 여부

  // 아이디 중복 확인
  checkIdButton.addEventListener("click", function (event) {
    event.preventDefault();
    console.log("checkIdButton click");

    if (!memIdInput.value) {
      isIdValid = false;
      idCheckMessage.textContent = "아이디를 입력하세요.";
      idCheckMessage.style.color = "red";
      memIdInput.style.borderColor = "red";
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
          memIdInput.style.borderColor = "red";
          isIdValid = false;
        } else {
          idCheckMessage.textContent = "사용 가능한 아이디입니다.";
          idCheckMessage.style.color = "green";
          memIdInput.style.borderColor = "green";
          isIdValid = true;
        }
      },
      error: function (response) {
        console.log("error:" + response);
        alert("아이디 중복 체크 중 오류가 발생했습니다.");
        //memIdCheckMessage.className = "error-message";
        idCheckMessage.style.color = "red";
        memIdInput.style.borderColor = "red";
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
      passwordInput.style.borderColor = "green";
      passwordCheckInput.style.borderColor = "green";
      pwCheckValid = true;
    } else if (passwordInput.value !== passwordCheckInput.value) {
      pwCheckMessage.textContent = "비밀번호가 일치하지 않습니다.";
      pwCheckMessage.style.color = "red";
      passwordInput.style.borderColor = "red";
      passwordCheckInput.style.borderColor = "red";
      pwCheckValid = false;
    }
  }

  // 이메일 도메인 선택
  emailDomain.addEventListener("change", function (event) {
    if (this.value === "") {
      customDomain.style.display = "inline-block";
      customDomain.value = ""; // 직접 입력을 위해 초기화
      customDomain.focus();
    } else {
      customDomain.style.display = "inline-block";
      customDomain.value = this.value;
      customDomain.style.borderColor = "";
      sendCheckMessage.textContent = "";
    }
  });

  // 도메인 형식 유효성 검사
  customDomain.addEventListener("input", function() {
    const domainValue = customDomain.value.trim();
    const domainPattern = /^[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if (domainValue === "" || domainPattern.test(domainValue)) {
      customDomain.style.borderColor = "green";
      sendCheckMessage.textContent = "";
    } else {
      customDomain.style.borderColor = "red";
      sendCheckMessage.textContent = "올바른 도메인 형식으로 입력하세요. (예: example.com)";
      sendCheckMessage.style.color = "red";
    }
  });

  // 이메일 입력값 가져오기
  function getFullEmail() {
    const emailPrefix = emailInput.value.trim();
    let domain;
  
    if (emailDomain.value === "") {
      // 도메인을 직접 입력한 경우
      domain = customDomain.value.trim();
    } else {
      // 도메인을 선택한 경우
      domain = emailDomain.value.trim();
    }
  
    // 이메일 입력 값이 없거나 도메인이 없으면 null 반환
    if (!emailPrefix || !domain) {
      return null;
    }
  
    // 이메일 형식 확인 (선택 사항)
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const fullEmail = `${emailPrefix}@${domain}`;
    if (!emailRegex.test(fullEmail)) {
      return null;
    }
  
    return fullEmail;
  }

  //이메일 인증 버튼 입력시 메일로 이메일 전송
  sendEmailBtn.addEventListener("click", function (event) {
    event.preventDefault();
    console.log("checkIdButton click");
    const fullEmail = getFullEmail();
    console.log(fullEmail);

    if (!fullEmail) {
      sendCheckMessage.textContent = "유효한 이메일 주소를 입력하세요.";
      sendCheckMessage.style.color = "red";
      emailInput.style.borderColor = "red";
      customDomain.style.borderColor = "red";
      return;
    }

    $.ajax({
      type: "POST",
      url: "/ehr/register/emailAuth.do",
      data: { email: fullEmail },
      dataType: "json",
      success: function (result) {
        console.log("result: " + result);
        code = result;
        console.log("code: " + code);
        authCodeInput.disabled = false;

        sendCheckMessage.textContent = "인증 코드가 입력하신 이메일로 전송 되었습니다.";
        sendCheckMessage.style.color = "green";
        emailInput.style.borderColor = "green";
        authCodeInput.focus();
      },
      error: function () {
        sendCheckMessage.textContent = "인증 코드 전송에 실패했습니다.";
        sendCheckMessage.style.color = "red";
        emailInput.style.borderColor = "red";
      }
    });
  });

  //입력된 이메일 검사하는 로직 
  checkCodeBtn.addEventListener("click", function () {
    //인증번호 입력 칸에 작성한 내용 가져오기
    console.log("입력코드: " + authCodeInput.value);
    console.log("인증코드: " + code);

    const enteredCode = authCodeInput.value.trim();

    if (Number(enteredCode) === code) {
      codeCheckMessage.textContent = "인증번호가 일치합니다.";
      codeCheckMessage.style.color = "green";
      authCodeInput.style.borderColor = "green";
      emailCheckValid = true;
    } else {
      codeCheckMessage.textContent = "인증번호가 일치하지 않습니다.";
      codeCheckMessage.style.color = "red";
      authCodeInput.style.borderColor = "red";
      emailCheckValid = false;
    }
  });

  // 로그인 화면으로 이동
  moveToLoginButton.addEventListener("click", function (event) {
    console.log("moveToLoginButton click");

    if (confirm("로그인 화면으로 이동 하시겠습니까?") === false) return;

    window.location.href = "/ehr/login/login_index.do";
  });

  // 회원 가입 버튼
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

    if (isEmpty(birthDtInput.value) === true) {
      alert("주민번호를 입력하세요.");
      birthDtInput.focus();
      return;
    }

    if (!isIdValid) {
      alert("아이디 중복을 확인하세요.");
      return;
    }

    if (!emailCheckValid) {
      alert("이메일 인증번호를 확인하세요.");
      return;
    }

    if (!pwCheckValid) {
      pwCheckMessage.textContent = "";
      pwCheckMessage.style.color = "";
      return;
    }

    if (passwordInput.value !== passwordCheckInput.value) {
      pwCheckMessage.textContent = "비밀번호가 일치하지 않습니다.";
      pwCheckMessage.style.color = "red";
      passwordCheckInput.style.borderColor = "red";
      passwordCheckInput.focus();
      return;
    } else if (passwordInput.value === passwordCheckInput.value) {
      pwCheckMessage.textContent = "비밀번호가 일치합니다.";
      pwCheckMessage.style.color = "green";
      passwordCheckInput.style.borderColor = "green";
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
        "phone": phoneInput.value.replace(/-/g, ""),
        "birthDt": birthDtInput.value
      },
      success: function (response) {
        console.log("success response: " + response);
        const message = JSON.parse(response);

        if (message.messageId == 1) { //등록 성공
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
  });

  moveToLoginButton.addEventListener("click", function (event) {
    console.log("moveToLoginButton click");

    window.location.href = "/ehr/login/login_index.do";
  });

  phoneInput.addEventListener("input", (event) => {
    let value = event.target.value.replace(/[^0-9]/g, ""); // 숫자만 남기기
    if (value.length > 3 && value.length <= 7) {
      value = value.replace(/(\d{3})(\d+)/, "$1-$2");
    } else if (value.length > 7) {
      value = value.replace(/(\d{3})(\d{4})(\d+)/, "$1-$2-$3");
    }
    event.target.value = value;
  });
});