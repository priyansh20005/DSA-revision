public class gridWays{

    public static int gridWays(int i , int j , int row , int col){
        // O(2^n+m)
        // i , j  - current index , source index
        // row , col , = total rows
        // row-1 , col-1 = destination index;

        //base case
        if( i ==row-1 ){
            return 1 ;
        }
        else if(j ==col-1){
            return 1;
        }

        //recursion 
        int ways1 = gridWays(i , j+1 , row , col); // right
        int ways2 = gridWays(i+1 , j , row , col); // down 
        int totalways = ways1+ ways2;


        return totalways;
    }


    public static int optimizied_gridWays(int i , int j , int row , int col){

        //permutation concept

        int numerator = factorial(row-1+col-1);
        int deno1 = factorial(row-1);
        int deno2= factorial(col-1);

        int permutation = numerator/(deno1*deno2);

        return permutation;
    }


    public static int factorial(int n ){
       if(n==1) return 1;
       return n*factorial(n-1);
    }

    public static void main(String args[]){

        int row= 4;
        int col=4;

        int totalways = gridWays(0 , 0 , row , col);
        System.out.println("total no of ways to reach destination from given source is =" + totalways);

        int optimizedWays = optimizied_gridWays(0,0 , 3 , 3);
        System.out.println("optimized ways " + optimizedWays);


    }
}