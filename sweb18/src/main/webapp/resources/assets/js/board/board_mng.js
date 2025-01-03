document.addEventListener('DOMContentLoaded', function() {
  console.log('DOMContentLoaded');
  
  const seqInput = document.querySelector('#seq');
  const divInput = document.querySelector('#div');

  const titleInput = document.querySelector('#title');
  const contentsTextArea = document.querySelector('#contentsTextArea');

  const moveToListBtn = document.querySelector("#moveToList");
  moveToListBtn.addEventListener('click',function(e){
    console.log("moveToListBtn click");

    window.location.href = "/ehr/board/doRetrieve.do?div="+divInput.value;
  });

  const doUpdateBtn = document.querySelector("#doUpdateBtn");
  doUpdateBtn.addEventListener('click',function(e){
    console.log("doUpdateBtn click");
    e.preventDefault();
    
    if(isEmpty(titleInput.value) === true){
      alert('제목을 입력하세요.');
      titleInput.focus();
      return;
    }

    if(isEmpty(simplemde.value()) === true){
      alert('내용을 입력하세요.');
      simplemde.focus();
      return;
    }

    console.log('${sessionScope.user.userId}');
    if(confirm('수정하시겠습니까?') === false)return;

    $.ajax({
      type: "Post",
      url: "/ehr/board/doUpdate.do",
      async: true,
      dataType: "html",
      data: {
          "seq": seqInput.value,
          "div": divInput.value,
          "title":titleInput.value,
          "contents" : simplemde.value()
      },
      success: function(response) {
          console.log("success response:" + response);
          const message = JSON.parse(response);
  
          if(1 == message.messageId){
            alert(message.message);
            window.location.href = "/ehr/board/doRetrieve.do?div="+divInput.value;
          }else{
            alert(message.message);
          }
      },
      error: function(response) {
          console.log("error:" + response);
      }
    });
  });



  const doDeleteBtn = document.querySelector("#doDeleteBtn");
  
  console.log(doDeleteBtn);

  doDeleteBtn.addEventListener("click",function(e){
    console.log("doDeleteBtn click");
    e.preventDefault();

    if(confirm('게시글을 삭제하시겠습니까?') === false) return;


    $.ajax({
    type: "GET",
    url: "/ehr/board/doDelete.do",
    async: true,
    dataType: "html",
    data: {
        "seq": seqInput.value
    },
    success: function(response) {
        console.log("success response:" + response);
        const message = JSON.parse(response);

        if(1 == message.messageId){
          alert(message.message);
          window.location.href = "/ehr/board/doRetrieve.do?div="+divInput.value;
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