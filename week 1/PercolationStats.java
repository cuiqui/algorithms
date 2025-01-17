import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final double[] thresholdByTrial;
    private final int numTrials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        numTrials = trials;
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        thresholdByTrial = new double[numTrials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int rndRow = StdRandom.uniform(1, n + 1);
                int rndCol = StdRandom.uniform(1, n + 1);
                p.open(rndRow, rndCol);
            }
            thresholdByTrial[i] = p.numberOfOpenSites() / (double) (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholdByTrial);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholdByTrial);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev()) / Math.sqrt(numTrials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev()) / Math.sqrt(numTrials);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);
        System.out.println("mean                     = " + ps.mean());
        System.out.println("stddev                   = " + ps.stddev());
        System.out.println(
                "95% confidence interval: = [" + ps.confidenceLo() + ", " + ps.confidenceHi()
                        + "]");
    }

}
