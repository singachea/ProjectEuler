using System;
using System.Collections;
using System.Linq;

namespace csharp
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			var slots = new long[1000000];
			var index = -1;
			var max = 0L;

			for (var i = 0; i < slots.Length; i++) {
				slots [i] = sequence (i + 1, 0, slots);
				if (slots [i] > max) {
					max = slots [i];
					index = i;
				}
			}

			Console.WriteLine ($"starting number = {index + 1}, max = {max}");

		}

		static long sequence(long n, long step, long[] cache) {
			if(n == 1) return step + 1;
			if (n <= cache.Length && cache [n - 1] != 0)
				return step + cache [n - 1];
			
			return sequence (n % 2 == 0 ? n / 2 : 3 * n + 1, step + 1, cache);
		}
	}
}
