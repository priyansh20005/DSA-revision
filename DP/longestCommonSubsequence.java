// longest common subsequence of two string 
// recursion
// memoization 
// dp

import java.util.*;

public class longestCommonSubsequence {
    
    public static void main(String args[]){
        String str1 = "abcdef";
        String str2 ="ace";
        int n = str1.length();
        int m = str2.length();

        System.out.println(LCS(str1,str2,n ,m ));

    }

    public static int LCS(String str1,String str2 , int n , int m){
        if(n==0||m==0)
            return 0;
        if(str1.charAt(n-1)==str2.charAt(m-1))
            return LCS(str1,str2 , n-1,m-1)+1;
        else{
            int ans1 = LCS(str1,str2 , n , m-1);
            int ans2 = LCS(str1,str2 , n-1,m);
            return Math.max(ans1,ans2);
        }
    }

    /////////////////// memoization o(nm) both sc and TC------------
    public int longestCommonSubsequence(String text1, String text2) {

        int [][] dp = new int[text1.length()+1][text2.length()+1];
        for(int i=0;i<dp.length;i++){
            Arrays.fill(dp[i],-1);
        }

        return LCS_util(text1,text2 , text1.length(),text2.length(),dp);

    }
    public static int LCS_util(String str1,String str2 , int n , int m , int[][]dp){
        if(n==0||m==0)
            return 0;
        if(dp[n][m]!= -1)
            return dp[n][m];
        else{
            if(str1.charAt(n-1)==str2.charAt(m-1))
                return dp[n][m]= LCS_util(str1,str2 , n-1,m-1,dp)+1;
            else{
                int ans1 = LCS_util(str1,str2 , n , m-1,dp);
                int ans2 = LCS_util(str1,str2 , n-1,m ,dp);
                return dp[n][m]=Math.max(ans1,ans2);
            }
        }
        
    }


    //-----------------------TABLUATION--------------
    
    public int LCS_tablu(String text1, String text2) {
       

        int [][] dp = new int[text1.length()+1][text2.length()+1];
        Arrays.fill(dp[0],0); // when n=0
        for(int i=0;i<dp.length;i++){//when m=0
            dp[i][0] = 0;
        }

        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(text1.charAt(i-1)==text2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] +1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        return dp[text1.length()][text2.length()];

    }

}
