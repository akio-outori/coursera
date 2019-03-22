import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    public static int[][] Percolation(int n) {
        int[][] matrix = new int[n][n];
        return matrix;
    }

    private static int ParseArgs(String arg) {
        try {
            int num = Integer.parseInt(arg);
            return num;
        } catch (NumberFormatException nfe) {
            System.out.println("The first argument must be an integer.");
            System.exit(1);
        }
        return 0;
    }

    public static void main(String args[]) {
        // Create an initial matrix
        int size = ParseArgs(args[0]);
        int[][] matrix = Percolation.Percolation(size);

        // print the matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
