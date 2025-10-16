// unbounded knapsack (coin change)  && rod cutting for max proft
// given coins and target value 
//find total no of ways to complete target 

// same as the unbounded knapsack just total ways approach

public class coinChange {
    public static void main(String args[]){
        int[] coins = {1,2 , 3, 6, 5};
        int target = 20;

        System.out.println(totalWays(coins , target));
    }

    public static int totalWays(int []coins ,int target){
        int[][] ways = new int[coins.length+1][target+1];
        // for sum =0 , ways =1
        for(int i=0; i<ways.length;i++)
            ways[i][0]=1;
        
        // for coins =0 ways =0 ;
        for(int j=1; j<ways[0].length;j++)
            ways[0][j]=0;

        for(int i=1 ; i<ways.length;i++){ //i = coins
            for(int j=1; j<ways[0].length;j++){  // j= target
                int currCoin = coins[i-1];
                if(currCoin <= j){
                    ways[i][j] = ways[i][j-currCoin] + ways[i-1][j];
                }else{
                    ways[i][j] = ways[i-1][j];
                }       
            }
        }
        return ways[coins.length][target];
    }

    // return the max profit obtianed  by cutting  rod of length n into given length
    public static int maxProfit(int[] pieces , int[] prices , int length){
        int [][] profit = new int[pieces.length+1][length+1];
        //when length =0 profit =0
        for(int i=0; i<profit.length;i++)
            profit[i][0]=0;
        //when pieces =0 ; profit = 0
        for(int j=1 ; j<profit[0].length;j++)
            profit[0][j] = 0;
    
        for(int i=1;i<profit.length;i++){ // i = rod peices
            for(int j=1;j<profit[0].length;j++){  // j = length of rod
                int currPieceLen = pieces[i-1];
                int currPiecePrice = prices[i-1];
                if(currPieceLen <=j ){
                   int ans1 = currPiecePrice +profit[i][j-currPieceLen];
                   int ans2 = profit[i-1][j];
                   profit[i][j]= Math.max(ans1,ans2);
                }else{
                    profit[i][j] = profit[i-1][j];
                }
            }
        }
        return profit[pieces.length][length];
    }
}
