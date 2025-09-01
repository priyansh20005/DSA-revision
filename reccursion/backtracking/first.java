


public class first{

    // first set arr then unset it
    public static void incArr(int arr[] , int idx , int val){
        
        //base case
        if(idx == arr.length){
            printArr(arr);
            return ;
        }

        //kamm
        arr[idx] = val;
        incArr(arr , idx+1 , val+1);
        arr[idx] -= 2;

        if(idx == 0){
            printArr(arr);
        }
    }


    // find all subsets of an array or string
    public static void subsets(String str , String ans, int idx ){
        
        if(idx == str.length()){
            System.out.println(ans);
            return;
        }
       

            subsets(str , ans+ str.charAt(idx) , idx+1 );
            subsets(str ,ans , idx+1 );
    }


    public static void printArr(int arr[]){
        int n = arr.length;
        for(int i=0 ; i<n ;i++){
            System.out.print(arr[i]+"  ");

        }
        System.out.println();
    }

    //print all possible permutations of the string
    public static void permutation(String str , String ans){
       
        if( str.length() == 0){
            System.out.println(ans );
            return ;
        }


        for(int i=0 ; i <str.length() ; i++){

            char curr= str.charAt(i);
            String NewStr = str.substring(0, i) + str.substring(i+1);

            permutation(NewStr , ans+curr);
        }
    }

    public static void main(String args[]){
        int arr[] = new int[5];

        incArr(arr , 0 ,0 );

        String str = "abc";

        // subsets(str , "" , 0);

        permutation(str , "" );

    }
}