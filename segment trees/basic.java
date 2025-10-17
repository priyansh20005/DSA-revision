// basic of the segment trees

import java.util.*;

public class basic {
    static int tree[];

    //initialization of segment tree -1;
    public static void init(int n){
        tree=new int[4*n];
    }
    
    // making segment tree - step 2
    public static int buildST(int []arr , int idx , int st,int end){
        //bc 
        if(st==end)
            return tree[idx]= arr[st];

        int mid=(st+end)/2;
        int leftsum=buildST(arr,2*idx+1 , st,mid);
        int rightsum=buildST(arr,2*idx+2 , mid+1,end);

        return tree[idx]= leftsum+rightsum;
    }

    // main function -----------------------------------------------------------------------------------------------
    public static void main(String args[]){
        int arr[]={1,2,3,4,5,6,7,8};
        int n = arr.length;
        init(n);
        buildST(arr,0,0,n-1);

        for(int no:tree){
            System.out.print(no+" ");
        }

        System.out.println(getSum(arr,2,5));
        update(arr,2,2);
        for(int no:tree){
            System.out.print(no+" ");
        }

        System.out.println(getSum(arr,2,5));
    }

    // get ans of query ---------------------------------------------------------------------------------------------
    public static int getSum(int[]arr,int qi ,int qj){
        int n = arr.length;
        return getSum_util(0,0,n-1,qi,qj);
    }
    public static int getSum_util(int i,int si,int sj,int qi,int qj){
        if(qj<=si ||qi>=sj){
            return 0;
        }else if(si>=qi && sj<=qj){
            return tree[i];
        }
        else{
            int mid=(si+sj)/2;
            int left = getSum_util(2*i+1 , si,mid,qi,qj);
            int right = getSum_util(2*i+2 ,mid+1,sj,qi,qj);
            return left+right;
        }
    }//---------------------------------------------------------------------------------------------------------------

    // ------------------  updating the arr and segment tree o(logn)-------------------
    public static void update(int[]arr , int idx , int newVal){
        int n= arr.length;
        int diff = newVal-arr[idx];
        arr[idx]= newVal; // array is updated 
        //now updating the segment tree
        updateST(0,0,n-1,idx , diff);
    }
    public static void updateST(int i,int si,int sj , int idx ,int diff){
        if(idx >=si && idx <=sj){ // included n given range
            tree[i]+=diff;
            if(si==sj)
                return ;
            int mid = (si+sj)/2;
            updateST(2*i+1,si,mid,idx,diff);
            updateST(2*i+2,mid+1,sj,idx,diff);

        }else{ // not in given range 
            return ;
        }
    }//--------------------------------------------------------------------------------------
}
