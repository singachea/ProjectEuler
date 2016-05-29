# The 5-digit number, 16807=7^5, is also a fifth power. Similarly, the 9-digit number, 134217728=8^9, is a ninth power.
#
# How many n-digit positive integers exist which are also an nth power?

# the goal is simple:
#     10 ^ (n - 1) <= k ^ n < 10 ^ n
#     10 ^ ((n - 1) / n) <= k < 10
#     if 10 ^ ((n - 1) / n) > 9, then n >  1 / (1 - log(9))

import math

def is_n_digit_power(k, n):
    return len(str(k ** n)) == n


max_n = int(1 / (1 - math.log(9, 10)))

x = len([k for n in range(1, max_n + 1) for k in range(int(10 ** ((n - 1) / n)), 10) if is_n_digit_power(k, n)])

print "Solution is %s" % x
