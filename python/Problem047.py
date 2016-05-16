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
        pfs = [primeFactors(i) for i in range(n, n + 4)]
        if [len(i) for i in pfs] == [length, length, length, length]:
            if len(set(k for sublist in pfs for k in sublist)) == 4 * length:
                return n
