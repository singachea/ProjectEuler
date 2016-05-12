//Euler discovered the remarkable quadratic formula:
//
//  n² + n + 41
//
//It turns out that the formula will produce 40 primes for the consecutive values n = 0 to 39. However, when n = 40, 402 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and certainly when n = 41, 41² + 41 + 41 is clearly divisible by 41.
//
//The incredible formula  n² − 79n + 1601 was discovered, which produces 80 primes for the consecutive values n = 0 to 79. The product of the coefficients, −79 and 1601, is −126479.
//
//Considering quadratics of the form:
//
//  n² + an + b, where |a| < 1000 and |b| < 1000
//
//where |n| is the modulus/absolute value of n
//  e.g. |11| = 11 and |−4| = 4
//Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of primes for consecutive values of n, starting with n = 0.

/**
  * Created by singachea on 5/8/16.
  */


object Problem027 {
  def main(args: Array[String]): Unit = {

    var max = 0
    var product = 0

    for(a <- Range(-999, 1000); b <- Range(-999, 1000)) {
      val x = maxPrimes(a, b)
      if(x > max) {
        max = x
        product = a * b
        println(s"max = $max, a = $a, b = $b, product = $product")
      }
    }

    println(s"Solution is $product")
  }

  def maxPrimes(a: Int, b: Int): Int = {
    var counter = 0
    val f = (n: Int) => n * n + a * n + b
    while(isPrime(f(counter))) counter += 1

    counter
  }


  def isPrime(num: Int): Boolean = {
    if(num <= 1) return false
    val s = Math.sqrt(num).asInstanceOf[Int]
    !Range(2, s + 1).exists( num % _ == 0)
  }
}
