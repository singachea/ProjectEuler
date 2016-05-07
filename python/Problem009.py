
number = [ a * b * (1000 - a - b) for a in range(1, 1000) for b in range(1, 1000) if a < b and a * a + b * b == (1000 - a - b) * (1000 - a - b) ][0]

print "Solution is %s" % number
