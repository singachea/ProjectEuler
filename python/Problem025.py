

def fibonacci():
    prev_prev, prev, cur = 0, 0, 1
    while True:
        yield cur
        prev_prev, prev, cur = prev, cur, cur + prev


counter, target, g = 1, 1000, fibonacci()

while len(str(next(g))) < target:
    counter += 1

print "Solution is %s" % counter
