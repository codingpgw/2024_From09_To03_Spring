function greet(name){
  console.log("안녕하세요 "+name);
}

console.log(greet('이상무'));

function greetDefault(name='무명씨'){
  console.log("안녕하세요 "+name);
}

console.log(greetDefault());