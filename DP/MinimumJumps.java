// given array of maximum jump size at given stage 
// we have to reach 0  to n-1(last) in minimum jumps


import java.util.*;

public class MinimumJumps {
    public static void main(String[] args){
        int jump[]={2,3,1,1,4};
        System.out.println(minJump(jump));
    }

    public static int minJump(int[] jump){
        int n=jump.length;
        int dp[] = new int[n];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[n-1]=0;
        for(int i=n-2;i>=0;i--){
            int minimum= Integer.MAX_VALUE;
            for(int j=1;  ((i+j)<n) && j<=jump[i] ;j++){
                     minimum=Math.min(dp[i+j] , minimum);
            }
            dp[i]=(minimum==Integer.MAX_VALUE) ? Integer.MAX_VALUE: minimum+1;
        }
        return dp[0];
    }
}
