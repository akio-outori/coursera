import java.util.Arrays;

public class Percolation {

    public static void main(String args[]) {
        // Create an initial matrix
        int[][] matrix = new int[20][20];

        // print the matrix
        // System.out.println(Arrays.toString(a));

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
