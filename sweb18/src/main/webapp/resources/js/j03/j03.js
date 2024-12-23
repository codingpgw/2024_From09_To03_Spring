document.addEventListener('DOMContentLoaded',function(){

  const input = document.getElementById('inputField');

  input.addEventListener('keyup',function(event){
      console.log('입력된 키: ',event.key);
  });

});
