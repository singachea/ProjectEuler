//Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:
//
//        21 22 23 24 25
//        20  7  8  9 10
//        19  6  1  2 11
//        18  5  4  3 12
//        17 16 15 14 13
//
//        It can be verified that the sum of the numbers on the diagonals is 101.
//
//        What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?


public class Problem028 {
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
