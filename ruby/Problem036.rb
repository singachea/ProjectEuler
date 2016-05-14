# The decimal number, 585 = 1001001001_2 (binary), is palindromic in both bases.
#
# Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
#
# (Please note that the palindromic number, in either base, may not include leading zeros.)

sum = (1...1_000_000)
        .select{ |n| n.to_s == n.to_s.reverse && n.to_s(2) == n.to_s(2).reverse }
        .inject(0){ |sum, el| sum + el }



puts "Solution is #{sum}"
