//An irrational decimal fraction is created by concatenating the positive integers:
//
//0.123456789101112131415161718192021...
//
//It can be seen that the 12th digit of the fractional part is 1.
//
//If dn represents the nth digit of the fractional part, find the value of the following expression.
//
//d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000

using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace csharp
{
	class Problem040
	{
		public static void Main (string[] args)
		{
			var product = Enumerable
							.Range (0, 7)
							.Select (n => (int)Math.Pow (10, n))
							.Select (e => Position(e))
 							.Aggregate ((a, b) => a * b);


			Console.WriteLine ($"Solution is {product}");
		}


		static int Position(int num) {
			int n = 1, left_over = num; 

			while (left_over - (int)(n * 9 * Math.Pow (10, n - 1)) > 0) {
				left_over -= (int)(n * 9 * Math.Pow (10, n - 1));
				n++;
			}
				
			var remainder = left_over % n;
			var divider = left_over / n;
			var target = (int)Math.Pow (10, n - 1) + divider;

			return remainder > 0 ? TermPos (target, remainder) : TermPos (target - 1, n);
		}


		static int TermPos(int num, int remainder) {
			return int.Parse (num.ToString ().ToCharArray () [remainder - 1].ToString ());
		}
	}
}
