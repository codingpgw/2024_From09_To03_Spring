/*
  Util function
  2024/12/19
*/

/**
 * 입력 값이 비어 있는지 확인하는 함수
 * @param {*} value 
 * @returns true(비어 있음)/false
 */
let isEmpty = function(value){
  if(value == null || value == undefined){
    return true;
  }

  if(typeof value === 'string' && value.trim() === ''){
    return true;
  }

  return false;
}