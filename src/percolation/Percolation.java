package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	boolean[][] perc;
	WeightedQuickUnionUF perc1D;
	int N;
	
	public Percolation(int N) {
		perc = new boolean[N][N];
		perc1D = new WeightedQuickUnionUF(N*N+2);	
		this.N = N;

		// connect the top row to "source" node 0
		for (int i = 1; i < N+1; i++) {
			perc1D.union(0,i);
		}
		// connect the bottom to "sink" node N*N+1
		for (int i = N*(N-1); i < N*N+1; i++) {
			perc1D.union(N*N+1, i);
		}
	}
	
	private boolean validateIndices(int i, int j) {
		int N = perc[0].length;
		if (i >= N ||  i < 0 || j >= N || j < 0) {
			throw new IndexOutOfBoundsException("Indices must be between 0 and " + (N-1));			
		}
		return true;
	}
	
	private int twoDToOneD(int i, int j) {
		return perc[0].length*i+j+1;
	}
	
	public boolean open (int i, int j) {
		// 1. Validate indices
		if (validateIndices(i,j) == false) {
			return false;
		}		
		// 2. Mark it open
		perc[i][j] = true;		 
		// 3. UF it
		// with node above
		if (i > 0 && perc[i-1][j] == true) {
			perc1D.union(twoDToOneD(i-1,j), twoDToOneD(i,j));
		}
		// with node to left
		if (j > 0 && perc[i][j-1] == true) {
			perc1D.union(twoDToOneD(i,j-1), twoDToOneD(i,j));
		}
		// with node to right
		if (j < perc[0].length-1 && perc[i][j+1] == true) {
			perc1D.union(twoDToOneD(i,j+1), twoDToOneD(i,j));
		}
		// with node below
		if (i < perc[0].length-1 && perc[i+1][j] == true) {
			perc1D.union(twoDToOneD(i+1,j), twoDToOneD(i,j));
		}
		return true;
	}
	
	public boolean isOpen(int i, int j) {
		if (validateIndices(i,j) == false) {
			return false;
		}
		return(perc[i][j]);
	}
	
	public boolean isFull(int i, int j) {
		if (validateIndices(i,j) == false || isOpen(i,j) == false) {
			return false;
		}
		return (perc1D.find(twoDToOneD(i,j)) == perc1D.find(0));	
	}
	
	public boolean percolates() {
		return (perc1D.connected(0,N*N+1));
	}

}
