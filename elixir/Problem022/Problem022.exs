# Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first names, begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply this value by its alphabetical position in the list to obtain a name score.
#
# For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. So, COLIN would obtain a score of 938 × 53 = 49714.
#
# What is the total of all the name scores in the file?


{:ok, content} = File.read("./p022_names.txt")

alpha_score = fn (name) ->
  name
    |> String.upcase
    |> String.to_char_list
    |> Enum.map(&(&1 - 64))
    |> Enum.sum
end

score = content
          |> String.replace("\"", "")
          |> String.split(",", trim: true)
          |> Enum.sort
          |> Enum.map(&alpha_score.(&1))
          |> Enum.with_index(1)
          |> Enum.map(fn {alpha, index} -> alpha * index end)
          |> Enum.sum


IO.puts "Solution is #{score}"
