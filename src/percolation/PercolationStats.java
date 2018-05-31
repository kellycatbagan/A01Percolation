package percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	int[] openNodes;
	int T, N;

	public PercolationStats(int N, int T) {
		if(N <= 0 || T <= 0) {
			throw new IllegalArgumentException("Invalid value has been entered for either grid size or times run.");
		}
		//Percolation instance
		Percolation p;
		//keeping track of openNodes for each percolation
		openNodes = new int[T];
		//Size and Times Run variables
		this.T = T;
		this.N = N;

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
	}

	public double mean() {
		return (StdStats.mean(openNodes) / (N * N));
	}

	public double stddev() {
		return (StdStats.stddev(openNodes));
	}

	public double confidenceLow() {
		return (mean() - 1.96 * stddev() / Math.sqrt(T));
	}

	public double confidenceHigh() {
		return (mean() + 1.96 * stddev() / Math.sqrt(T));
	}

}
