# The cube, 41063625 (345^3), can be permuted to produce two other cubes: 56623104 (384^3) and 66430125 (405^3). In fact, 41063625 is the smallest cube which has exactly three permutations of its digits which are also cube.
#
# Find the smallest cube for which exactly five permutations of its digits are cube.

### please use ruby >= 2.3
### first temp... the brute force... doesn't work well

# def perfect_cubed?(num)
#   root_cube = num ** (1.0/3)
#   root_cube.round ** 3 == num
# end
#
# def find_cubed_set(group_size)
#   x = Enumerator.new do |y|
#     n = 1
#     loop do
#       num = n ** 3
#       puts "-----> N: #{n} -> #{num} (#{(1..(num.to_s.size)).inject(:*)})"
#       y << num
#       n += 1
#     end
#   end
#
#   a = []
#   while a.size != group_size do
#     num = x.next
#     a = num
#           .to_s.split('')
#           .permutation
#           .to_a
#           .map{|el| el.join('').to_i }
#           .uniq
#           .select { |el| el >= num }
#           .select{ |el| perfect_cubed?(el) }
#   end
#
#   a
# end
#
# r = find_cubed_set(5)
#
# puts r.inspect


def smallest_cube(size, limit = 1000)
  cubed_list = (1..10000).each_with_object({}) do |el, acc|
    x = (el ** 3).to_s.split("").sort.join("")
    acc[x] ||= []
    acc[x] << el
  end

  x = cubed_list.values.select{ |list| list.size == size }&.first&.first
  x ? x ** 3 : smallest_cube(size, limit + 1000) # keep increasing until there is smallest one
end


puts "Solution is #{smallest_cube(5)}"
