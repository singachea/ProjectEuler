// Pentagonal numbers are generated by the formula, Pn=n(3n−1)/2. The first ten pentagonal numbers are:
//
// 1, 5, 12, 22, 35, 51, 70, 92, 117, 145, ...
//
// It can be seen that P4 + P7 = 22 + 70 = 92 = P8. However, their difference, 70 − 22 = 48, is not pentagonal.
//
// Find the pair of pentagonal numbers, Pj and Pk, for which their sum and difference are pentagonal and D = |Pk − Pj| is minimised; what is the value of D?


function pentagonal(n) {
  return n * (3 * n - 1) / 2;
}

function isPentagonal(k) {
  // just do reverse of equation
  var n = (1 + Math.sqrt(24 * k + 1))
  return n % 6 == 0 ? n / 6 : false;
}



function findD() {
  var i = 1;
  while(true) {
    var Pi = pentagonal(i);
    for(var j = i - 1; j >= 1; j--) { // the nearer, the better
      var Pj = pentagonal(j)
      if(isPentagonal(Pi - Pj) && isPentagonal(Pi + Pj)) return Pi - Pj;
    }

    i++;
  }

}

console.log("Solution is", findD());
