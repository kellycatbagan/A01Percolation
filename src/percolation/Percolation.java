package percolation;

public class Percolation {

	boolean[][] grid;

	public Percolation(int n) {
		if (n < 0) {
			throw (new IllegalArgumentException("Cannot create grid of negative size."));
		}
		for (int p = 0; p < n; p++) {
			for (int s = 0; s < n; s++) {
				grid[p][s] = false;
			}
		}
	}

	public void open(int i, int j) {
		validate(i, j);
		grid[i][j] = true;
	}

	public boolean isOpen(int i, int j) {
		validate(i, j);
		return grid[i][j];
	}

	public boolean isFull(int i, int j) {
		validate(i, j);
		return !grid[i][j];
	}

	public boolean perlocates() {
		return false;
	}

	private void validate(int i, int j) {
		if (i <= grid.length || j <= grid.length || i < 0 || j < 0) {
			throw (new IndexOutOfBoundsException("One or more input do not exist on the current grid."));
		}
	}
}
