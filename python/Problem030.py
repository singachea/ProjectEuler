# Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:
#
# 1634 = 14 + 64 + 34 + 44
# 8208 = 84 + 24 + 04 + 84
# 9474 = 94 + 44 + 74 + 44
# As 1 = 14 is not a sum it is not included.
#
# The sum of these numbers is 1634 + 8208 + 9474 = 19316.
#
# Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.

import math

def powerCompare(power):
    n, result = 2, []

    while ((9**power) * math.log(n, 10) >= n):
        s = sum(map(lambda x:  int(x) ** power, str(n)))
        if s == n:
            result.append(n)
        n += 1

    return result

print("Solution is %s" % sum(powerCompare(5)))
