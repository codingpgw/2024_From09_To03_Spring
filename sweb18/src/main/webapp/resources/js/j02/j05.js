const score = prompt('성적을 입력하세요.');

let grade = '';
if(score >= 90){
  grade = 'A';
}else if(score >= 80){
  grade = 'B';
}else if(score >= 70){
  grade = 'C';
}else if(score >= 60){
  grade = 'D';
}else{
  grade = 'F';
}

console.log(`점수는 ${score}, 학점은 ${grade}`);