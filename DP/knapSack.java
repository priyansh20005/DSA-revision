// 0-1 knapsack ---
// 1> simple recursion 
// 2>  memoization (advanced recusrion)
// 3> tabluation

import java.util.*;


public class knapSack {



    public static void main(String args[]){

        int[] val= {15,14,10,45,30};
        int  wt[]={2,5,1,3,4};
        int W=7;

        System.out.println(maxProfit_memo(val, wt, W, 5));

    }


    // simple recursion - o(2^n)
    public static int maxProfit(int[] val , int[] wt , int remainCapacity ,int idx ){
        if(remainCapacity==0|| idx ==wt.length)
            return 0;  //base case

        if(remainCapacity >= wt[idx]){  //can include
            //choices 
            //included
            int inclu=  val[idx]+ maxProfit(val,wt, remainCapacity-wt[idx] , idx+1);

            //not included
            int exclu= maxProfit(val,wt, remainCapacity , idx+1);
            return Math.max(inclu, exclu);
        }
        // else  (remainCapacity< wt[idx])(
           return maxProfit(val,wt, remainCapacity , idx+1);
    }

    //memoization --------------------------------------------------------
    public static int maxProfit_memo(int[] val , int[] wt , int W , int n ){
        int dp[][] = new int[val.length+1][W+1];
       
        for (int i = 0; i <= val.length; i++) {
            for (int j = 0; j <= W; j++) {
                 dp[i][j] = -1;
            }
    }

        //helper function
       return  maxProfit_memo_util(val, wt , W ,n , dp);
    }

    public static int maxProfit_memo_util(int[]val , int[] wt , int W , int n , int[][]dp){

        if(W==0||n==0)
            return dp[n][W]= 0;
    
        if(dp[n][W]!= -1){
            return dp[n][W];
        }
        if(wt[n-1] <=W){
            int ans1 = val[n-1]+ maxProfit_memo_util(val,wt, W-wt[n-1] , n-1 , dp);
            int ans2 = maxProfit_memo_util(val,wt, W , n-1 , dp);
            return dp[n][W]= Math.max(ans1,ans2);
        }
        else{
            return dp[n][W]= maxProfit_memo_util(val,wt, W , n-1 , dp);
        }
    }

    //----------------------------------------------------------------------------------
    //----------------------TABULATION---------------------------
    public static int maxProfit_tab(int[]val , int[] wt , int W , int n){
        int [][]dp = new int[n+1][W+1];
        for(int i=0 ;i<dp.length ;i++){ // 0 col
            dp[i][0]=0;
        }
        for(int j=0;j<dp[0].length ;j++){  // 0th row
            dp[0][j] =0;
        }

        for(int i= 1 ; i<n+1 ; i++){
            for(int j=1;j<W+1 ;j++){
                 int v = val[i-1];  // ith item val 
                 int w = wt[i-1];   // ith item wt
                 if(w <= j){        // valid condition
                    int ans1 = v+ dp[i-1][j-w];
                    int ans2 = dp[i-1][j];
                    dp[i][j]= Math.max(ans1,ans2);
                 }
                 else{
                    dp[i][j] = dp[i-1][j];
                 }
            }
        }
        return dp[n][W];
    }

    ////////////////////////////////////////// unbounded knapsack-----------------------
    public static int maxProfit_tab_unbounded(int[]val , int[] wt , int W , int n){
        int [][]dp = new int[n+1][W+1];
        for(int i=0 ;i<dp.length ;i++){ // 0 col
            dp[i][0]=0;
        }
        for(int j=0;j<dp[0].length ;j++){  // 0th row
            dp[0][j] =0;
        }

        for(int i= 1 ; i<n+1 ; i++){
            for(int j=1;j<W+1 ;j++){
                 int v = val[i-1];  // ith item val 
                 int w = wt[i-1];   // ith item wt
                 if(w <= j){        // valid condition
                    int ans1 = v+ dp[i][j-w];   // only chnage search for the same items with remain capcity
                    int ans2 = dp[i-1][j];
                    dp[i][j]= Math.max(ans1,ans2);
                 }
                 else{
                    dp[i][j] = dp[i-1][j];
                 }
            }
        }
        return dp[n][W];
    }
}
