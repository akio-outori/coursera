import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    final private int size;
    final private WeightedQuickUnionUF find;
    private int[][] matrix;

    // Create a data type that instantiates an N*N matrix of size^2
    public Percolation(int matrixSize) {

        // Declare data structures
        size   = matrixSize;
        matrix = new int[size][size];
        find   = new WeightedQuickUnionUF((size * size) + 2);

    }

    // Check whether the system percolates
    public boolean percolates() {
        return find.connected(0, (size*size) - 1);
    }

    // Count the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int row = 1; row < size; row++) {
            for (int column = 1; column < size; column++) {
                if (isOpen(row, column)) {
                    count++;
                }
            }
        }
        return count;
    }

    // Check if a square is "Open", e.g. if it has been changed from 0 to 1
    public boolean isOpen(int row, int column) {

        if (row <= 0 || row > size || column <= 0 || column > size) {
            throw new IndexOutOfBoundsException("Illegal parameter value - Row: " + row + " Column: " + column);
        }

        if (matrix[row - 1][column - 1] == 1) {
            return true;
        } else {
            return false;
        }
    }

    // Check if a square is "Closed", e.g. if its value is 0
    public boolean isFull(int row, int column) {

        if (row <= 0 || row > size || column <= 0 || column > size) {
            throw new IndexOutOfBoundsException("Illegal parameter value - Row: " + row + " Column: " + column);
        }

        if (matrix[row - 1][column - 1] == 0) {
            return true;
        } else {
            return false;
        }
    }

    // Set the value of a given square to 1, e.g. open it
    public void open(int row, int column) {

        if (row <= 0 || row > size || column <= 0 || column > size) {
            throw new IndexOutOfBoundsException("Illegal parameter value - Row: " + row + " Column: " + column);
        }

        // Update matrix value
        matrix[row - 1][column - 1] = 1;

        // Connect all first and last row squares
        if (row == 1) {
            find.union(0, convert(row, column));
        } else if (row == size) {
            find.union(((size*size) - 1), convert(row, column));
        }

        // Connect Up
        if (row - 1 >= 1) {
            if (isOpen(row - 1, column)) {
                find.union(convert(row - 1, column), convert(row, column));
            }
        }

        // Connect Down
        if (row + 1 <= size) {
            if (isOpen(row + 1, column)) {
                find.union(convert(row + 1, column), convert(row, column));
            }
        }

        // Connect Left
        if (column - 1 >= 1) {
            if (isOpen(row, column - 1)) {
                find.union(convert(row, column - 1), convert(row, column));
            }
        }

        // Connect Right
        if (column + 1 <= size) {
            if (isOpen(row, column + 1)) {
                find.union(convert(row, column + 1), convert(row, column));
            }
        }

    }

    // Main method
    public static void main(String args[]) {

        // Set the size of the matrix
        int matrixSize = parseArgs(args[0]);

        // Create an initial matrix
        Percolation system = new Percolation(matrixSize);

        // Run simulation
        system.simulate();

        // View the state of the system if asked
        system.visualize(system.matrix);

        // return the number of squares required to percolate
        System.out.println(system.numberOfOpenSites());
    }

    // Show the current state of the system as an N*N square
    private void visualize(int[][] matrix) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
        System.out.println();
        }
    }

    // Move code from the main method here to run percolation simulation
    private void simulate() {
        while (!percolates()) {
            int row    = (StdRandom.uniform(size) + 1);
            int column = (StdRandom.uniform(size) + 1);
            if (isFull(row, column) == true) {
                open(row, column);
            } else {
                continue;
            }
        }
    }

    // Convert from the matrix notation to the union find notation for a square
    private int convert(int row, int column) {
        return ((column - 1) * size) + (row - 1);
    }

    // Take the size of the matrix from command line args
    private static int parseArgs(String arg) {
        return Integer.parseInt(arg);
    }

}
