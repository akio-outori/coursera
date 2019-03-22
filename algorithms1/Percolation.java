import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][] matrix;
    private WeightedQuickUnionUF find;
    private int size;

    public Percolation(int size) {
        matrix = new int[size][size];
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

    private void Visualize(int[][] matrix) {
        for ( int i = 0; i < matrix.length; i++) {
            for ( int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
        System.out.println();
        }
    }

    public void open(int row, int column) throws IndexOutOfBoundsException {
        matrix[row][column] = 1;
    }

    public static void main(String args[]) {

        // Create an initial matrix
        int size = ParseArgs(args[0]);
        Percolation system = new Percolation(size);

        // Test open
        int row    = StdRandom.uniform(size) + 1;
        int column = StdRandom.uniform(size) + 1;
        try {
            system.open(row, column);
        } catch (IndexOutOfBoundsException error) {
            System.out.println("Parameter out of bounds:" + " row: " + row + " column: " + column);
            System.exit(1);
        }

        // View the state of the system
        system.Visualize(system.matrix);

    }

}
