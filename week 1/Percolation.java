import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int numberOfOpenSites;
    private final int size;
    private final int virtualTopSite;
    private final int virtualBottomSite;
    private final WeightedQuickUnionUF qf;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        qf = new WeightedQuickUnionUF(size * size + 2);
        grid = new boolean[size][size];
        virtualTopSite = size * size;
        virtualBottomSite = size * size + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        // FIXME: still have backwash percolation bug
        if (!withinBoundaries(row, col)) {
            throw new IllegalArgumentException();
        }
        // open
        if (!grid[row - 1][col - 1]) {
            grid[row - 1][col - 1] = true;
            numberOfOpenSites++;
        }

        // connect
        int tileIdx = getTileIdx(row, col);

        // connect to virtual top or bottom sites
        if (row == 1) {
            qf.union(tileIdx, virtualTopSite);
        }
        if (row == size) {
            qf.union(tileIdx, virtualBottomSite);
        }
        // connect other ends
        if (row - 1 > 0 && isOpen(row - 1, col)) {
            qf.union(tileIdx, getTileIdx(row - 1, col));
        }
        if (row + 1 <= size && isOpen(row + 1, col)) {
            qf.union(tileIdx, getTileIdx(row + 1, col));
        }
        if (col - 1 > 0 && isOpen(row, col - 1)) {
            qf.union(tileIdx, getTileIdx(row, col - 1));
        }
        if (col + 1 <= size && isOpen(row, col + 1)) {
            qf.union(tileIdx, getTileIdx(row, col + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!withinBoundaries(row, col)) {
            throw new IllegalArgumentException();
        }
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // check if tile in (row, col) is connected to
        // top row
        int tileIdx = getTileIdx(row, col);
        if (!withinBoundaries(row, col)) {
            throw new IllegalArgumentException();
        }

        return qf.connected(tileIdx, virtualTopSite);
    }

    private int getTileIdx(int row, int col) {
        return size * (row - 1) + col - 1;
    }

    private boolean withinBoundaries(int row, int col) {
        return (0 < row && row <= size) && (0 < col && col <= size);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return qf.connected(virtualBottomSite, virtualTopSite);
    }
}
