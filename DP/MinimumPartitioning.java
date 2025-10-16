// given array , part it in two sets such that the sum of | set1(elements) - set2(elements) | is minimum
// it is kind of 0-1 kanpsack

import java.util.*;

public class MinimumPartitioning {
    public static void main(String args[]){
        int nums[]={1,5,11,6};
        System.out.println("Minimum diff : "+minDiff(nums));
    }

    public static int minDiff(int[]nums){
        int sum=0;
        for(int n :nums)
            sum+=n;

        int sum1 = knapsack(nums, sum/2);
        int sum2 = sum-sum1;

        int minimumDiff= Math.abs(sum1-sum2);
        return minimumDiff;
    }
    public static int knapsack(int[]nums, int Capacity){
        int n=nums.length;
        int dp[][]=new int[n+1][Capacity+1] ;

        for(int j=0;j<dp[0].length;j++)
            dp[0][j]=0;  //items are 0 
        
        for(int i=0;i<dp.length;i++){
            dp[i][0]=0;  // capacity is 0
        }

        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(nums[i-1] <=j){
                    dp[i][j] = Math.max(nums[i-1]+dp[i-1][j-nums[i-1]] , dp[i-1][j]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][Capacity];
    }
}
