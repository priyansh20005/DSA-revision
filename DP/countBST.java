// given either array or length of arr
// we have to find all total no of ways to form BST by that arr size
// Catalan no

import java.util.*;

public class countBST {
    public static void main(String args[]){
        int[]arr={10,20,30,40};
        int n=4;
        System.out.println(totalBST(9));
    }
    public static int totalBST(int n){
        int [] bst = new int[n+1];
        bst[0]=1;
        bst[1]=1;
        bst[2]=2;

        for(int i=3;i<=n;i++){
            int temp=0;
            for(int j=0;j<i;j++){// j idx is main root 
                //no of all possible bst when j is main root
                int t2= bst[j]*bst[i-j-1];
                temp+=t2; // adding in main count 
            }
            bst[i]=temp;
        }
        return bst[n];
    }
}
