defmodule Solution do

  def find_pattern(numerator, denominator, dlist, rlist) do
    # list = [numerator | list]
    r = rem(numerator * 10, denominator)
    d = div(numerator * 10, denominator)

    index = Enum.find_index(rlist, &(&1 == r))
    rlist = [r | rlist]
    IO.inspect {d, r}
    cond do
      r == 0 -> dlist
      index -> [d | (dlist |> Enum.slice(0, index) |> Enum.reverse)]
      true -> find_pattern(r, denominator, [d | dlist], rlist)
    end
  end

  def dlen(numerator, denominator) do
    length(find_pattern(numerator, denominator, [], []))
  end

end


Solution.find_pattern(1, 12, [], [])


#
# max = 1..999
#         |> Enum.map(&Solution.dlen(1, &1))
#         |> Enum.max
#
#
# IO.puts "Solution is #{max}"
