# If we take 47, reverse and add, 47 + 74 = 121, which is palindromic.
#
# Not all numbers produce palindromes so quickly. For example,
#
# 349 + 943 = 1292,
# 1292 + 2921 = 4213
# 4213 + 3124 = 7337
#
# That is, 349 took three iterations to arrive at a palindrome.
#
# Although no one has proved it yet, it is thought that some numbers, like 196, never produce a palindrome. A number that never forms a palindrome through the reverse and add process is called a Lychrel number. Due to the theoretical nature of these numbers, and for the purpose of this problem, we shall assume that a number is Lychrel until proven otherwise. In addition you are given that for every number below ten-thousand, it will either (i) become a palindrome in less than fifty iterations, or, (ii) no one, with all the computing power that exists, has managed so far to map it to a palindrome. In fact, 10677 is the first number to be shown to require over fifty iterations before producing a palindrome: 4668731596684224866951378664 (53 iterations, 28-digits).
#
# Surprisingly, there are palindromic numbers that are themselves Lychrel numbers; the first example is 4994.
#
# How many Lychrel numbers are there below ten-thousand?
#
# NOTE: Wording was modified slightly on 24 April 2007 to emphasise the theoretical nature of Lychrel numbers.


defmodule Solution do
  def palindrome?(num) do
    str = Integer.to_string(num)
    str == String.reverse(str)
  end

  def will_be_palindrome?(_, counter) when counter >= 50, do: false

  def will_be_palindrome?(num, counter) do
    case {counter, palindrome?(num)} do
      {x, true} when x > 0 -> true
      _ ->
        r = num |> Integer.to_string
                |> String.reverse
                |> String.to_integer
        will_be_palindrome?(num + r, counter + 1)
    end
  end

end


count = (10..9999)
        |> Enum.filter(fn x -> !Solution.will_be_palindrome?(x, 0) end)
        |> Enum.count

IO.puts "Solution is #{count}"
