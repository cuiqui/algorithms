import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int size;
    private int virtualTopSite;
    private int virtualBottomSite;
    private int numberOfOpenSites;
    private WeightedQuickUnionUF qf;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        size = n;
        qf = new WeightedQuickUnionUF(size * size + 2);
        grid = new boolean[size][size];
        virtualTopSite = size * size;
        virtualBottomSite = size * size + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
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
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // check if tile in (row, col) is connected to
        // top row
        int tileIdx = getTileIdx(row, col);
        if (tileIdx < 0 || tileIdx > size * size - 1) {
            throw new IndexOutOfBoundsException();
        }

        return qf.connected(tileIdx, virtualTopSite);
    }

    private int getTileIdx(int row, int col) {
        return size * (row - 1) + col - 1;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return qf.connected(virtualBottomSite, virtualTopSite);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
