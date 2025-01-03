document.addEventListener("DOMContentLoaded", function () {
  console.log("DOMContentLoaded");

  const doUpdateButton = document.querySelector("#doUpdate");
  const doDeleteButton = document.querySelector("#doDelete");

  const cmnNoInput = document.querySelector("#cmnNo");
  const titleInput = document.querySelector("#title");
  const viewInput = document.querySelector("#view");
  const memIdInput = document.querySelector("#memId");
  const regDtInput = document.querySelector("#regDt");
  const modDtInput = document.querySelector("#modDt");
  const contentInput = document.querySelector("#content");

  doUpdateButton.addEventListener("click", function (event) {
    event.preventDefault();
    console.log("doUpdateButton click");

    console.log("cmnNoInput.value:", cmnNoInput.value);

    if (isEmpty(titleInput.value) === true) {
      alert("제목을 입력 하세요.");
      titleInput.focus();
      return;
    }

    if (isEmpty(viewInput.value) === true) {
      alert("조회수를 입력 하세요.");
      viewInput.focus();
      return;
    }

    if (isEmpty(memIdInput.value) === true) {
      alert("사용자 ID를 입력 하세요.");
      memIdInput.focus();
      return;
    }

    if (isEmpty(regDtInput.value) === true) {
      alert("등록일을 입력 하세요.");
      regDtInput.focus();
      return;
    }

    if (isEmpty(modDtInput.value) === true) {
      alert("수정일을 입력 하세요.");
      modDtInput.focus();
      return;
    }

    if (isEmpty(contentInput.value) === true) {
      alert("내용을 입력 하세요.");
      contentInput.focus();
      return;
    }

    if (confirm("글을 수정 하시겠습니까?") === false) return;

    $.ajax({
      type: "POST",
      url: "/ehr/community/doUpdate.do",
      async: true,
      dataType: "html",
      data: {
        "cmnNo": cmnNoInput.value,
        "title": titleInput.value,
        "content": contentInput.value
      },
      success: function (response) {
        console.log("success response:" + response);
        const message = JSON.parse(response);
        if (1 == message.messageId) { // 수정 성공
          alert(message.message);
          //목록으로 화면 이동.
          window.location.href = "/ehr/admin/adminRetrieveCommunity.do";
        } else {
          alert(message.message);
        }

      },
      error: function (response) {
        console.log("error:" + response);
      }
    });
  });

  doDeleteButton.addEventListener("click", function (event) {
    event.preventDefault();
    console.log("doDeleteButton click");

    console.log("cmnNoInput.value: ", cmnNoInput.value);

    if (confirm("글을 삭제 하시겠습니까?") === false) return;

    $.ajax({
      type: "GET",
      url: "/ehr/community/doDelete.do",
      async: true,
      dataType: "html",
      data: {
        "cmn_no" : cmnNoInput.value
      },
      success: function (response) {
        console.log("success response: " + response);
        const message = JSON.parse(response);

        if (1 == message.messageId) {
          alert(message.message);
          window.location.href = "/ehr/admin/adminRetrieveCommunity.do";
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