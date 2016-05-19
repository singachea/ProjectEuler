# There are exactly ten ways of selecting three from five, 12345:
#
# 123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
#
# In combinatorics, we use the notation, 5C3 = 10.
#
# In general,
#
# nCr =
# n!
# r!(n−r)!
# ,where r ≤ n, n! = n×(n−1)×...×3×2×1, and 0! = 1.
# It is not until n = 23, that a value exceeds one-million: 23C10 = 1144066.
#
# How many, not necessarily distinct, values of  nCr, for 1 ≤ n ≤ 100, are greater than one-million?

def factorial(n)
  return 1 if n <= 1
  (2..n).inject(1){ |product, el| product * el }
end

def c(n, r)
  factorial(n) / (factorial(r) * factorial(n - r))
end


sum = (1..100).map{ |n| (0..n).select{ |r| c(n, r) > 1_000_000 }.size }.inject(0){ |sum, el| sum + el }


puts "Solution is #{sum}"
