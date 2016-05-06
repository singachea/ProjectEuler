# A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
#
# Find the largest palindrome made from the product of two 3-digit numbers.


# $> elixir Problem004.exs

defmodule Solution do
  def find_max_palindrome(max, min) when max >= min, do: find_pair(max * max, max, min)

  defp palindrome?(number) do
    str = Integer.to_string number
    String.reverse(str) == str
  end

  defp find_pair(num, _, min) when num < min * min, do: nil

  defp find_pair(num, max, min) do
    cond do
      palindrome?(num) and splitable?(num, max, min) -> num
      true -> find_pair(num - 1, max, min)
    end
  end

  defp splitable?(num, max, min) do
    max..min
      |> Enum.any?(fn el ->
          {r, d} = {rem(num, el), div(num, el)}
          r == 0 and d <= max and d >= min
        end)
  end
end


IO.puts "Solution is #{Solution.find_max_palindrome(999, 100)}"
