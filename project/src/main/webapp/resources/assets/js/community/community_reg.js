document.addEventListener('DOMContentLoaded', function() {
  console.log('DOMContentLoaded');
  
  const divInput = document.querySelector("#cmn_div");
  const titleInput = document.querySelector("#cmnTitle");
  const regIdInput = document.querySelector("#memId");
  const contentTextArea = document.querySelector("#contentsTextArea");

  const doSaveBtn = document.querySelector("#doSave");
  doSaveBtn.addEventListener("click",function(e){
    console.log('doSaveBtn click');

    if(isEmpty(titleInput.value) === true){
      alert('제목을 입력하세요.');
      titleInput.focus();
      return;
    }

    if(isEmpty(regIdInput.value) === true){
      alert('로그인 후 이용이 가능합니다.');
      //regIdInput.focus();

      if(confirm('로그인 화면으로 이동하시겠습니까?') === false) return;
      const retUrl = "/ehr/community/community_reg_index.do";
      window.location.href = "/ehr/login/login_index.do?retUrl="+retUrl;
      return;
    }

    if(isEmpty(contentTextArea.value) === true){
      alert('내용을 입력하세요.');
      contentTextArea.focus();
      return;
    }

    if(confirm('등록하시겠습니까?') === false)return;

      $.ajax({
        type: "POST",
        url: "/ehr/community/doSave.do",
        async: true,
        dataType: "html",
        data: {
            "div"  : divInput.value,
            "title": titleInput.value,
            "regId": regIdInput.value,
            "contents":contentTextArea.value
        },
        success: function(response) {
          console.log("success response:" + response);
          const message = JSON.parse(response);
          if( 1 == message.messageId){//등록 성공
            alert(message.message);
            //목록으로 화면 이동.
            window.location.href = '/ehr/community/doRetrieve.do?div='+divInput.value; 
          }else{
            alert(message.message);
          }


          alert(message.message);
        },
        error: function(response) {
            console.log("error:" + response);
        }

      });

  });

});