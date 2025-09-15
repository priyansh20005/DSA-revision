/// return size of largest subarray with sum 0 or target;

import java.util.*;


public class largestSubarray{

    public static int getSize(int[] arr , int target){
        int n = arr.length;
        // int subSum[] = new int[n];
        int size =0;
        int currSum =0;

        HashMap<Integer,Integer> sum = new HashMap<>();

        for(int i=0 ; i<n ;i++){
            currSum += arr[i];
            if(sum.containsKey(currSum+target)){
                // j-i = size
                size = i - sum.get(currSum+target);
            }
            else
            sum.put(currSum, i);
        }
        return size;
    }

    public static void main(String args[]){
        int arr[] ={15,-2 ,2,-8 , 1 ,-1 , 7 ,1, 10};
        int target =0;

       int size = getSize(arr , target);
       System.out.println(size);
    }
}