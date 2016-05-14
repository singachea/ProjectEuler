using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;

namespace csharp
{
	class Problem033
	{
		public static void Main (string[] args)
		{
			var product = allCuriousFractions ().Aggregate ((a, b) => Tuple.Create (a.Item1 * b.Item1, a.Item2 * b.Item2));

			Console.WriteLine ($"Solution is {smallestFraction(product.Item1, product.Item2).Item2}");
		}

		static IEnumerable<Tuple<int, int>> allCuriousFractions() {
			for (int i = 10; i < 99; i++) {
				for (int j = i + 1; j <= 99; j++) {
					if (isCurious (i, j)) {
						yield return Tuple.Create(i, j);
					}
				}
			}
		}


		static bool isCurious(int x, int y) {
			if (x < 10 || x > 99 || y < 10 || y > 99 || x >= y || x % 10 == 0 || y % 10 == 0)
				return false;

			var a = new List<int> (){ x / 10, x % 10 };
			var b = new List<int> (){ y / 10, y % 10 };

			var intersect = a.Intersect (b);
			if (intersect.Count () != 1) return false;

			var common = intersect.First ();

			a.Remove (common);
			b.Remove (common);

			return areFractionsEquivalent(x, y, a.First (), b.First ());
		}


		private static bool areFractionsEquivalent(int a, int b, int c, int d) {
			return smallestFraction (a, b).Equals (smallestFraction (c, d));
		}


		private static Tuple<int, int> smallestFraction(int a, int b) {
			var d = gcd (a, b);
			return Tuple.Create(a / d, b / d);
		}


		private static int gcd(int a, int b) {
			if (a < b) return gcd (b, a);
			
			return a % b == 0 ? b : gcd (b, a % b);
		}


	}
}
