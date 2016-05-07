/**
  * Created by singachea on 5/7/16.
  */

//By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
//
//What is the 10 001st prime number?

object Problem007 {
  def main(args: Array[String]): Unit = {
    println(s"Solution is ${primeth(10001)}") //104743
  }

  def isPrime(number: Int): Boolean = {
    if(number == 2) return true
    !Range(2, number).exists(el => number % el == 0)
  }

  def nextPrime(number: Int): Int = {
    if(number == 2) return 3
    val nextSuspect = number + 2

    if(isPrime(nextSuspect)) nextSuspect else nextPrime(nextSuspect)
  }

  def primeth(th: Int): Int = {
    var (first, prime) = (1, 2)

    while(first < th) {
      first += 1
      prime = nextPrime(prime)
    }

    prime
  }


}
