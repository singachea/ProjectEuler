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
  var primes = List[Int](3)

  def possibleSet(length: Int): List[Int] = {
    while(primes.length < length) {
      primes = primes :+ nextPrime()
    }

    val last = primes.last

    var coprimes = List[Int](last)

    for(p <- primes.slice(0, primes.length - 1)) {
      if(arePermutedPrimes2(List(last, p))) {
        coprimes = coprimes :+ p
      }
    }

    if(coprimes.length < length) {
      primes = primes :+ nextPrime()
      possibleSet(length)
    }
    else {
      val suspectedPrimes = permuteList4(coprimes).filter(arePermutedPrimes2)
      if(suspectedPrimes.isEmpty) {
        primes = primes :+ nextPrime()
        possibleSet(length)
      }
      else {
        suspectedPrimes.head
      }
    }
  }



  def arePermutedPrimes2(list: List[Int]) = {
    permute2(list).forall(isPrime)
  }


  def permute2(list: List[Int]) = {
    for(i <- list; j <- list.diff(List(i)))
      yield Integer.parseInt(s"$i$j")
  }


  def permuteList4(list: List[Int]) = {
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