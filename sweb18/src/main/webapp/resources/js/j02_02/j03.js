const childBtn = document.querySelector('#child');
const parentDiv = document.querySelector('#parent');

childBtn.addEventListener('click',function(event){
  console.log('child click');
  alert('child click');

  event.stopPropagation();
});

parentDiv.addEventListener('click',function(event){
  console.log('parent click');
  alert('parent click');
})