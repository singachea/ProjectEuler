// It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.
//
// 9 = 7 + 2×12
// 15 = 7 + 2×22
// 21 = 3 + 2×32
// 25 = 7 + 2×32
// 27 = 19 + 2×22
// 33 = 31 + 2×12
//
// It turns out that the conjecture was false.
//
// What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?


function isPrime(num) {
  if(num <= 1) return false;
  if(num == 2) return true;
  if(num % 2 == 0) return false;

  var s = Math.sqrt(num);

  for(i = 3; i <= s; i += 2) {
    if(num % i == 0) return false;
  }

  return true;
}


function findSmallestNumber() {
  var i = 1;
  while(true) {
    var num = 2 * i + 1;
    var j = 0;
    var ok = false;
    while(num - 2 * j * j > 0) {
      if (isPrime(num - 2 * j * j)) {
        ok = true;
        break;
      }
      j++;
    }

    if(!ok) return num;
    i += 1;
  }
}


console.log("Solution is ", findSmallestNumber());
