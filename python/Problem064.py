from math import sqrt

def gcd(a, b):
    if a < b:
        return gcd(b, a)
    if a % b == 0:
        return b
    return gcd(b, a % b)

def find_partition(x, a, b, c):
    y = int(b * (c * sqrt(x) + a) / (c * c * x - a * a))
    newC = b * c
    newA = -(a * b - y * (c * c * x - a * a))
    newB = c * c * x - a * a
    d = gcd(abs(newA), gcd(newB, newC))
    newA = int(newA / d)
    newB = int(newB / d)
    newC = int(newC / d)

    return (y, newA, newB, newC)


def find_loop(x, a, b, c):
    ll = []
    temp = find_partition(x, a, b, c)
    while not temp in ll:
        ll.append(temp)
        temp = find_partition(x, temp[1], temp[2], temp[3])

    return list(u[0] for u in ll)

def is_perfect_square_root(x):
    s = int(sqrt(x))
    return s * s == x

def sqrt_num(x):
    if is_perfect_square_root(x):
        return (int(sqrt(x)), [])
    else:
        a = int(sqrt(x))
        b = 1
        c = 1
        return (a, find_loop(x, a, b, c))

r = [sqrt_num(x) for x in range(1, 10001)]
odds = list(x for x in r if len(x[1]) % 2 == 1)

print("Solution is %s" % len(odds))
