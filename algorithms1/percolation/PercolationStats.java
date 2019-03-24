import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {

    private static int size;
    private Percolation experiment;
    private static int experiments;
    private static double[] results;

    public PercolationStats(int size, int experiments) throws IllegalArgumentException {

        results = new double[experiments];

        // Perform the specified number of experiments
        for (int iteration = 0; iteration < experiments; iteration++) {
            Percolation system = new Percolation(size);
            system.simulate();
            results[iteration] = Double.valueOf(system.numberOfOpenSites()) / Double.valueOf((size*size));
        }
    }

    // Take the size of the matrix from command line args
    private static int ParseArgs(String arg) throws NumberFormatException {
        return Integer.parseInt(arg);
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
        size        = ParseArgs(args[0]);
        experiments = ParseArgs(args[1]);

        PercolationStats ps = new PercolationStats(size, experiments);
        String confidence95 = ps.confidenceLo() + ", " + ps.confidenceHi();

        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = " + confidence95);
    }

}
