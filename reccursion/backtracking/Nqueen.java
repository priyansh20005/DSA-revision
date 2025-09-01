

public class Nqueen{
    
    static int count =0;

    public static void Nqueen(char board[][] , int row ){

       
        
        if(row == board.length){
           printBoard(board) ;
           count++;
           return ;
        }


        //
        for(int j=0 ;j<board.length ; j++ ){
            if(isSafe(board , row , j)){
                board[row][j] ='Q';
                Nqueen(board , row+1);
                board[row][j] ='_';

            }
            
        }
        
    }


    //check for safe placing of queen in the row
    public static boolean isSafe(char board[][] , int row , int col){
        // we check only for upper side not for lower side 
        // right and left diagonal 
        // upwards in column

        boolean safe = true;

        int r = row-1;
        int c = col-1;
        // upwards in column 
        while(r >= 0){
            if(board[r][col]=='Q'){
                return false;
            }
            r--;
        }
        r= row-1;
        

        //left upper daigonal
        while(r>= 0 && c>= 0 ){
            if(board[r][c]=='Q')
                return false;
            r-- ; c--;
        }

        //right upper daigonal
        r= row-1;
        c=col+1 ;
        while(r >=0 && c<board.length){
            if(board[r][c]=='Q')
                return false;
            c++ ;
            r--;
        }
        
        return safe;
    }



    //print chessboard function
    public static void printBoard(char arr[][]){

        for(int i=0 ; i<arr.length ;i++){
            for(int j=0 ; j<arr.length ;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }



    public static void main(String args[]){

        int n =5;
        char board[][] = new char[n][n];

        //initialize
        for(int i=0 ; i<n ;i++){
            for(int j=0 ; j<n ;j++){
                board[i][j] = '_';
            }
        }

        Nqueen(board , 0);
        System.out.println("ways to place nqueens is "+ count);

    }
}