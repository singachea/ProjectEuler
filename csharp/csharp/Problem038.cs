//Take the number 192 and multiply it by each of 1, 2, and 3:
//
//  192 × 1 = 192
//	192 × 2 = 384
//	192 × 3 = 576
//	By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated product of 192 and (1,2,3)
//
//The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918273645, which is the concatenated product of 9 and (1,2,3,4,5).
//
//What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n > 1?

using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace csharp
{
	class Problem038
	{
		public static void Main (string[] args)
		{
			Console.WriteLine ($"Solutions is {Pandigitals().Max()}");
		}

		static IEnumerable<int> Pandigitals() {

			/* Our goal is to find k such that (k)(2k)...(nk) < 10^9 where n > 1
			 * Thus k must have some limits as follow:
			 * 					1000 <= k < 10000     n = 2    (actually we can have 5000 <= k < 10000)
			 * 					 100 <= k < 1000      n = 3
			 * 					  10 <= k < 100       n = 4
			 * 					   1 <= k < 10        n = 5
			 */

			for (int n = 2; n <= 5; n++) {
				int lowerLimit = (int)Math.Pow (10, 5 - n);
				for (int k = lowerLimit; k < lowerLimit * 10; k++) {
					var x = Enumerable.Range (1, n).Select (e => (e * k).ToString ()).Aggregate ((a, b) => a + b);
					if(IsPandigital (x))
						yield return int.Parse (x);
				}
					
			}

		}

		static bool IsPandigital(string num) {
			if (String.IsNullOrEmpty (num) || num.Length != 9 || Regex.IsMatch (num, "[^1-9]"))
				return false;

			return num.ToArray ().Distinct ().Count () == num.Length;
		}
	}
}
