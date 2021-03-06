defmodule Solution do
  def card_value(c) do
    case c do
      "A" -> 14
      "K" -> 13
      "Q" -> 12
      "J" -> 11
      "T" -> 10
      x -> String.to_integer(x)
    end
  end

  def flush?(cards) do # or can use parttern matching here
    {_, color} = Enum.at(cards, 0)
    Enum.all?(cards, fn {_, c} -> c == color end)
  end

  def straight?(cards) do
    cards
      |> Enum.map(fn {v, _} -> card_value(v) end)
      |> Enum.sort
      |> straight_up
  end

  def rank(cards) do
    array = cards
    |> Enum.map(fn {v, _} -> card_value(v) end)
    |> Enum.group_by(&(&1))
    |> Enum.map(fn {k, v} -> {k, length(v)} end) # result is in [{key: length}]

    cond do
      straight?(cards) and flush?(cards) -> rank_straight_flush(array)
      straight?(cards) -> rank_straight(array)
      flush?(cards) -> rank_flush(array)
      true ->
        values = Enum.map(array, fn {_, v} -> v end) |> Enum.sort(&(&1 < &2))
        case check_type(values) do
          :high_card -> rank_pairs(array)
          :pair -> rank_pairs(array)
          :two_pair -> rank_pairs(array)
          :three_of_a_kind -> rank_three(array)
          :full_house -> rank_full_house(array)
          :four_of_a_kind -> rank_four(array)
        end
    end

  end

  #--------helpers----------
  defp straight_up([a, b, c, d, e]) when b == a + 1 and c == a + 2 and d == a + 3 and e == a + 4, do: true
  defp straight_up(_), do: false

  defp check_type([1, 1, 1, 1, 1]), do: :high_card
  defp check_type([1, 1, 1, 2]), do: :pair
  defp check_type([1, 2, 2]), do: :two_pair
  defp check_type([1, 1, 3]), do: :three_of_a_kind
  defp check_type([2, 3]), do: :full_house
  defp check_type([1, 4]), do: :four_of_a_kind


  defp partition_by_size(array, type_count, high_zeroes, low_zeroes ) do
    {pairs, rest} =  array |> Enum.partition(fn {_, v} -> v == type_count end)
    pairs = pairs
            |> Enum.map(fn {v, _} -> v end)
            |> Enum.sort(fn a, b -> a > b end)
            |> Enum.map(&Integer.to_string(&1))
            |> Enum.map(&("#{String.rjust(&1, 2, ?0)}#{String.duplicate("0", high_zeroes)}"))
            |> Enum.join("")

    rest = rest
            |> Enum.map(fn {v, _} -> v end)
            |> Enum.sort(fn a, b -> a > b end)
            |> Enum.map(&Integer.to_string(&1))
            |> Enum.map(&("#{String.rjust(&1, 2, ?0)}#{String.duplicate("0", low_zeroes)}"))
            |> Enum.join("")

    "#{pairs}#{rest}"
  end

  defp rank_padding(array, zeroes) do
    r = array
      |> Enum.map(fn {v, _} -> v end)
      |> Enum.sort(fn a, b -> a > b end)
      |> Enum.map(&Integer.to_string(&1))
      |> Enum.map(&("#{String.rjust(&1, 2, ?0)}"))
      |> Enum.join("")


    "#{r}#{String.duplicate("0", zeroes)}"
  end

  defp rank_pairs(array) do
    partition_by_size(array, 2, 4, 0)
    # 1 p -> (1|2)0000(6) = 11|12 digits
    # 2 p -> (1|2)0000(2)0000(2) = 13|14 digits
  end

  defp rank_three(array) do
    partition_by_size(array, 3, 9, 0)
    # 3 p -> (1|2)000000000(4) = 14|15 digits
  end

  defp rank_straight(array) do
    rank_padding(array, 6)
  end

  defp rank_flush(array) do
    rank_padding(array, 7)
  end

  defp rank_full_house(array) do
    partition_by_size(array, 3, 9, 4)
    # 3 p, 2p -> (1|2)0000000000(2)0000 = 17|18 digits
  end

  defp rank_four(array) do
    partition_by_size(array, 5, 15, 0)
    # 4 p -> (1|2)000000000000000(2) = 18|19 digits
  end

  defp rank_straight_flush(array) do
    rank_padding(array, 10)
  end

  # we don't need this one.
  # defp rank_royal_flush(array) do
  #   rank_straight_flush(array)
  # end
end


{:ok, content} = File.read("./p054_poker.txt")
lines = content
          |> String.split(~r{\n}, trim: true)
          |> Enum.map(&(&1
                        |> String.split(~r{\s}, trim: true)
                        |> Enum.map(fn el -> el
                                          |> String.split("", trim: true)
                                          |> List.to_tuple end)
                        |> Enum.split(5)))

          # {[{"A", "S"}, {"K", "D"}, {"3", "D"}, {"J", "D"}, {"8", "H"}],
          #      [{"7", "C"}, {"8", "C"}, {"5", "C"}, {"Q", "D"}, {"6", "C"}]}

count = lines
  |> Enum.map(fn {p1, p2} -> {Solution.rank(p1) |> String.to_integer , Solution.rank(p2) |> String.to_integer} end)
  |> Enum.filter(fn {p1, p2} -> p1 > p2 end)
  |> Enum.count


IO.puts "Solution is #{count}"
