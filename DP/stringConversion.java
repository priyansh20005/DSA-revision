// given str1 and str2 convert s1 to s2 by only delte and add opeation
// return min no delte and add operations

// 1> find lcs of both 
// 2> remove all nonLCS from str1 = delte op
// 3> add all nonLCS from str2 = add opeartions

import java.util.*;

public class stringConversion {
    public static void main(String args[]){
        String str1="abcdef";
        String str2 = new String("aceg");
        int n=str1.length();
        int m = str2.length();

        System.out.println(minOp(str1,str2,n,m));
    }

    //// actual function
    public static int minOp(String str1,String str2 , int n , int m){
        int lcs= LCS(str1,str2 ,n ,m);
        int delete_op = n-lcs;
        int add_op = m-lcs;

        System.out.println("the total operation required are : delte-> "+ delete_op+" add->"+add_op);

        return delete_op+add_op;
    }
//// already done lcs function
    public static int LCS(String str1,String str2, int n ,int m){
        int [][]dp = new int[n+1][m+1];
        Arrays.fill(dp[0],0);
        for(int i=0;i<dp.length;i++){
            dp[i][0]=0;
        }

        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j]= dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        return dp[n][m];
    }
}
