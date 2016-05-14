//The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.
//
//  There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
//
//How many circular primes are there below one million?

object Problem035 {

  def main(args: Array[String]): Unit = {

    val oddPrimes = Range(3, 1000000, 2).filter(isOddPrime)
    val cPrimes = 2 +: oddPrimes.filter(isCircularPrime(_, oddPrimes))

    println(s"Solution is ${cPrimes.size}")
  }


  def isCircularPrime(num: Int, list: IndexedSeq[Int]): Boolean = {
    val cNums = circularNumbers(num)

    cNums match {
      case null => false
      case _ => cNums.forall(list.contains(_))
    }
  }


  def circularNumbers(num: Int): List[Int] = {
    var nums = num.toString.split("")

    if(nums.map(_.toInt).exists(_ % 2 == 0)) return null // OMG, without this it will be very slow

    val x = for(i <- Range(0, nums.length)) yield {
      nums = nums.tail :+ nums.head
      nums.mkString("").toInt
    }

    x.toList
  }


  def isOddPrime(num: Int): Boolean = {
    val s = Math.sqrt(num).asInstanceOf[Int]
    !Range(3, s + 1, 1).exists(num % _ == 0)
  }

}
