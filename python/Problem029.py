numbers = set(a ** b for a in range(2, 101) for b in range(2, 101))

print "Solution is %s" % len(numbers)
