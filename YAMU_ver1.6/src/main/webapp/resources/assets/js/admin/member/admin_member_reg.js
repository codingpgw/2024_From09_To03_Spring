document.addEventListener("DOMContentLoaded", function () {
  console.log("DOMContentLoaded");

  const doSaveButton = document.querySelector("#doSave");

  const memIdInput = document.querySelector("#memIdReg");
  const passwordRegInput = document.querySelector("#passwordReg");
  const nameRegInput = document.querySelector("#nameReg");
  const emailRegInput = document.querySelector("#emailReg");
  const phoneRegInput = document.querySelector("#phoneReg");
  const birthDtRegInput = document.querySelector("#birthDtReg");

  doSaveButton.addEventListener("click", function (event) {
    console.log("doSaveButton click");

    console.log("memIdInput.value:", memIdInput.value);
    console.log("birthDtRegInput.value:", typeof birthDtRegInput.value);

    if (isEmpty(memIdInput.value) === true) {
      alert("사용자 ID를 입력 하세요.");
      memIdInput.focus();
      return;
    }

    if (isEmpty(passwordRegInput.value) === true) {
      alert("비밀번호를 입력 하세요.");
      passwordRegInput.focus();
      return;
    }

    if (isEmpty(nameRegInput.value) === true) {
      alert("이름을 입력 하세요.");
      nameRegInput.focus();
      return;
    }

    if (isEmpty(emailRegInput.value) === true) {
      alert("이메일을 입력 하세요.");
      emailRegInput.focus();
      return;
    }

    if (isEmpty(phoneRegInput.value) === true) {
      alert("전화번호를 입력 하세요.");
      phoneRegInput.focus();
      return;
    }

    if (isEmpty(birthDtRegInput.value) === true) {
      alert("생년월일을 입력 하세요.");
      birthDtRegInput.focus();
      return;
    }

    if (confirm("회원 등록 하시겠습니까?") === false) return;

    // fetch(`/ehr/admin/adminMemberSelectOne.do?memId=${memId}`)
    //   .then(response => {
    //     if (!response.ok) {
    //       throw new Error(`HTTP error! Status: ${response.status}`);
    //     }
    //     return response.json();
    //   })
    //   .then(data => {
    //     if ((memId !== memIdInput.value) === false) return;

    //   })
    //   .catch(error => {
    //     console.log("Error fetching member data:", error);
    //     alert("중복된 회원 ID입니다.");
    //   });
    
    

    $.ajax({
      type: "POST",
      url: "/ehr/member/doSave.do",
      async: true,
      dataType: "html",
      data: {
        "memId": memIdInput.value,
        "password": passwordRegInput.value,
        "name": nameRegInput.value,
        "email": emailRegInput.value,
        "phone": phoneRegInput.value,
        "birthDt": birthDtRegInput.value
      },
      success: function (response) {
        console.log("success response: " + response);
        const message = JSON.parse(response);

        if (1 == message.messageId) { //등록 성공
          alert(message.message);
          //목록으로 화면 이동.
          window.location.href = "/ehr/admin/adminRetrieveMember.do";
        } else if (2 == message.messageId) {
          alert(message.message);
          
          return;
        } else {
          alert(message.message);
        }
      },
      error: function (response) {
        console.log("error:" + response);
      }
    });
  });
});