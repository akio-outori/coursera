import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    public static int[][] Percolation(int n) {
        int[][] matrix = new int[n][n];
        return matrix;
    }

    public static void main(String args[]) {
        // Create an initial matrix
        int[][] matrix = Percolation.Percolation(20);

        // print the matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
