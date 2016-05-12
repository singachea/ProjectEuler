//A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:
//
//        012   021   102   120   201   210
//
//        What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?


import java.util.ArrayList;
import java.util.Arrays;

public class Problem028 {
    static int counter = 0;
    static final int target = 1000000;

    public static void main(String[] args) {
        int n = 1001;
        int[][] matrix = new int[n][];
        for(int i = 0; i < matrix.length; i++) matrix[i] = new int[n];

        fillMatrix(matrix);
        System.out.println(diagonalSum(matrix));
    }

    static int diagonalSum(int[][] m) {
        int sum = 0;
        for(int i = 0; i < m.length; i++) {
            sum += m[i][i] + m[m.length - i - 1][i];
        }

        return sum - 1; // minus the center
    }

    static void fillMatrix(int[][] m) {
        int n = m.length; // expect n is odd
        int x = n / 2;
        int y = x;


        int direction = 0;

        for(int i = 1; i <= n * n; i++) {
            m[x][y] = i;

            if(i == n*n) break;

            int[] nxy = nextXY(direction, x, y);

            if(m[nxy[0]][nxy[1]] == 0) {
                direction = (direction + 1) % 4; // forward
            }
            else {
                nxy = nextXY((direction + 3) % 4, x, y); // previous direction
            }

            x = nxy[0];
            y = nxy[1];
        }
    }


    static int[] nextXY(int direction, int x, int y) {
        int nX = x;
        int nY = y;

        switch(direction) {
            case 0: // right
                nY++;
                break;
            case 1: // down
                nX++;
                break;

            case 2: // left
                nY--;
                break;
            case 3: // up
                nX--;
        }

        return new int[]{nX, nY};

    }



}
