// given target sum and set of no , find if any subset sum is equal to tareget sum

import java.util.*;

public class targetSum {
    
    public static void main(String args[]){
        int[]  numbers = {4,2,7,1,3};
        int targetSu = 10;

        System.out.println(isTargetSum(numbers , targetSu));
    }

    public static boolean isTargetSum(int[]num , int target){
        boolean[][] dp = new boolean[num.length+1][target+1];
        //base-case
        // when target =0 true
        for(int i =0; i< dp.length ;i++)
            dp[i][0]=true;
        //when item =0 false
        for(int j =1 ; j<dp[0].length;j++)
            dp[0][j]=false;
        
        for(int i=1 ;i<dp.length;i++){   // items 
            for(int j=1 ; j<dp[0].length;j++){  // target
                int wt = num[i-1];
                if(wt <=j){  // valid 
                    // boolean ans1 = dp[i-1][j-wt];
                    // boolean ans2 = dp[i-1][j];
                    // dp[i][j]  = ans1 || ans2;
                    dp[i][j] = dp[i-1][j-wt] || dp[i-1][j];
                }else{  // not valid , cant include this item 
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        printTable(dp);
        return dp[num.length][target];
    }

    /// function o=to print dp table
    public static void printTable(boolean[][] dp){
        for(int i=0 ;i <dp.length;i++){
            for(int j=0 ; j<dp[0].length;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
    }
}
