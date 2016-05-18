# By replacing the 1st digit of the 2-digit number *3, it turns out that six of the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.
#
# By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number is the first example having seven primes among the ten generated numbers, yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993. Consequently 56003, being the first member of this family, is the smallest prime with this property.
#
# Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit, is part of an eight prime value family.

require 'pry'


def prime?(num)
  return true if num == 2
  return false if num <= 1 || num % 2 == 0
  s = Math.sqrt(num).to_i

  !(3..s).step(2).any? { |i| num % i == 0 }
end


def permute(array, size)
  length = array.size
  (1...2**length).map{ |el| el.to_s(2).rjust(length, "0").split("") } # spilt into binary
                .select { |el| el.select {|el| el == "1"}.size == size } # select only the right size
                .map{ |el| el.to_enum.with_index(0).map{ |e, i| e == "0" ? array[i] : "*" }.join("") } # replace the original number
end


def prime_family(pattern, size)
  range = pattern.start_with?("*") ? (1..9) : (0..9)

  primes = range.map{ |d| pattern.gsub("*", d.to_s).to_i }
        .select { |el| prime?(el) }

  primes.size == size ? primes : nil
end


def smallest_prime_with_family(start, max_digit_size, family_size)

  (start..1_000_000).each do |num|
    puts "tick: #{num}" if num % 100000 == 0
    next unless prime?(num)

    (1..max_digit_size).each do |digit_size|
      permute(num.to_s.split(""), digit_size).each do |el|
        if x = prime_family(el, family_size)
          return x
        end
      end
    end
  end

  nil
end



primes = smallest_prime_with_family(1, 3, 8) # you try max_digit_size to 4 or 5 to see how

if primes
  puts "Family:"
  puts primes
  puts "---------------"
  puts "Solution is #{primes.first}"
end
