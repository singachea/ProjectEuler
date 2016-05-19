# It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different order.
#
# Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.


def transform(num)
  num.to_s.split("").sort.join("").to_i
end

def find_permuted_multiple
  (1..1_000_000).each do |num|
    return num if (2..6).all?{ |multiplier| transform(num) == transform(num * multiplier) }
  end
end


puts "Solution is #{find_permuted_multiple}"
