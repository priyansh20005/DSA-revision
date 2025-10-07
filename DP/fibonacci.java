/// obtaining fibonacci no by technique of memoization & tablulation
// same -> climbing stairs

public class fibonacci {
    
    // memoization
    public static int fib(int n , int[] val){
        if(n==0|| n==1){
            return val[n] =n;
        }
        if(val[n]!=0)
            return val[n];
        else
            return val[n] = fib(n-1 , val)+fib(n-2 ,val);
    }

    //tablulation
    public static int fib_tabluation(int n ){
        //initialization
        int[] dp= new int[n+1];
            //base condition
            dp[0] =0;
            dp[1]=1;
        
        // atta ching meaning
        // dp[i] = i th fibonacci no

        // filling
        for(int i=2;i<=n ;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }

        return dp[n];
    }

    public static void main(String args[]){
        System.out.println(fib(7 , new int[7+1]));
        System.out.println("fibonacci of 7-> "+ fib_tabluation(7));
    }
}
