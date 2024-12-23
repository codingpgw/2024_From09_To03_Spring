const greet = function(name){
  console.log("Hello, ",name);
}

function sayHello(callback){
  callback('이상무');
}

sayHello(greet);