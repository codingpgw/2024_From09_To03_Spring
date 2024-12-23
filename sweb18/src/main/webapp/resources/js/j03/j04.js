document.addEventListener('DOMContentLoaded', function() {
    console.log('DOMContentLoaded');

    const log = document.querySelector('#log');
    const box = document.querySelector('#box');

    //한 번 클릭
    box.addEventListener('click',function(){
      console.log('box click');

      log.innerHTML += "<p>click event 발생!</p>";
    });

    //더블클릭
    box.addEventListener('dblclick',() => {
      console.log('box click');

      log.innerHTML += "<p>dblclick event 발생!</p>";
    });

    box.addEventListener('mouseenter',() => {
      console.log('mouseenter');

      log.innerHTML += "<p>mouseenter event 발생!</p>";
    });

    box.addEventListener('mouseleave',() => {
      console.log('mouseleave');

      log.innerHTML += "<p>mouseleave event 발생!</p>";
    });

    box.addEventListener('mouseover',() => {
      console.log('mouseover');

      log.innerHTML += "<p>mouseover event 발생!</p>";
    });

    box.addEventListener('mouseout',() => {
      console.log('mouseout');

      log.innerHTML += "<p>mouseout event 발생!</p>";
    });
});