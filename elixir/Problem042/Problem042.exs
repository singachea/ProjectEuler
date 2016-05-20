# The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:
#
# 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
#
# By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle number then we shall call the word a triangle word.
#
# Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common English words, how many are triangle words?


{:ok, content} = File.read("./p042_words.txt")

alpha_score = fn (name) ->
  name
    |> String.upcase
    |> String.to_char_list
    |> Enum.map(&(&1 - 64))
    |> Enum.sum
end

triangle_list = 1..100 |> Enum.map(&( &1 * (&1 + 1) / 2 ))

count = content
          |> String.replace("\"", "")
          |> String.split(",", trim: true)
          |> Enum.map(&alpha_score.(&1))
          |> Enum.filter(&(Enum.any?(triangle_list, fn e -> e == &1 end)))
          |> Enum.count


IO.puts "Solution is #{count}"
