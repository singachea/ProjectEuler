# The series, 11 + 22 + 33 + ... + 1010 = 10405071317.
#
# Find the last ten digits of the series, 11 + 22 + 33 + ... + 10001000.

print "Solution is %s" % (sum(x ** x for x in range(1, 1001)) % 10 ** 10)
