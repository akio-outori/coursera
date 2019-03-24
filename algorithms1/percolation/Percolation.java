import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    public int size;
    private int[][] matrix;
    private WeightedQuickUnionUF find;

    // Create a data type that instantiates an N*N matrix of size^2
    public Percolation(int matrixSize) {
        size   = matrixSize;
        matrix = new int[size][size];
        find   = new WeightedQuickUnionUF((size * size) + 2);
    }

    // Show the current state of the system as an N*N square
    private void Visualize(int[][] matrix) {
        for ( int i = 0; i < size; i++) {
            for ( int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
        System.out.println();
        }
    }

    // Move code from the main method here to run percolation simulation
    public void simulate() {
        while (!percolates()) {
            int row    = StdRandom.uniform(size);
            int column = StdRandom.uniform(size);
            try {
                if (isFull(row, column) == true) {
                    open(row, column);
                } else {
                    continue;
                }
            } catch (IndexOutOfBoundsException error) {
                System.out.println("Parameter out of bounds:" + " row: " + row + " column: " + column);
                System.out.println(error);
                System.exit(1);
            }
        }
    }

    // Check whether the system percolates
    public boolean percolates() {
        return find.connected(0, (size*size) - 1 );
    }

    // Count the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for ( int row = 0; row < size; row++ ) {
            for ( int column = 0; column < size; column++ ) {
                if (isOpen(row, column)) {
                    count++;
                }
            }
        }
        return count;
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

    // Convert from the matrix notation to the union find notation for a square
    public int convert(int row, int column) {
        return (column * size) + row + 1;
    }

    // Set the value of a given square to 1, e.g. open it
    public void open(int row, int column) throws IndexOutOfBoundsException {

        // Update matrix value
        matrix[row][column] = 1;

        // Connect all first and last row squares
        if ( row == 0 ) {
            find.union(0, convert(row, column));
        } else if ( row == size - 1) {
            find.union(((size*size) - 1), convert(row, column));
        }

        // Connect Down
        if ( row - 1 >= 0 ) {
            if (isOpen(row - 1, column)) {
                find.union(convert(row - 1, column), convert(row, column));
            }
        }

        // Connect Up
        if ( row + 1 <= size - 1 ) {
            if (isOpen(row + 1, column)) {
                find.union(convert(row + 1, column), convert(row, column));
            }
        }

        // Connect Left
        if ( column - 1 >= 0 ) {
            if (isOpen(row, column - 1)) {
                find.union(convert(row, column - 1), convert(row, column));
            }
        }

        // Connect Right
        if ( column + 1 <= size - 1 ) {
            if (isOpen(row, column + 1)) {
                find.union(convert(row, column + 1), convert(row, column));
            }
        }

    }

    // Main method
    public static void main(String args[]) {

        // Set the size of the matrix
        int matrixSize = Integer.parseInt(args[0]);

        // Create an initial matrix
        Percolation system = new Percolation(matrixSize);

        // Run the percolation simulation
        system.simulate();

        // View the state of the system if asked
        system.Visualize(system.matrix);

        // return the number of squares required to percolate
        System.out.println(system.numberOfOpenSites());
    }

}
