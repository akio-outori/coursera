import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    final private int size;
    final private int experiments;
    final private double[] results;

    public PercolationStats(int matrixSize, int numExperiments) {

        size        = matrixSize;
        experiments = numExperiments;
        results     = new double[experiments];

        // Perform the specified number of experiments
        for (int iteration = 0; iteration < experiments; iteration++) {
            Percolation system = new Percolation(size);

            while (!system.percolates()) {
                int row    = (StdRandom.uniform(size) + 1);
                int column = (StdRandom.uniform(size) + 1);
                if (!system.isOpen(row, column)) {
                    system.open(row, column);
                } else {
                    continue;
                }
            }
            results[iteration] = Double.valueOf(system.numberOfOpenSites()) / Double.valueOf((size*size));
        }
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
        PercolationStats ps = new PercolationStats(parseArgs(args[0]), parseArgs(args[1]));
        String confidence95 = ps.confidenceLo() + ", " + ps.confidenceHi();

        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = " + confidence95);
    }

    // Take the size of the matrix from command line args
    private static int parseArgs(String arg) {
        return Integer.parseInt(arg);
    }

}
