// get he maximum of the subarray[i to j] of given arr
/// update the val at arr[idx]
/// 


import java.util.*;

public class subarrayMax {

    public static int[] tree;

    //step-1 initialization------------------------------------------
    public static void init(int n ){
        tree= new int[4*n];
    }

    //step2 -> construct segment tree---------------------------------
    public static int buildST(int arr[] , int i , int st,int end){
        if(st==end)
            return tree[i] =arr[st];
        
        int mid = (st+end)/2;
        int leftMax = buildST(arr,2*i+1,st,mid);
        int rightMax=buildST(arr,2*i+2,mid+1 , end);

        return tree[i]  =Math.max(leftMax,rightMax);
    }

    //main function-----------------------------------------MAIN   MAIN-----------------
    public static void main (String args[]){
        int []arr = {1,2,3,4,5,6,7,8};
        int n=arr.length;
        init(n);
        buildST(arr,0,0,n-1);

        System.out.println(maxSubarr(arr, 2, n-1));

        for(int i=0;i<tree.length;i++){
                System.out.print(tree[i]+" ");
        }

        update(arr,3,78);
        System.out.println(maxSubarr(arr, 2, n-1));

        for(int i=0;i<tree.length;i++){
                System.out.print(tree[i]+" ");
        }

    }

    //step 3> query max of subarray-----------------------------------------------------------

    public static int maxSubarr(int[] arr , int qi , int qj){
        int n= arr.length;

        return maxSubarr_util(0,0,n-1 , qi,qj);
    }
    public static int maxSubarr_util(int i, int si,int sj,int qi,int qj){
        // not overlapping
        if(sj<qi || si>qj)
            return Integer.MIN_VALUE;
        // complete overlapping
        else if(si>=qi && sj<=qj){
            return tree[i];
        }
        // partial overlapping
        else{
            int mid = (si+sj)/2;
            int leftMax = maxSubarr_util(2*i+1, si,mid,qi,qj);
            int rightMax = maxSubarr_util(2*i+2, mid+1,sj,qi,qj);
            return Math.max(leftMax,rightMax);
        }
    }//--------------------------------------------------------------------------------------------------

    // step 4> update  ----------------------------------------------------------------------------------
    public static void update(int arr[] , int idx , int newVal){
        int n= arr.length;
        arr[idx]= newVal;

        updateST(0 , 0,n-1 , idx ,newVal );
    }
    public static int updateST(int i, int si,int sj , int idx , int newVal){
        if(si<=idx && sj >=idx){// idx in range
            if(si ==sj) 
                return tree[i] = newVal;

            int mid = (si+sj)/2;
            int leftMax = updateST(2*i+1,si,mid,idx,newVal);
            int rightMax= updateST(2*i+2,mid+1,sj,idx,newVal);

            return tree[i]  =Math.max(leftMax,rightMax);
        }else{
            return tree[i];
        }
    }
}
