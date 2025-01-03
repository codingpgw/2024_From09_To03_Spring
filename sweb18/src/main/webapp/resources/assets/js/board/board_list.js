document.addEventListener('DOMContentLoaded', function() {
  console.log('DOMContentLoaded');
  
  const moveToRegBtn = document.querySelector("#moveToRegBtn");
  moveToRegBtn.addEventListener("click",function(e){
    console.log("moveToRegBtn click");

    moveToReg();
  });

  function moveToReg(){
    console.log("moveToReg()");

    if(confirm("등록 화면으로 이동하시겠습니까?") === false) return;

    window.location.href="/ehr/board/board_reg_index.do";
  }

  const doRetrieveBtn = document.querySelector("#doRetrieveBtn");
  console.log(doRetrieveBtn);

  doRetrieveBtn.addEventListener("click",function(e){
    console.log("doRetrieveBtn click");
    e.preventDefault();

    doRetrieve(1);
  });

  function doRetrieve(pageNo){
    console.log("doRetrieve pageNo:"+pageNo);

    let userForm = document.userForm;
    userForm.pageNo.value = pageNo;
    userForm.action = "/ehr/board/doRetrieve.do";

    userForm.submit();
  }

});

function pageDoRetrieve(url,pageNo){
  console.log("pageDoRetrieve click");

  let userForm = document.userForm;

  userForm.pageNo.value = pageNo;
  userForm.action = url;    

  userForm.submit();
}