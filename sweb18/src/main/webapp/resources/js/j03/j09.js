document.addEventListener('DOMContentLoaded', function() {
 console.log('DOMContentLoaded');
    console.log("브라우저:",window.navigator.userAgent);
    console.log("브라우저:",window.navigator.language);
    console.log("location.href:",window.location.href);

    //window.history.forward();
    const moveToBtn = document.querySelector('#moveTo');

    moveToBtn.addEventListener('click',function(e){
      window.location.href = "index06.html";
    });
});