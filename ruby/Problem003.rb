# The prime factors of 13195 are 5, 7, 13 and 29.
#
# What is the largest prime factor of the number 600851475143 ?

target_number = 600851475143
largest_prime = 2


def prime?(number)
  return true if number == 2
  return false if (3...number).step(2).any? { |num| number % num == 0 }
  true
end

def next_prime(cur)
  return 3 if cur == 2
  next_suspect = cur + 2
  prime?(next_suspect) ? next_suspect : next_prime(next_suspect)
end


cur_prime = largest_prime

while target_number > 1
  while target_number % cur_prime == 0
    target_number /= cur_prime
    largest_prime = cur_prime
  end

  cur_prime = next_prime(cur_prime) # move to next prime
end

puts "Larget prime factor is #{largest_prime}"
