def n_range(func, min, max):
    n = 1
    while(func(n) < min):
        n += 1
    while(func(n) <= max):
        yield func(n)
        n += 1

def unique(l):
    return len(set(l)) == len(l)

def is_cycle(l):
    return all(l[i] % 100 == int(l[i+1] / 100) for i in range(-1, len(l) - 1))

def combination(ls, minus_list):
    terms = set(map(lambda x: x[1], ls))
    minus_set = set(map(lambda x: x[1], minus_list))
    return filter(lambda x: x[1] in (terms - minus_set), ls)

def p3_func(n):
    return int(n * (n + 1) / 2)

def p4_func(n):
    return int(n * n)

def p5_func(n):
    return int(n * (3 * n - 1) / 2)

def p6_func(n):
    return int(n * (2 * n - 1))

def p7_func(n):
    return int(n * (5 * n - 3) / 2)

def p8_func(n):
    return int(n * (3 * n - 2))


p3 = list((x, 3) for x in n_range(p3_func, 1000, 9999))
p4 = list((x, 4) for x in n_range(p4_func, 1000, 9999))
p5 = list((x, 5) for x in n_range(p5_func, 1000, 9999))
p6 = list((x, 6) for x in n_range(p6_func, 1000, 9999))
p7 = list((x, 7) for x in n_range(p7_func, 1000, 9999))
p8 = list((x, 8) for x in n_range(p8_func, 1000, 9999))

# pl = [x for el in [p3, p4, p5, p6, p7, p8] for x in el]
# [(a, b, c, d, e, f) for a in pl for b in pl for c in pl for d in pl for e in pl for f in pl if unique([a[1], b[1], c[1], d[1], e[1], f[1]]) and is_cycle([a[0], b[0], c[0]], d[0], e[0], f[0])]

pl = [x for el in [p3, p4, p5, p6, p7, p8] for x in el]
[(a, b, c, d, e, f) for a in pl for b in combination(pl, [a]) for c in combination(pl, [a, b]) for d in combination(pl, [a, b, c]) for e in combination(pl, [a, b, c, d]) for f in combination(pl, [a, b, c, d, e]) if is_cycle([a[0], b[0], c[0], d[0], e[0], f[0]])]


# pl = [x for el in [p3, p4, p5] for x in el]
# [(a, b, c) for a in pl for b in combination(pl, [a]) for c in combination(pl, [a, b]) if is_cycle([a[0], b[0], c[0]])]
