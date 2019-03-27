import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // test client (optional)
    public static void main(String[] args) {

        Percolation p1 = new Percolation(100);
        p1.open(2, 1);
        p1.open(2, 2);
        p1.uf.connected(p1.xyTo1D(2, 1), p1.xyTo1D(2, 2));

        Percolation p2 = new Percolation(2);
        p2.open(1, 1);
        p2.open(1,2);
        p2.open(2,1);
        p2.open(2,2);
        p2.uf.count();
        p2.percolates();
    }

    private WeightedQuickUnionUF uf;
    private boolean[][] m;
    private int n;
    private int openSites;

    private int rowPercolationStart;
    private int colPercolationStart;
    private int rowPercolationEnd;
    private int colPercolationEnd;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n must be a positive integer");

        this.uf = new WeightedQuickUnionUF((n + 1) * (n + 1));
        this.n = n;
        this.m = new boolean[n + 1][n + 1];

        this.rowPercolationStart = 0;
        this.colPercolationStart = 0;
        this.rowPercolationEnd = 0;
        this.colPercolationEnd = 1;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        this.checkValidBounds(row, col);

        // if it's already open, do nothing
        if (m[row][col])
            return;

        // opening site and counting
        m[row][col] = true;
        openSites++;

        // connecting with neighboring sites, if they are open
        int rowAbove = row - 1;
        int rowBelow = row + 1;
        int colBefore = col - 1;
        int colAfter = col + 1;
        int oneDimension = this.xyTo1D(row, col);
        if (this.isValidBounds(rowAbove, col) && m[rowAbove][col])
            uf.union(this.xyTo1D(rowAbove, col), oneDimension);

        if (this.isValidBounds(rowBelow, col) && m[rowBelow][col])
            uf.union(this.xyTo1D(rowBelow, col), oneDimension);

        if (this.isValidBounds(row, colBefore) && m[row][colBefore])
            uf.union(this.xyTo1D(row, colBefore), oneDimension);

        if (this.isValidBounds(row, colAfter) && m[row][colAfter])
            uf.union(this.xyTo1D(row, colAfter), oneDimension);

        // if the opening point is in the first row, lets connect it to the percolation start
        if (row == 1)
            uf.union(this.xyTo1D(this.rowPercolationStart, this.colPercolationStart), oneDimension);

        // if the opening point is in the last row, lets connect it to the percolation end
        if (row == n) {
            uf.union(this.xyTo1D(this.rowPercolationEnd, this.colPercolationEnd), oneDimension);
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        this.checkValidBounds(row, col);

        return this.m[row][col];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        this.checkValidBounds(row, col);

        return uf.connected(this.xyTo1D(this.rowPercolationStart, this.colPercolationStart), this.xyTo1D(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return this.openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(this.xyTo1D(this.rowPercolationStart, this.colPercolationStart),
                this.xyTo1D(this.rowPercolationEnd, this.colPercolationEnd));
    }

    private int xyTo1D(int x, int y) {
        // if n is 20:
        // x = 0, y = 1, ret = 1
        // x = 1, y = 1, ret = 20
        // x = 1, y = 2, ret = 21
        // x = 2, y = 1, ret = 40
        // x = 2, y = 2, ret = 41
        // x = 3, y = 2, ret = 61

        return x * n + y;
    }

    private boolean isValidBounds(int row, int col) {
        return !(row <= 0 || row > n || col <= 0 || col > n);
    }

    private void checkValidBounds(int row, int col) {
        if (!this.isValidBounds(row, col))
            throw new IllegalArgumentException("row and column must be within valid range of 1 to n");
    }
}
