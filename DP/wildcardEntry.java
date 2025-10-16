// Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

// '?' Matches any single character.
// '*' Matches any sequence of characters (including the empty sequence).
// The matching should cover the entire input string (not partial).

 

// Example 1:

// Input: s = "aa", p = "a"
// Output: false
// Explanation: "a" does not match the entire string "aa".

import java.util.*;


public class wildcardEntry {
    public static void main(String args[]){
        System.out.println(" "+isMatch("abcde","ab*ef"));
    }

    public static boolean isMatch(String s, String p) {
        int n=s.length();  // i ->row
        int m=p.length();   //j-> column 

        boolean [][]dp=new boolean[n+1][m+1];
        dp[0][0]= true;
        //for i>0 and j=0 -> false
        for(int i=1;i<dp.length;i++){
            dp[i][0]=false;
        }
        //for i=0 and j>0 -> depend on pattern
        for(int j=1;j<dp[0].length;j++){
            if(p.charAt(j-1)=='*'){
                dp[0][j]= dp[0][j-1] ;
            }else
                dp[0][j] =false;
        }

        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
              if(s.charAt(i-1)==p.charAt(j-1) ||p.charAt(j-1)=='?' ){
                dp[i][j] = dp[i-1][j-1];
              }
              else if(p.charAt(j-1)=='*'){
                dp[i][j]= dp[i][j-1]||dp[i-1][j];
              }
              else
                dp[i][j]=false;
            }
        }
    return dp[n][m];
    }
}
