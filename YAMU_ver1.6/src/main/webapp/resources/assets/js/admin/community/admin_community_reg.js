document.addEventListener("DOMContentLoaded", function () {
  console.log("DOMContentLoaded");

  const doSaveButton = document.querySelector("#doSave");

  const setCmnCategoryInput = document.querySelector("#setCmnCategory");
  const setCmnDivInput = document.querySelector("#setCmnDiv");
  const titleInput = document.querySelector("#titleReg");
  const memIdInput = document.querySelector("#memIdReg");
  const contentInput = document.querySelector("#contentReg");

  doSaveButton.addEventListener("click", function (event) {
    console.log("doSaveButton click");

    console.log("memIdInput.value:", memIdInput.value);

    if(isEmpty (titleInput.value) === true){
      alert("제목을 입력 하세요.");
      titleInput.focus();
      return;
    }

    if(isEmpty (memIdInput.value) === true){
      alert("사용자 ID를 입력 하세요.");
      memIdInput.focus();
      return;
    }

    if(isEmpty (contentInput.value) === true){
      alert("내용을 입력 하세요.");
      contentInput.focus();
      return;
    }

    if(confirm("글을 등록 하시겠습니까?") === false) return;

    $.ajax({
      type: "POST",
      url: "/ehr/community/doSave.do",
      async: true,
      dataType: "html",
      data: {
          "category" : setCmnCategoryInput.value,
          "div"      : setCmnDivInput.value,
          "title"    : titleInput.value,
          "memId"    : memIdInput.value,
          "content"  : contentInput.value
      },
      success: function(response) {
          console.log("success response: " + response);
          const message = JSON.parse(response);

          if(1 == message.messageId){ //등록 성공
              alert(message.message);
              //목록으로 화면 이동.
              window.location.href = "/ehr/admin/adminRetrieveCommunity.do"; 
          } else {
            alert(message.message);
          }
      },
      error: function(response) {
          console.log("error:" + response);
      }
    });
  });
});