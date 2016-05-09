using System;
using System.Collections;
using System.Linq;

namespace csharp
{
	class Problem015
	{
		public static void Main (string[] args)
		{

			var n = 21; // grid 20x20 is 21x21 points
			var matrix = new long[n][];
		
			for (var i = 0; i < matrix.Length; i++) {
				matrix [i] = new long[n];
			}


			for (var i = matrix.Length - 2; i >= 0; i--) {
				for (var j = matrix [0].Length - 2; j >= 0; j--) {
					matrix [i] [j] = route (i, j, matrix);
				}
			}
				

			Console.WriteLine ($"Solution is {matrix [0] [0]}");

		}

		static long route(int x, int y, long[][] matrix) {
			if (x == matrix.Length - 1 || y == matrix[0].Length - 1)
				return 1;
			
			if (matrix [x] [y] != 0)
				return matrix [x] [y];

			return route (x + 1, y, matrix) + route (x, y + 1, matrix);
		}
	}
}
