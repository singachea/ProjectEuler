

defmodule Solution do

  def gcd(a, b) when b > a, do: gcd(b, a)
  def gcd(a, b) when rem(a, b) == 0, do: b
  def gcd(a, b), do: gcd(b, rem(a, b))

  def simplify({a, b}) do
    d = gcd(a, b)
    {div(a, d), div(b, d)}
  end


  # simplify fraction of a + b / c
  def fraction(a, b, c) do
    simplify({a * c + b, c})
  end

  # simplify fraction of a / b / c
  def fraction_level(a, b, c) do
    simplify({a * c , b})
  end

  # def sqrt_generator(_), do: 2 # testing purpose

  def e_generator(k) when rem(k, 3) == 1, do: 2 * (div(k, 3) + 1)
  def e_generator(_), do: 1

  def generator(k), do: e_generator(k) # can switch here

  def reciprocal(fr, 0), do: fr

  def reciprocal({a, b}, k) do
    {x, y} = fraction(generator(k-1), a, b)
    {u, v} = fraction_level(1, x, y)
    reciprocal({u, v}, k - 1)
  end


  def convergent(base, k) do #starting index from 0 and exclude base
    {x, y} = reciprocal({1, generator(k)}, k)
    fraction(base, x, y)
  end
end

{numerator, _} = Solution.convergent(2, 98)

sum = numerator
      |> Integer.to_string
      |> String.split("", trim: true)
      |> Enum.map(&(String.to_integer(&1) ))
      |> Enum.sum


IO.puts "Solution is #{sum}"
