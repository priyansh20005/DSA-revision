// longest common substring
// varity of lcs


import java.util.*;

public class LongestCommonSubstring {
    public static void main(String args[]){

        String str1 = "abcde";
        String str2="abgce";
        int n = str1.length();
        int m  = str2.length();

        System.out.println(LCSubstring(str1,str2, n ,m ,0 ,0));
        System.out.println(LCS_tabu(str1,str2, n ,m));

    }

    public static int LCSubstring(String str1,String str2 , int n ,int m , int curr ,int highest){
        if(n==0|| m==0)
            return highest;
        
        if(str1.charAt(n-1)== str2.charAt(m-1)){
            // if(highest < curr+1)
            //     highest= curr+1;
            curr++;
            highest = Math.max(highest,curr);
            return LCSubstring(str1,str2 , n-1,m-1 , curr ,highest );
        }
            
        else{
            int ans1 = LCSubstring(str1, str2, n, m-1,0, highest);
            int ans2 =LCSubstring(str1, str2, n-1, m,0, highest);
            return Math.max(ans1,ans2);
            // return LCSubstring(str1, str2, n-1, m-1 , 0 , highest);
        }

    }

    public static int LCS_tabu(String str1,String str2 , int n ,int m){
        
        int [][] dp = new int[n+1][m+1];
        Arrays.fill(dp[0],0);
        for(int i=0;i<dp.length;i++)
            dp[i][0]=0;

        int highest =0;
        int temp=0;

        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = 0;
                }
                if(dp[i][j] >highest)
                        highest=dp[i][j];     
            }
        }
        return highest;
    }
}
