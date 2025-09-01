// g=Given an array of size n and window sixe K 
// find and store the max element from each window 

import java.util.*;

public class SlidingWindow{

    public static class Element implements Comparable<Element> {
        int val ;
        int idx;
        Element(int v , int i){
            val = v;
            idx = i;
        }

        @Override 
        public int compareTo(Element e2){
            if(this.val - e2.val == 0) // if val is same then compare on bof idx
                return this.idx - e2.idx; 
            else
                // return this.val- e2.val;  //min heap condition
                return e2.val- this.val; // max heap condition
        }
    }


    public static void getMaxElement(int[] arr , int k , int[] max ){

        // PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Element> pq = new PriorityQueue<>();
        int wind=0;

        for(int i =0 ;i<arr.length ; i++){
            pq.add(new Element(arr[i] , i));
            if(i >= k-1){  // window start
                Element temp = pq.peek();
                while(temp.idx < wind){
                      // it is not a part of current window 
                    pq.remove();
                    temp= pq.peek();
                 }
                max[wind] =temp.val;
                wind++;
            }
        }
    }


    public static void main(String args[]){

        int arr[] ={1 , 2, 13 , 4 , 25 , 6, 72 , 8 , 90 , 10};
        int k = 3;
        int max[] = new int[arr.length -k+1];

        
        getMaxElement(arr , k , max);

        /// now max contains ans 
        
        for(int i=0 ; i<max.length ; i++){
            System.out.print(max[i]+" ");
        }

    }
}