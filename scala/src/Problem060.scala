import sun.security.util.Length

/**
  * Created by singachea on 5/22/16.
  */
object Problem060 {
  def main(args: Array[String]) = {
    val ps = new PrimeSet()
    val x = ps.possibleSet(5)
    println(x)
    println(x.sum)
  }

}

class PrimeSet {
  var primes = List[Int](3, 5, 7, 11) // don't need to start with 2

  def possibleSet(length: Int): List[Int] = {
    val last = primes.last

    val coprimes = last :: primes.slice(0, primes.length - 1).filter(p => arePermutedPrimes2(List(last, p)))

    if(coprimes.length >= length) {
      val suspectedPrimes = combination5outOfList(coprimes).filter(arePermutedPrimes2)
      if(suspectedPrimes.nonEmpty) {
        return suspectedPrimes.head
      }
    }

    primes = primes :+ nextPrime()
    possibleSet(length)
  }


  def arePermutedPrimes2(list: List[Int]) = {
    permute2outOfList(list).forall(isPrime)
  }

  def permute2outOfList(list: List[Int]) = {
    for(i <- list; j <- list.diff(List(i)))
      yield Integer.parseInt(s"$i$j")
  }


  def combination5outOfList(list: List[Int]) = {
    for(a <- list; b <- list; c <- list; d <- list; e <- list; if (a < b && b < c && c < d && d < e))
      yield List(a, b, c, d, e)
  }

  def nextPrime(): Int = {
    var last = primes.last + 2
    while(!isPrime(last))  last += 2
    last
  }

  def isPrime(num: Int): Boolean = {
    if(num == 2) return true
    if(num <= 1 || num % 2 == 0) return false
    val s = math.sqrt(num).asInstanceOf[Int]

    !Range(3, s + 1, 2).exists(num % _ == 0)
  }

}