// edit string 
// given two strings we have to find minimum no of operations required to convert str1 to str2 
// operations-> delete , insert , replace 

import java.util.*;

public class editDistance {
    public static void main(String args[]){
        String str1 = new String("intention");
        String str2= new String("execution");
        int n = str1.length();
        int m = str2.length();

        System.out.println("min operations required to convert str1 to sstr2 is "+ minOperations(str1,str2,n,m));
    }

    public static int minOperations(String str1,String str2 ,int n ,int m){
        int [][] dp = new int[n+1][m+1];
        //bc-initialization
        for(int i=0;i<dp.length;i++){
            //str1(n)-> str2(0)  == n operatios
            dp[i][0]=i;
        }
        for(int j=0 ;j<dp[0].length;j++){
            // str1(0) -> str2(m) == m operations
            dp[0][j] =j;
        }

        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    int insert = dp[i][j-1]+1;
                    int delete = dp[i-1][j]+1;
                    int replace = dp[i-1][j-1]+1;

                    dp[i][j] =Math.min(replace,Math.min(insert,delete));
                }
            }
        }
        return dp[n][m];
    }
}
