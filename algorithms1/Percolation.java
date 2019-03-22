import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] matrix;
    private WeightedQuickUnionUF find;
    private int size;

    public Percolation(int size) {
        matrix = new boolean[size][size];
        find   = new WeightedQuickUnionUF((size * size) + 2);
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

    private static boolean[][] Visualize(boolean[][] matrix) {
        for ( int i = 0; i < matrix.length; i++) {
            for ( int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
        System.out.println();
        }
        return matrix;
    }

    public static void main(String args[]) {

        // Create an initial matrix
        int size = ParseArgs(args[0]);
        Percolation system = new Percolation(size);
        Visualize(system.matrix);

        // print the matrix
        //for (int i = 0; i < matrix.length; i++) {
        //    for (int j = 0; j < matrix[i].length; j++) {
        //        System.out.print(matrix[i][j] + " ");
        //    }
        //    System.out.println();
        //}
    }

}
