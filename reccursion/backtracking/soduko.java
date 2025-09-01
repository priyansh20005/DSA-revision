public class soduko{


    public static boolean soduko(int[][] board , int i , int j){

        //base case
        if(j== board[0].length){
            return soduko(board , i+1 , 0);
        }
        if(i== board.length){
            printArr(board);
            return true;
        }

        // work
        if(notfilled(board , i , j)){
            for(int k=1; k<=9 ; k++){
            if(isSafe(board , i , j , k)){
                board[i][j] = k; 
               if( soduko(board , i , j+1)){
                return true;
               }
                board[i][j]=0;
            }
        }
        }else{
            //next cell call
            return soduko(board , i , j+1);
        }

        return false;
        
    }


    public static boolean notfilled(int[][] board, int row , int col){

        if(board[row][col] == 0)
        return true;
        else 
        return false;
    }


    public static boolean isSafe(int [][] board , int row , int col , int val){
        
        
        //checking in column 
        for(int i =0 ;i< 9 ; i++){
            // if(i== row) continue;

            if(board[i][col] == val )
            return false;
        }


        // checl in row
        for(int j=0 ; j<9 ;j++){
            // if(j==col) continue;

            if(board[row][j] == val) 
            return false;
        }


        //check grid

        //   int C=col/3;
        //   int R= row/3;

        //   for(int i=0 ; i<3 ;i++){
        //     for(int j=0;j<3 ;j++){
        //         // if(3*R+i == row && 3*C+j == col) continue;
        //         if(board[3*R+i][3*C+j] == val) return false;
        //     }
        //   }

        int SC = (col/3) *3;  // starting col idx of subgrid
        int SR = (row/3) *3;    //st row idx of subgrid

        for(int i=SR ; i <SR+3; i++){
            for(int j = SC; j<SC+3 ; j++){
                if(board[i][j]==val) 
                    return false;
            }
        }




        return true;
    }


    public static void printArr(int[][] arr){
        int n= arr.length;
        int m = arr[0].length;

        for(int i=0 ; i< n ;i++){
            for(int j=0 ; j<m ; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println(); 
        }
         System.out.println();
    }

    public static void main(String args[]){
        int[][] board = {
    {5, 3, 0, 0, 7, 0, 0, 0, 0},
    {6, 0, 0, 1, 9, 5, 0, 0, 0},
    {0, 9, 8, 0, 0, 0, 0, 6, 0},
    {8, 0, 0, 0, 6, 0, 0, 0, 3},
    {4, 0, 0, 8, 0, 3, 0, 0, 1},
    {7, 0, 0, 0, 2, 0, 0, 0, 6},
    {0, 6, 0, 0, 0, 0, 2, 8, 0},
    {0, 0, 0, 4, 1, 9, 0, 0, 5},
    {0, 0, 0, 0, 8, 0, 0, 7, 9}
                        };

    boolean isPossible = soduko(board, 0, 0 );

    }
}