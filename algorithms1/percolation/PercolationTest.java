import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationTest {

    final private int size;
    final private int experiments;
    final private double[] results;

    public PercolationTest(int matrixSize, int numExperiments) {

        size        = matrixSize;
        experiments = numExperiments;
        results     = new double[experiments];

        Percolation system = new Percolation(size);
        System.out.println(system.isOpen(-1,-1));

    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(experiments));
    }

    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(experiments));
    }

    public static void main(String[] args) {

        // Get the size of the matrix and the number of runs to perform
        PercolationTest ps = new PercolationTest(parseArgs(args[0]), parseArgs(args[1]));
    }

    // Take the size of the matrix from command line args
    private static int parseArgs(String arg) {
        return Integer.parseInt(arg);
    }

}
