# Starting with 1 and spiralling anticlockwise in the following way, a square spiral with side length 7 is formed.
#
# 37 36 35 34 33 32 31
# 38 17 16 15 14 13 30
# 39 18  5  4  3 12 29
# 40 19  6  1  2 11 28
# 41 20  7  8  9 10 27
# 42 21 22 23 24 25 26
# 43 44 45 46 47 48 49
#
# It is interesting to note that the odd squares lie along the bottom right diagonal, but what is more interesting is that 8 out of the 13 numbers lying along both diagonals are prime; that is, a ratio of 8/13 ≈ 62%.
#
# If one complete new layer is wrapped around the spiral above, a square spiral with side length 9 will be formed. If this process is continued, what is the side length of the square spiral for which the ratio of primes along both diagonals first falls below 10%?

defmodule Solution do
  def smallest_size_length(n \\ 1, prime_count \\ 0, non_prime_count \\ 1) do
    case prime_percentage(n, prime_count, non_prime_count) do
      {x, _, _} when x < 0.1 -> n
      {_, p, np} ->
        smallest_size_length(n+2, p, np)
    end
  end

  defp prime_percentage(1, _, _), do: {1, 0, 1} # special case

  defp prime_percentage(n, prime_n, non_prime_n) do
    {p, np} = [f(n, :tr), f(n, :tl), f(n, :bl), f(n, :br)] |> Enum.partition(&(prime?(&1)))
    prime_count = prime_n + length(p)
    non_prime_count = non_prime_n + length(np)
    {prime_count / (prime_count + non_prime_count), prime_count, non_prime_count}
  end

  defp f(n, :br), do: n * n # bottom right
  defp f(n, :tr), do: f(n - 2, :br) + (n - 1) # top right
  defp f(n, :tl), do: f(n, :tr) + (n - 1) # top left
  defp f(n, :bl), do: f(n, :tl) + (n - 1) # bottom left

  defp prime?(num) do
    case num do
      2 -> true
      x when x <= 1 or rem(x, 2) == 0  -> false
      _ ->
        s = :math.sqrt(num) |> round
        not (:lists.seq(3, s, 2) |> Enum.any?(&(rem(num, &1) == 0)))
    end
  end
end


IO.puts "Solution is #{Solution.smallest_size_length}"
