document.addEventListener('DOMContentLoaded', function() {
    console.log('DOMContentLoaded');

    const form = document.querySelector('#myForm');

    form.addEventListener('submit',function(event){
      event.preventDefault();//기본 동작 취소
      console.log('폼이 제출되었습니다.');
    });
});