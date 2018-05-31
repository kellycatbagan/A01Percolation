package percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	int[] openNodes;
	int T;
	
	public PercolationStats(int N, int T) {
		Percolation p = new Percolation(N);
		openNodes = new int[T];
		this.T = T;
			
		for (int k=0; k < T; k++) {
			openNodes[k] = 1;
			while(p.percolates() == false) {
				int i = StdRandom.uniform(N);
				int j = StdRandom.uniform(N);
				if (p.isOpen(i, j) == false) {
					p.open(i, j);
					openNodes[k]++;
				}				
			}			
		}
	}
	
	public double mean() {
		return(StdStats.mean(openNodes));//		
	}
	
	public double stddev() {
		return(StdStats.stddev(openNodes));				
	}
	
	public double confidenceLow() {
		return(mean() - 1.96*stddev()/Math.sqrt(T));
	}
	public double confidenceHigh() {
		return(mean() + 1.96*stddev()/Math.sqrt(T));
	}
	
}
