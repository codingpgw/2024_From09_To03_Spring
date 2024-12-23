//const clickButton = document.getElementById('clickBtn');
const clickButton = document.querySelector('#clickBtn');

console.log('clickButton:',clickButton);

clickButton.addEventListener('click',function(){
  // alert('Button click');

  console.log('클릭된 요소:',event.target);
  console.log('클릭된 요소 좌표:',event.clientX,event.clientY);
});


// clickButton.onclick = function(){
//   console.log('click');
// }