import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    public static int size;
    private int[][] matrix;
    private WeightedQuickUnionUF find;

    // Create a data type that instantiates an N*N matrix of size^2
    public Percolation(int size) {
        matrix = new int[size][size];
        find   = new WeightedQuickUnionUF((size * size));
    }

    // Take the size of the matrix from command line args
    private static void ParseArgs(String arg) throws NumberFormatException {
        size = Integer.parseInt(arg);
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

    // Convert from the matrix notation to the union find notation for a square
    public int convert(int row, int column) {
        //System.out.println("Row: " + row + " Size: " + size + " Column: " + column + " Result: " + ((row * size) + column));
        return (column * size) + row;
    }

    // Set the value of a given square to 1, e.g. open it
    public void open(int row, int column) throws IndexOutOfBoundsException {

        // Update matrix value
        matrix[row][column] = 1;

        System.out.println("Value: " + convert(row, column));

        // Left / Right
        if ( column == 0 ) {
            System.out.print("Right: ");
            System.out.println(convert((row + 1), column));
        } else if ( column == size ) {
            System.out.print("Left: ");
            System.out.println(convert((row - 1), column));
        } else if ( row - 1 >= 0 && row + 1 <= size ) {
            System.out.print("Left: ");
            System.out.println(convert((row - 1), column));
            System.out.print("Right: ");
            System.out.println(convert((row + 1), column));
        }

        // Up / Down
        if ( row == 0 ) {
            System.out.print("Down: ");
            System.out.println(convert(row, (column + 1)));
        } else if ( row == size ) {
            System.out.print("Up: ");
            System.out.println(convert(row, (column - 1)));
        } else if ( column - 1 >= 0 && column + 1 <= size ) {
            System.out.print("Up: ");
            System.out.println(convert(row, (column - 1)));
            System.out.print("Down: ");
            System.out.println(convert(row, (column + 1)));
        }

        find.union(0, convert(row, column));
    }

    // Main method
    public static void main(String args[]) {

        // Create an initial matrix
        ParseArgs(args[0]);
        Percolation system = new Percolation(size);

        System.out.println(size);
        // Test open
        int i = 0;
        while (i < 400) {
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

        // Test percolation
        System.out.println("Start: " + system.find.find(0));
        System.out.println("End: " + system.find.find((size*size) - 1));
        System.out.println(system.find.count());
        System.out.println(system.find.connected(0, (size*size) - 1));
    }

}
