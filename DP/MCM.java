/// matrix chain multiplication
///find minimum no of operations requried for final matrix 
/// given arr of size n for (n-1) MAtrix
/// M[i]= arr[i-1]*arr[i];   i>=1 to n-1
/// 

import java.util.*;

public class MCM {
    public static void main(String args[]){
        int arr[]={1,2,3,4,5};
        int dp[][]= new int[arr.length][arr.length];
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                dp[i][j]=-1;
            }
        }
        System.out.println(minCost(arr,1,arr.length-1 ,dp));

        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println(minCost_tabu(arr));
    }

    // recursive+memoization function for min cost or operations
    public static int minCost(int[]arr,int st , int end , int[][]dp){

        //base condition
        if(st==end)
            return dp[st][end]= 0;
        else if(dp[st][end] != -1)
            return dp[st][end];

        int finalMinCost= Integer.MAX_VALUE;
        for(int k=st; k<=end-1;k++){
            // k is set dividing point  set1=(st - k)   set2=(k+1 - end)
            int cost1= minCost(arr,st ,k,dp);
            int cost2= minCost(arr,k+1, end , dp);
            int cost3= arr[st-1]*arr[k]*arr[end];

            int totalCost=cost1+cost2+cost3;

            finalMinCost = Math.min(finalMinCost ,totalCost);
        }
        return dp[st][end]= finalMinCost;
    }

    /// tabulation code for the same 
    public static int minCost_tabu(int[]arr){
        int st=1;
        int end = arr.length-1;

        int dp[][] = new int[end+1][end+1];
        for(int i=0;i<dp.length;i++)
            Arrays.fill(dp[i],-1);
        
        //initialization for base cases
        for(int start=1;start<=end;start++){
            //when start = end -> op=0
            dp[start][start]=0;
        }

        //solution
        for(int len=2;len<=end;len++){
            for(int i=1;i<=end-len+1 ;i++){
                int j= i+len-1;
                int finalMinCost = Integer.MAX_VALUE;
                for(int k=i;k<= j-1;k++){
                    int cost1= dp[i][k];
                    int cost2= dp[k+1][j];
                    int cost3 = arr[i-1]*arr[k]*arr[j];
                    int total=cost1+cost2+cost3;
                    finalMinCost=Math.min(total,finalMinCost);
                }
                dp[i][j]= finalMinCost;
            }
        }

        return dp[st][end];
    }
}
