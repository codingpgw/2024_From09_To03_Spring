document.addEventListener('DOMContentLoaded', function() {
  console.log('DOMContentLoaded');
  
  const seqInput = document.querySelector('#cmnNo');
  const divInput = document.querySelector('#cmn_div');
  const doDeleteBtn = document.querySelector("#doDeleteBtn");
  
  console.log(doDeleteBtn);

  doDeleteBtn.addEventListener("click",function(e){
    console.log("doDeleteBtn click");
    e.preventDefault();

    if(confirm('게시글을 삭제하시겠습니까?') === false) return;
  });

  $.ajax({
    type: "GET",
    url: "/ehr/community/doDelete.do",
    async: true,
    dataType: "html",
    data: {
        "cmnNo": seqInput.value
    },
    success: function(response) {
        console.log("success response:" + response);
        const message = JSON.parse(response);

        if(1 == message.messageId){
          alert(message.message);
          window.location.href = "/ehr/community/doRetrieve.do?div="+divInput.value;
        }else{
          alert(message.message);
        }
    },
    error: function(response) {
        console.log("error:" + response);
    }
 });

});