// 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
//
// What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?


// Run: node Problem005.js


// this function will result in `RangeError: Maximum call stack size exceeded`. Screw node
// function gcd(a, b) {
//   if(a < b) return gcd(b, a);
//   return a - b === 0 ? b : gcd(b, a - b)
// }

function gcd(a, b) {
  if(a < b) return gcd(b, a);
  return a % b === 0 ? b : gcd(b, a % b)
}

function lcm(a, b) {
  return (a * b) / gcd(a, b);
}

function range(from, to) {
  var array = [];
  for(var i = from ; i <= to; i++) array.push(i);
  return array;
}

console.log("The solution is ", range(1, 20).reduce(lcm));
