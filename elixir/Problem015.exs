
# Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.
#
# How many such routes are there through a 20×20 grid?


defmodule Solution do

  def route(1, _), do: 1
  def route(_, 1), do: 1

  def route(x, y) when x > 1 and y > 1 do
    route(x - 1, y) + route(x, y - 1)
  end

  def grid(x, y) do
    route(x + 1, y + 1)
  end

end

IO.puts "Solution is #{Solution.grid(20, 20)}" # this can't finish! to make it work, we need some sort of memoization. find solution in C# folder!
