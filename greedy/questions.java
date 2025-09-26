import java.util.*;

public class questions{

    // find minumum absolute diss sum 
    // given two arr A B  of same length
    // pair element of A with B such that sum of absolute diff of pair is mini

    public static int minAbsDiffSum(int[] A , int[] B){
        int n = A.length;

        // we first need to sort both of them 
        Arrays.sort(A);
        Arrays.sort(B);

        // now pair with same index elemensts
        // bcuz their diff is min

        int sum =0;

        for(int i =0 ; i<n ;i++){
            int diff = Math.abs(A[i]-B[i]);
            sum+=diff;
        }
        
        return sum;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////
// Maximum Length of Pair Chain
// You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.
// A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.
// Return the length longest chain which can be formed.
// You do not need to use up all the given intervals. You can select pairs in any order.

     public int findLongestChain(int[][] pairs) {
        
        // sorting on basis of end (b) of each pair
        Arrays.sort(pairs , Comparator.comparingDouble(o->o[1]));

        int prevEnd = pairs[0][1];  //b
        // int prevEnd =0;  doesnt work for negative values
        int count=1;

        for(int i=1 ;i<pairs.length;i++){
                int start = pairs[i][0];   // c
            if(prevEnd < start){
                prevEnd= pairs[i][1];
                count++;
            }
        }
        return count;
    }

}