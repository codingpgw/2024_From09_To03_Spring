document.addEventListener('DOMContentLoaded', function() {
  console.log('DOMContentLoaded');
  
  const doSaveBtn =  document.querySelector('#doSave');
  
  const userIdInput = document.querySelector('#userId');

  doSaveBtn.addEventListener('click',function(e){
    console.log('doSaveBtn click');

    //Validation
    console.log('userIdInput.value:',userIdInput.value);
    if(isEmpty(userIdInput.value) == true){
      alert('사용자 ID를 입력하세요.');
      userIdInput.focus();
      return;
    }
    if(confirm('회원 등록을 하시겠습니까?') == false){return;}
  
  });

  const moveToListBtn = document.querySelector('#moveToList');

  moveToListBtn.addEventListener('click',function(e){
    console.log('moveToListBtn click');
    if(confirm('회원 목록으로 이동하시겠습니까?') == false){return;}

    window.location.href = "/ehr/user/doRetrieve.do"
  });
});