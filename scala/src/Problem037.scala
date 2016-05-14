//The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right, and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.
//
//Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
//
//NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.

object Problem037 {

  def main(args: Array[String]): Unit = {
    val oddPrimes = Range(11, 1000000, 2).filter(isOddPrime).filter(isTruncatable)

    println(oddPrimes.mkString(", "))
    println(s"Solution is ${oddPrimes.sum}")
  }


  def isTruncatable(num: Int): Boolean = {
    var left = num
    var right = num

    while(left > 0 && isPrime(left)) left = left % math.pow(10, left.toString.length - 1).asInstanceOf[Int]
    if(left > 0) return false

    while(right > 0 && isPrime(right)) right = right / 10
    if(right > 0 ) return false  // for clarity

    true
  }

  def isPrime(num: Int): Boolean = {
    if(num <= 1) return false
    val s = Math.sqrt(num).asInstanceOf[Int]
    !Range(2, s + 1, 1).exists(num % _ == 0)
  }

  def isOddPrime(num: Int): Boolean = {
    val s = Math.sqrt(num).asInstanceOf[Int]
    !Range(3, s + 1, 2).exists(num % _ == 0)
  }

}
