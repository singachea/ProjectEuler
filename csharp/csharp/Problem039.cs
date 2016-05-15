//A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
//
//	a2 + b2 = c2
//		For example, 32 + 42 = 9 + 16 = 25 = 52.
//
//	There exists exactly one Pythagorean triplet for which a + b + c = 1000.
//		Find the product abc.
//

using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace csharp
{
	class Problem039
	{
		public static void Main (string[] args)
		{
			var max = PythagoreanParameter ().GroupBy (e => e).OrderByDescending (g => g.Count ()).First ();
			Console.WriteLine ($"key: {max.Key}, count: {max.Count ()}");
		}

		static IEnumerable<int> PythagoreanParameter() {
			var maxParameter = 1000;
			var largestA = maxParameter / 3;

			for (int p = 1; p <= maxParameter; p++) {
				for (int a = 1; a <= largestA; a++) {
					for (int b = a; b <= (p - a) / 2; b++) {
						if (a * b + b * b == (p - a - b) * (p - a - b)) {
							yield return p;
						}
					}
				}
			}
		}

	}
}
