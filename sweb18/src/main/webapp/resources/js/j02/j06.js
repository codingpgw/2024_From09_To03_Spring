let day = prompt('요일 숫자를 입력하세요.');

console.log(typeof day);

day = parseInt(day);
console.log(typeof day);
switch(day){
  case 1:
    console.log(`${day}은 월요일입니다.`);
  break;

  case 2:
    console.log(`${day}은 화요일입니다.`);
  break;
  
  case 3:
    console.log(`${day}은 수요일입니다.`);
  break;

  default:
    console.log(`${day}은 주중이 아닙니다..`);
  break;
}