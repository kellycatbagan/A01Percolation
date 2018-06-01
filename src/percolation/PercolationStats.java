package percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	int[] openNodes;
	double[] normalizedNodes;
	int T, N;
	int gridSize;

	public PercolationStats(int N, int T) {
		if(N <= 0 || T <= 0) {
			throw new IllegalArgumentException("Invalid value has been entered for either grid size or times run.");
		}
		//Percolation instance
		Percolation p;
		//keeping track of openNodes for each percolation
		openNodes = new int[T];
		normalizedNodes = new double[T];
		//Size and Times Run variables
		this.T = T;
		this.N = N;
		this.gridSize = N*N;

		//run T times
		for (int k = 0; k < T; k++) {
			openNodes[k] = 0;
			p  = new Percolation(N);
			while (!p.percolates()) {
				int i = StdRandom.uniform(N);
				int j = StdRandom.uniform(N);
				if (!p.isOpen(i, j)) {
					p.open(i, j);
					openNodes[k]++;
				}
			}
		}
		
		//normalize results
		for (int l = 0; l < T; l++)
			normalizedNodes[l] = (double) openNodes[l] / gridSize;
	}

	public double mean() {
		return (StdStats.mean(normalizedNodes));
	}

	public double stddev() {
		return (StdStats.stddev(normalizedNodes));
	}

	public double confidenceLow() {
		return (mean() - 1.96 * stddev() / Math.sqrt(T));
	}

	public double confidenceHigh() {
		return (mean() + 1.96 * stddev() / Math.sqrt(T));
	}

}
