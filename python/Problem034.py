# 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
#
# Find the sum of all numbers which are equal to the sum of the factorial of their digits.
#
# Note: as 1! = 1 and 2! = 2 are not sums they are not included.

import math

def factorialCompare():
    n, result = 10, []

    while (math.factorial(9) * math.log(n, 10) >= n):
        s = sum(map(lambda x: math.factorial(int(x)), str(n)))
        if s == n:
            result.append(n)
        n += 1

    return result

print("Solution is %s" % sum(factorialCompare()))
