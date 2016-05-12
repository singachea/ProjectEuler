# A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:
#
# 1/2	= 	0.5
# 1/3	= 	0.(3)
# 1/4	= 	0.25
# 1/5	= 	0.2
# 1/6	= 	0.1(6)
# 1/7	= 	0.(142857)
# 1/8	= 	0.125
# 1/9	= 	0.(1)
# 1/10	= 	0.1
# Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.
#
# Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.

defmodule Solution do
  def dlen(numerator, denominator) do
    find_pattern(numerator, denominator, [], [])
      |> Enum.join("")
      |> String.length
  end

  defp expand_numerator(numerator, denominator, leading_zeroes) do
    case numerator do
      x when x < denominator -> expand_numerator(numerator * 10, denominator, "0" <> leading_zeroes)
      _ -> {numerator, leading_zeroes}
    end
  end

  defp find_pattern(numerator, denominator, dlist, rlist) do

    {numerator, leading_zeroes} = expand_numerator(numerator * 10, denominator, "") # we need leading_zeroes. e.g. for case 1 / 13 => 10 / 13 => 100 / 13

    r = rem(numerator, denominator) # remainder
    d = leading_zeroes <> Integer.to_string(div(numerator, denominator))

    index = Enum.find_index(rlist, &(&1 == r)) # find index from the head of array to see the repeat pattern

    cond do
      r == 0 -> [] # no repeat
      index -> [d | (dlist |> Enum.slice(index + 1, length(dlist)))] # repeat here, and we attach the last one to head and slice from index + 1
      true -> find_pattern(r, denominator,  dlist ++ [d], rlist ++ [r]) # I don't use | because i don't want ot reverse and mess up the thought process
    end
  end
end

{ _, max_index } = 1..999
                    |> Enum.map(&Solution.dlen(1, &1))
                    |> Enum.with_index(1)
                    |> Enum.max_by(fn {value, _} -> value end)

IO.puts "Solution is #{max_index}"
