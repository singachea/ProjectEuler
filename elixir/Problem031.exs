# In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:
#
# 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
# It is possible to make £2 in the following way:
#
# 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
# How many different ways can £2 be made using any number of coins?

defmodule Solution do
  @coin_values [200, 100, 50, 20, 10, 5, 2, 1]
  @target_value 200

  def permutate(_, 0), do: 1
  def permutate([], _), do: 0

  def permutate([h | tail], left_over) when h > left_over, do: permutate(tail, left_over)

  def permutate([h | tail], left_over) do
    d = div(left_over, h)
    (0..d)
        |> Enum.map(&(permutate(tail, left_over - &1 * h)))
        |> Enum.sum
  end

  def ways do
    permutate(@coin_values, @target_value)
  end

end


IO.puts "Solution is #{Solution.ways}"
