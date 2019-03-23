import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][] matrix;
    private WeightedQuickUnionUF find;
    private int size;

    // Create a data type that instantiates an N*N matrix of size^2
    public Percolation(int size) {
        matrix = new int[size][size];
        find   = new WeightedQuickUnionUF((size * size) + 2);
    }

    // Take the size of the matrix from command line args
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

    // Show the current state of the system as an N*N square
    private void Visualize(int[][] matrix) {
        for ( int i = 0; i < matrix.length; i++) {
            for ( int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
        System.out.println();
        }
    }

    // Check if a square is "Open", e.g. if it has been changed from 0 to 1
    public boolean isOpen(int row, int column) throws IndexOutOfBoundsException {
        if (matrix[row][column] == 1) {
            return true;
        } else {
            return false;
        }
    }

    // Check if a square is "Closed", e.g. if its value is 0
    public boolean isFull(int row, int column) throws IndexOutOfBoundsException {
        if (matrix[row][column] == 0) {
            return true;
        } else {
            return false;
        }
    }

    // Set the value of a given square to 1, e.g. open it
    public void open(int row, int column) throws IndexOutOfBoundsException {
        matrix[row][column] = 1;
    }

    // Main method
    public static void main(String args[]) {

        // Create an initial matrix
        int size = ParseArgs(args[0]);
        Percolation system = new Percolation(size);

        // Test open
        int i = 0;
        while (i < 100) {
            int row    = StdRandom.uniform(size);
            int column = StdRandom.uniform(size);
            try {
                if (system.isFull(row, column) == true) {
                    system.open(row, column);
                } else {
                    continue;
                }
            } catch (IndexOutOfBoundsException error) {
                System.out.println("Parameter out of bounds:" + " row: " + row + " column: " + column);
                System.out.println(error);
                System.exit(1);
            }
            i++;
        }

        // View the state of the system
        system.Visualize(system.matrix);

    }

}
