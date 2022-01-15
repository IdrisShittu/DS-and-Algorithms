class MCMNaive {

    
	static int MatrixChainOrder(int p[], int i, int j){
		if (i == j)return 0;
		int min = Integer.MAX_VALUE;
		for (int k = i; k < j; k++) {
			int count = MatrixChainOrder(p, i, k) +
						MatrixChainOrder(p, k + 1, j) +
						p[i - 1] * p[k] * p[j];
			if (count < min)min = count;
		}
		return min;
	}

	public static void main(String args[]){
		int a[] = new int[] { 10, 15, 20, 15, 30 };
		int n = a.length;

        long startTime = System.nanoTime();
		System.out.println("Naive: Minimum Number of multiplications: "
							+ MatrixChainOrder(a, 1, n - 1) + "Time Taken: "+ (System.nanoTime() - startTime));
	}
}

