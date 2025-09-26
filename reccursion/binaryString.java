
public class binaryString{

    public static int Npair( int n ){
        if(n==1 || n==2 ){
            return n;
        }

        int ways1 = Npair(n-1);
        int ways2 = Npair(n-2);

        return ways1 + (n-1)*ways2;
    }


    public static int tiling(int n){
        if( n == 0)return 1;
        else if (n==1 ) return 1;
        // else if (n==2) return 2;

        int ways1 = tiling(n-1);
        int ways2 = tiling(n-2);

        return ways1+ways2 ;
    }

    public static void printString( int n , int lastplace , String str){

        //base case
        if(n == 0 ){
            System.out.println(str);
            return;
        }

        // if(lastplace == 0 ){
        //     printString(n-1 , 0 , str+"0");
        //     printString(n-1 , 1 , str+"1");
        // }
        // else{
        //     printString(n-1 , 0 , str+"0");
        // }

        printString(n-1 , 0 , str+"0");
        if(lastplace == 0 ){
            printString(n-1 , 1 , str+"1");
        }
    }

    public static void main(String args[]){
        System.out.println("heelo");

        printString( 3 , 0 , "");

        System.out.println( "No of ways in which tiles can arrange is -> "+tiling(5));

        System.out.println("No of ways in which friends can pair -> "+Npair(5));
    }
}