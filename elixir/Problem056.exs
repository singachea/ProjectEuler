# A googol (10**100) is a massive number: one followed by one-hundred zeros; 100**100 is almost unimaginably large: one followed by two-hundred zeros. Despite their size, the sum of the digits in each number is only 1.
#
# Considering natural numbers of the form, a**b, where a, b < 100, what is the maximum digital sum?


defmodule Solution do

  def power(a, b) do
    (1..b)
      |> Enum.reduce(1, fn (_, acc) -> a * acc end)
  end

  def power_sum(a, b) do
    power(a, b)
      |> Integer.to_string
      |> String.split("", trim: true)
      |> Enum.map(&(String.to_integer(&1)))
      |> Enum.sum
  end

  def max_power(limit) do
    for a <- (1..(limit - 1)), b <- (1..(limit - 1)) do
      power_sum(a, b)
    end
      |> Enum.max
  end
end

IO.puts "Solution is #{Solution.max_power(100)}"
