document.addEventListener('DOMContentLoaded', function() {
  console.log('DOMContentLoaded');
  
  const form = document.querySelector('#userForm');
  const nameField = document.querySelector('#name');
  const log = document.querySelector('#log');
  //console.log(nameField);
  function logMessage(message){
    log.innerHTML += `<p>${message}</p>`;
  }

  //권한
  const roleSelect = document.querySelector('#role');
  roleSelect.addEventListener('change',function(event){
    logMessage(`권한 선택:${event.target.value}`);
  });

  const emailField = document.querySelector('#email');
  emailField.addEventListener('focus',function(event){
    console.log('focus in');
    logMessage('이메일 필드 선택');
  });

  emailField.addEventListener('blur',function(event){
    logMessage('이메일 필드 blured');
  });

  nameField.addEventListener('input',function(event){
    console.log(`이름:${event.target.value}`);
    let nameValue = event.target.value;

    logMessage(nameValue);

    
  });

    form.addEventListener('submit',function(e){
      e.preventDefault();

      //form 객체를 통한 값 출력
      let nameValue = form.name.value;
      console.log(`nameValue:${nameValue}`);

      if(isEmpty(nameValue) === true){
        alert('이름을 입력하세요.');
        form.name.focus();
        return;
      }

      let emailValue = form.email.value;
      if(null === emailValue || emailValue.length == 0){
        alert('이메일을 입력하세요.');
        form.email.focus();
        return;
      }

      logMessage('form 전송');

      const formData = new FormData(form);
      logMessage(`전송 data:${JSON.stringify(Object.fromEntries(formData))}`);
    });
    
  


});