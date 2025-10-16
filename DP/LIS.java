// given one array of int 
// lis -> lowest increasing subsequence


import java.util.*;

public class LIS {
    public static void main(String args[]){
        int arr1[] ={50,3,10,7,40,80};

        System.out.println(LIS(arr1));
    }

    // normal brute force-o(n^2);
    public static int LIS(int[] arr){
        int[] dp = new int[arr.length];
        // dp[0]=1;
        Arrays.fill(dp,1);
        int high=1;

        for(int i=1;i<dp.length;i++){
            for(int j=0;j<i;j++){
                 if(arr[j] <arr[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                    // break;
                }
            }
            high = Math.max(high,dp[i]);     
        }
        return high;
    }

    //-----optimized
    public static int LIS_opti(int arr1[]){

        // making arr2 unique increasing sorted
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<arr1.length;i++){
            set.add(arr1[i]);
        }
        int arr2[]= new int[set.size()];
        int i=0;
        for(int num: set){
            arr2[i] = num;
            i++;
        }
        Arrays.sort(arr2); //ascending
       
        return  lcs(arr1,arr2); //helper function
    }
    public static int lcs(int[]arr1,int[] arr2){
        int n = arr1.length;
        int m = arr2.length;
        int dp[][] = new int[n+1][m+1];

        // initialization
        Arrays.fill(dp[0],0);  // when n=0 ,  0
        for(int i=0;i<dp.length;i++){
            dp[i][0]=0;
        }

        //sol bottom up
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(arr1[i-1] ==arr2[j-1])
                    dp[i][j]= dp[i-1][j-1]+1;
                else{
                    int ans1 = dp[i-1][j];
                    int ans2 = dp[i][j-1];
                    dp[i][j]  = Math.max(ans1,ans2);
                }
            }
        }

        return dp[n][m];
    }
}
