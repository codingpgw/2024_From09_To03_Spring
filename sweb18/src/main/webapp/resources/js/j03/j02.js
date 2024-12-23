$(document).ready(function(){
  const doSaveBtn = document.getElementById('doSave');
  const doRetrieveBtn = document.getElementById('doRetrieve');
  
  console.log(doSaveBtn);
  console.log(doRetrieveBtn);

  doSaveBtn.addEventListener('click',function(event){
    console.log('doSaveBtn click');
  });
  
  doRetrieveBtn.addEventListener('click',function(){
    console.log('doRetrieveBtn click');
  });

});//documnet.ready------------------------------------------

