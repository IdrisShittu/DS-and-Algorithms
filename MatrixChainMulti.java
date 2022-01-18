import java.io.*;
import java.util.*;

class MatrixChainMulti{

    public static void main(String args[]){

        int array[];
        long startTime;

        for(int i=3; i<=50; i++){
            array = new int[i];
            for(int j=0; j<i; j++) array[j]=(int)Math.round(Math.random() * 46 + 5);
            System.out.println("n = " + i + " Input p[] = "+ Arrays.toString(array));
		
         
//------Memoisation Method-------------------------------------------------------------------------------------------------
            for (int[] row : dp)  Arrays.fill(row, -1); 
            startTime = System.nanoTime();
            System.out.println("Memoization: Output= " 
            + MatrixChainOrderMemoised(array, i) 
            + " Time Taken= "+ (System.nanoTime() - startTime));


//------Tabulation Method---------------------------------------------------------------------------------------------------
            System.out.println("Tabulation: Output= "+
            + MatrixChainOrderTabulated(array, i)  
            + " Time Taken= "+ (System.nanoTime() - startTime) );


//------Naive Recursive-----------------------------------------------------------------------------------------------------
            startTime = System.nanoTime();
		    System.out.println("Naive: Ouput= "
		    + MatrixChainOrderNaive(array, 1, i - 1) 
            + " Time Taken= "+ (System.nanoTime() - startTime) + "\n\n");
        }//end for Loop
	}//end Main




//==========================================================================================================================




//--Naive Recursive Implementation------------------------------------------------------------------------------------------
	static int MatrixChainOrderNaive(int p[], int i, int j){
		if (i == j)return 0;
		int min = Integer.MAX_VALUE;
		for (int k = i; k < j; k++) {
			int count = MatrixChainOrderNaive(p, i, k) +
						MatrixChainOrderNaive(p, k + 1, j) +
						p[i - 1] * p[k] * p[j];
			if (count < min)min = count;
		}
		return min;
	}




//--Memoization Method Implementation--------------------------------------------------------------------------------------
    static int[][] dp = new int[100][100];
    static int matrixChainMemoised(int[] p, int i, int j) { 
        if (i == j) return 0; 
        if (dp[i][j] != -1)  return dp[i][j]; 
        dp[i][j] = Integer.MAX_VALUE; 
        for (int k = i; k < j; k++)  { 
            dp[i][j] = Math.min( dp[i][j], matrixChainMemoised(p, i, k) 
            + matrixChainMemoised(p, k + 1, j) 
            + p[i - 1] * p[k] * p[j]); 
        } 
    return dp[i][j]; 
    } 

    static int MatrixChainOrderMemoised(int[] p, int n) { 
        int i = 1, j = n - 1; 
        return matrixChainMemoised(p, i, j); 
    } 




//--Tabulated--------------------------------------------------------------------------------------------------------------
    static int MatrixChainOrderTabulated(int p[], int n){
        int m[][] = new int[n][n];
        int i, j, k, L, q;
        for (i = 1; i < n; i++) m[i][i] = 0;
        for (L = 2; L < n; L++){
            for (i = 1; i < n - L + 1; i++) {
                j = i + L - 1;
                if (j == n)continue;
                m[i][j] = Integer.MAX_VALUE;
                for (k = i; k <= j - 1; k++){
                    q = m[i][k] + m[k + 1][j]
                    + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j])m[i][j] = q;
                }
            }
        }
        return m[1][n - 1];
    }
}

