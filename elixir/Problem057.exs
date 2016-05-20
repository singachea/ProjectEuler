# It is possible to show that the square root of two can be expressed as an infinite continued fraction.
#
# √ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...
#
# By expanding this for the first four iterations, we get:
#
# 1 + 1/2 = 3/2 = 1.5
# 1 + 1/(2 + 1/2) = 7/5 = 1.4
# 1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
# 1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...
#
# The next three expansions are 99/70, 239/169, and 577/408, but the eighth expansion, 1393/985, is the first example where the number of digits in the numerator exceeds the number of digits in the denominator.
#
# In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?


#-----------------------------------------------------------------------------

# The sequence can be written as
# f(n) = 1 + 1 / (1 + f(n-1)), and dealing is with fraction headache! Thus need to innovative :D
# if f(n-1) = {a, b} as notation of fraction a/b, then f(n) = {2 * b + a, a + b}

defmodule Solution do
  def f(0), do: {1, 1}
  def f({a, b}), do: {2 * b + a, a + b}
  def f(n), do: f(f(n - 1))
end



count = 1..1000
          |> Enum.map(&Solution.f(&1))
          |> Enum.filter(fn {a, b} -> String.length(Integer.to_string(a)) > String.length(Integer.to_string(b)) end)
          |> Enum.count


IO.puts "Solution is #{count}"
