document.addEventListener('DOMContentLoaded', function() {
 console.log('DOMContentLoaded');
    const popUpBtn = document.getElementById('popUp');

    popUpBtn.addEventListener('click',function(e){
      console.log('popUpBtn click');

      //window.open('https://cafe.daum.net/pcwk',"_blank", "width=500, height=500");
      let options = "width=500, height=500,left=100,top=300";
      window.open('/js/j03/index06.html',"_blank",options);
    });
});