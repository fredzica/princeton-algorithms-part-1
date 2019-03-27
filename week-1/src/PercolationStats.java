import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.valueOf(args[0]);
        int trials = Integer.valueOf(args[1]);

        PercolationStats pStats = new PercolationStats(n, trials);
        System.out.println("mean = " + pStats.mean());
        System.out.println("stddev = " + pStats.stddev());
        System.out.println(String.format("95 confidence interval = %f, %f]", pStats.confidenceLo(), pStats.confidenceHi()));
    }

    // perform trialsResults independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Invalid arguments for PercolationStats constructor");

        double[] trialsResults = new double[trials];

        // doing trials and storing results
        for (int i = 0;i < trials; i++) {
            Percolation p = new Percolation(n);
            boolean percolates = false;
            while (!percolates) {
                p.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
                percolates = p.percolates();
            }
            trialsResults[i] = p.numberOfOpenSites() / (double)(n * n);
        }

        // filling attribute values
        this.mean = StdStats.mean(trialsResults);
        this.stddev = StdStats.stddev(trialsResults);

        double confidencePart = 1.96 * this.stddev/Math.sqrt(n);
        this.confidenceLo = this.mean - confidencePart;
        this.confidenceHi = this.mean + confidencePart;
    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.confidenceHi;
    }
}
