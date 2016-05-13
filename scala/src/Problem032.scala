//We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigital.
//
//The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.
//
//Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.
//
//HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.


object Problem032 {
  def main(args: Array[String]): Unit = {
    // the goal is to find number a, b and c where a * b = c and they combine a string running from 1 to 9
    // first let make a <= b and thus a > 1 because if a = 1 then b = c where b is duplicate of c
    // second b < 5,000, otherwise c >= 10,000 that lead to combining string of 10 digits
    // third c = a * b < 10,000 because max(a * b) = 9 * 999 < 10,000. thus a^2 < 10,000 => a < 100

    val numbers = for(a <- Range(2, 100); b <- Range(a, 5000) if a * b < 10000; if isPandigital(a, b, a * b, 9)) yield (a * b)

    println(s"Solution is ${numbers.distinct.sum}")

  }

  def isPandigital(a: Int, b: Int, c: Int, length: Int): Boolean = {
    val array = (a.toString + b.toString + c.toString).split("")
    if(array.contains("0")) return false // remove if it contains 0
    array.distinct.length == array.length && array.length == length
  }


}
