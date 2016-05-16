# The first two consecutive numbers to have two distinct prime factors are:
#
# 14 = 2 x 7
# 15 = 3 x 5
#
# The first three consecutive numbers to have three distinct prime factors are:
#
# 644 = 2**2 x 7 x 23
# 645 = 3 x 5 x 43
# 646 = 2 x 17 x 19.
#
# Find the first four consecutive integers to have four distinct prime factors. What is the first of these numbers?

from collections import Counter

def primeFactors(num):
    factors = []
    n = 2
    while num > 1:
        while num % n == 0:
            factors.append(n)
            num = num / n
        n += 1
    fc = Counter(factors)
    return list(i * fc[i] for i in fc)

def consecutive_numbers(length):
    n = 1
    while True:
        n += 1

        if (not len(primeFactors(n)) == length) or (not len(primeFactors(n + 1)) == length):
            continue # make thing slightly faster!

        pfs = [primeFactors(i) for i in range(n, n + length)]

        if all(len(i) == length for i in pfs):
            if len(set(k for sublist in pfs for k in sublist)) == length * length:
                return n

print("Solution is ", consecutive_numbers(4))
