import scala.collection.parallel.immutable.ParRange
import scala.collection.parallel.mutable.ParArray

/**
  * Created by singachea on 5/8/16.
  */

//The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
//
//Find the sum of all the primes below two million.

object Problem010 {
  def main(args: Array[String]): Unit = {
    val start = System.currentTimeMillis()
    // `Long` data is twice slower than `Int`. Interesting! In this case, we can just use `Int`, but `Long` is for larger number

    // val sum = Range.Long(2L, 2000000L, 1L).filter(isPrime).sum // running time: 4.75s
    // val sum = 2L + Range.Long(3L, 2000000L, 2L).filter(isOddPrime).sum // running time: 2.616s
    val sum = 2L + Range.Long(3L, 2000000L, 2L).par.filter(isOddPrime).sum // running time: 1.571s

    // you probably wanna do the clever way of saving the list prime instead of looping through every/odd numbers. Well but it's slower!

    println(s"Solution is ${sum}")
    println((System.currentTimeMillis() - start) / 1000.0)
  }


  def isPrime(num: Long): Boolean = {
    val s = Math.sqrt(num).asInstanceOf[Long]
    !Range.Long(2L, s + 1L, 1L).exists(num % _ == 0)
  }

  def isOddPrime(num: Long): Boolean = {
    val s = Math.sqrt(num).asInstanceOf[Long]
    !Range.Long(3L, s + 1L, 2L).exists(num % _ == 0)
  }

}
