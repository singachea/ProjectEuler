A = set(range(0, 10))

sum = sum(int(str.join('',(str(d1), str(d2), str(d3), str(d4), str(d5), str(d6), str(d7), str(d8), str(d9), str(d10))))
     for d6 in {0, 5}
     for d4 in {0, 2, 4, 6, 8} - {d6}
     for d1 in A - {d4, d6} - {0}
     for d2 in A - {d4, d6, d1}
     for d3 in A - {d4, d6, d1, d2}
     for d5 in A - {d4, d6, d1, d2, d3}
     for d7 in A - {d4, d6, d1, d2, d3, d5}
     for d8 in A - {d4, d6, d1, d2, d3, d5, d7}
     for d9 in A - {d4, d6, d1, d2, d3, d5, d7, d8}
     for d10 in A - {d4, d6, d1, d2, d3, d5, d7, d8, d9}
     if (d3 + d4 + d5) % 3 == 0
     if (100 * d5 + 10 * d6 + d7) % 7 == 0
     if (100 * d6 + 10 * d7 + d8) % 11 == 0
     if (100 * d7 + 10 * d8 + d9) % 13 == 0
     if (100 * d8 + 10 * d9 + d10) % 17 == 0
)

print "Solution is %s" % sum
