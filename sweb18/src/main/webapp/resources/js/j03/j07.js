document.addEventListener('DOMContentLoaded', function() {
  console.log('DOMContentLoaded');
  
  const doSaveBtn = document.getElementById('doSave');
  const doDeleteBtn = document.getElementById('doDelete');

  doDeleteBtn.addEventListener('click',function(event){
    console.log('doDeleteBtn click');
    
    //확인 : true
    //실패 : false
    if(window.confirm('삭제하시겠습니까?') != true){
      return;
    }
    //let status = window.confirm('삭제하시겠습니까?');
    
    window.alert('삭제 로직');
  });

  doSaveBtn.addEventListener('click',function(event){
    console.log('doSaveBtn click');

    window.alert('doSaveBtn click');
  });

});