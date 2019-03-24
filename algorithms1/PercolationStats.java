import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {

    private int experiments;
    private double[] results;

    public PercolationStats(int n, int trials) {

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

    }

}
