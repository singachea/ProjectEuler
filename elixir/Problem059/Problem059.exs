# Each character on a computer is assigned a unique code and the preferred standard is ASCII (American Standard Code for Information Interchange). For example, uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.
#
# A modern encryption method is to take a text file, convert the bytes to ASCII, then XOR each byte with a given value, taken from a secret key. The advantage with the XOR function is that using the same encryption key on the cipher text, restores the plain text; for example, 65 XOR 42 = 107, then 107 XOR 42 = 65.
#
# For unbreakable encryption, the key is the same length as the plain text message, and the key is made up of random bytes. The user would keep the encrypted message and the encryption key in different locations, and without both "halves", it is impossible to decrypt the message.
#
# Unfortunately, this method is impractical for most users, so the modified method is to use a password as a key. If the password is shorter than the message, which is likely, the key is repeated cyclically throughout the message. The balance for this method is using a sufficiently long password key for security, but short enough to be memorable.
#
# Your task has been made easy, as the encryption key consists of three lower case characters. Using cipher.txt (right click and 'Save Link/Target As...'), a file containing the encrypted ASCII codes, and the knowledge that the plain text must contain common English words, decrypt the message and find the sum of the ASCII values in the original text.

use Bitwise

defmodule Solution do

  @common_words ~w{the with} # some common english words

  def sum(key_length, cipher_text) do
    char_base(key_length)
      |> Stream.map(&(deciper(&1, cipher_text)))
      |> Stream.filter(&(Enum.all?(@common_words, fn w -> String.contains?(&1, w) end)))
      |> Enum.at(0)
      |> to_char_list
      |> Enum.sum
  end

  def deciper(key, cipher_text) do
    Stream.cycle(key)
      |> Enum.take(length(cipher_text))
      |> Enum.zip(cipher_text)
      |> Enum.map(fn {k, v} -> bxor(k, v) end)
      |> to_string
  end

  def char_base(char_len) do
    total = :math.pow(26, char_len) |> round
    (0..(total - 1))
      |> Stream.map(&(char_mapper(&1) |> String.rjust(char_len, ?a) |> to_char_list ))
  end

  def char_mapper(x), do: char_mapper(x, [])
  def char_mapper(x, list) when x < 26, do: [x | list] |> Enum.map(&(&1 + ?a)) |> to_string

  def char_mapper(num, list) do
    d = div(num, 26)
    r = rem(num, 26)

    char_mapper(d, [r | list])
  end

end


{:ok, content} = File.read("./p059_cipher.txt")
bytes = content
          |> String.strip
          |> String.split(",", trim: true)
          |> Enum.map(&String.to_integer(&1))

IO.puts "Solution is #{Solution.sum(3, bytes)}"
