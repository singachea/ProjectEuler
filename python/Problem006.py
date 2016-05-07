# Run `python Problem006.py`

r = range(1, 100 + 1)

square_of_sum = pow(sum(r), 2)
sum_of_square = sum(map(lambda x: x * x, r))

print "Solution is %s" % (square_of_sum - sum_of_square)
