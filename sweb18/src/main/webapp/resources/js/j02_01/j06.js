function createMultiplier(multiplier){
  
  return function(num){
    return num * multiplier;
  }
}

const double = createMultiplier(2);

console.log(double(5));