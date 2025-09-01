// given an array of interger size n 
// return element occuring more theen n/3 times in array

import java.util.*;

public class MjaorityElement{


    public static void majorityElements(int[]arr , ArrayList<Integer> ans){

        HashMap<Integer ,Integer> map = new HashMap<>();

        for(int i=0; i<arr.length ;i++){
            int num = arr[i];

            if(map.containsKey(num)==false){ // first occurence
                map.put(num , 1);
            }
            else{ // not first occurence
                int prevVal=map.get(num);
                map.put(num , prevVal+1);
            }
        }

        int majority = arr.length/3;

        Set<Integer>keys = map.keySet();
        for(Integer k: keys){
            int val = map.get(k);
            if(val > majority)  
                ans.add(k);
        }

    }

    public static void main(String args[]){

        int arr[] = {1,3,2, 5,1,3,1,5,1};
        ArrayList<Integer> ans = new ArrayList<>();

        majorityElements(arr , ans);

        for(int i =0; i<ans.size() ; i++){
            System.out.print(ans.get(i)+" ");
        }

    }
}