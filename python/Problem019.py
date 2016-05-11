# You are given the following information, but you may prefer to do some research for yourself.
#
# 1 Jan 1900 was a Monday.
# Thirty days has September,
# April, June and November.
# All the rest have thirty-one,
# Saving February alone,
# Which has twenty-eight, rain or shine.
# And on leap years, twenty-nine.
# A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
#
# How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?

# Obviously you can do something like:
#  len(filter(lambda x: x == 6, (datetime.date(year, month, 1).weekday() for year in range(1901, 2001) for month in range(1, 13))))
# But that's not the point of this exercise!



def is_leap(year):
    return (year % 400 == 0) if (year % 100 == 0) else (year % 4 == 0)

def days_in_year(year):
    return 366 if is_leap(year) else 365

def days_in_month(month, year):
    switcher = {
        1: 31,
        2: 29 if is_leap(year) else 28,
        3: 31,
        4: 30,
        5: 31,
        6: 30,
        7: 31,
        8: 31,
        9: 30,
        10: 31,
        11: 30,
        12: 31
    }
    return switcher.get(month)

def days_in_date_from_1900(day, month, year):
    days_y = sum(days_in_year(d) for d in range(1900, year))
    days_m = sum(days_in_month(m, year) for m in range(1, month))
    return day + days_m + days_y - 1

def is_sunday(day, month, year):
    return (days_in_date_from_1900(day, month, year) + 1) % 7 == 0

print "Solution is %s" % sum((is_sunday(1, month, year) for year in range(1901, 2001) for month in range(1, 13)))
