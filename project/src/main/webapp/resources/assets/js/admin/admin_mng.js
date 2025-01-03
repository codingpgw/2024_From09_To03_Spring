document.addEventListener("DOMContentLoaded", function () {
  console.log("DOMContentLoaded");

  const doUpdateButton = document.querySelector("#doUpdate");
  const doDeleteButton = document.querySelector("#doDelete");

  const memIdInput = document.querySelector("#memId");
  const passwordInput = document.querySelector("#password");  // 비밀번호
  const nameInput = document.querySelector("#name");          // 이름
  const emailInput = document.querySelector("#email");        // 이메일
  const phoneInput = document.querySelector("#phone");        // 전화번호
  const juminInput = document.querySelector("#jumin");        // 주민번호
  const memDivInput = document.querySelector("#memDiv");        // 주민번호

  

  doUpdateButton.addEventListener("click", function (event) {
    event.preventDefault();
    console.log("doUpdateButton click");

    if (isEmpty(memIdInput.value) === true) {
      alert('사용자 ID를 입력 하세요.');
      memIdInput.focus();
      return;
    }

    if (isEmpty(passwordInput.value) === true) {
      alert('비밀번호를 입력 하세요.');
      passwordInput.focus();
      return;
    }

    if (isEmpty(nameInput.value) === true) {
      alert('이름을 입력 하세요.');
      nameInput.focus();
      return;
    }

    if (isEmpty(emailInput.value) === true) {
      alert('이메일을 입력 하세요.');
      emailInput.focus();
      return;
    }

    if (isEmpty(phoneInput.value) === true) {
      alert('전화번호를 입력 하세요.');
      phoneInput.focus();
      return;
    }

    if (isEmpty(juminInput.value) === true) {
      alert('주민번호를 입력 하세요.');
      juminInput.focus();
      return;
    }

    if (isEmpty(memDivInput.value) === true) {
      alert('사용자 구분을 입력 하세요.');
      memDivInput.focus();
      return;
    }

    if (confirm('회원 정보를 수정 하시겠습니까?') === false) return;

    $.ajax({
      type: "POST",
      url: "/ehr/member/doUpdate.do",
      async: true,
      dataType: "html",
      data: {
        "memId": memIdInput.value,
        "password": passwordInput.value,
        "name": nameInput.value,
        "email": emailInput.value,
        "phone": phoneInput.value,
        "jumin": juminInput.value,
        "memDiv": memDivInput.value
      },
      success: function (response) {
        console.log("success response:" + response);
        const message = JSON.parse(response);
        if (1 == message.messageId) { // 수정 성공
          alert(message.message);
          //목록으로 화면 이동.
          window.location.href = '/ehr/admin/adminRetrieve.do';
        } else {
          alert(message.message);
        }

      },
      error: function (response) {
        console.log("error:" + response);
      }
    });
  });

  // 삭제
  doDeleteButton.addEventListener("click", function (event) {
    event.preventDefault();
    console.log("doDeleteButton click");

    console.log("memIdInput.value: ", memIdInput.value);

    if (confirm("회원을 삭제 하시겠습니까?") === false) return;

    $.ajax({
      type: "POST",
      url: "/ehr/member/doDelete.do",
      async: true,
      dataType: "html",
      data: {
        memId: memIdInput.value,
      },
      success: function (response) {
        console.log("success response: " + response);
        const message = JSON.parse(response);

        if (1 == message.messageId) {
          alert(message.message);
          window.location.href = "/ehr/admin/adminRetrieve.do"
        } else {
          alert(message.message);
        }
      },
      error: function (response) {
        console.log("error: " + response);
      },
    });
  });
});