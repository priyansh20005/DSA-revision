import java.util.*;

public class nextGreater{

    // given an array of postive nums , return an (ans array) that store the next greater element 
    // for that index at same idx in  ans arr;

    // 1.> by brute force 0(n^2);
    // 2 > recursive  / stack

// this is brute force recersively O(n^2)
    public static void nextGreater(int[] arr , int[] nextGreater){

        // nextGreater[nextGreater.length -1] = -1;
        int idx =arr.length-1;

        // for brute force
        // findGreater(arr , nextGreater ,  idx);


        //for stack
        Stack<Integer> s = new Stack<>();
        // stack stores the next grater val in it 
        nextGreater[idx] = -1;
        s.push(arr[idx]);

        findNextGreater(arr , nextGreater ,s , idx-1);

    }

    //helperr brute force
    public static void findGreater(int[] arr , int[] nextGreater , int idx){

        if(idx ==-1){
            return ;
        }

        if(idx == nextGreater.length -1){
            nextGreater[nextGreater.length -1] = -1;
            findGreater(arr , nextGreater , idx-1);
            return;
        }
        int temp = idx+1;
        while( temp < arr.length && arr[idx] >= arr[temp]){
            temp++;
        }

        if(temp == arr.length){
            // not found greater;
            nextGreater[idx] = -1;
        }
        else{
            nextGreater[idx] = arr[temp];
        }

        findGreater(arr , nextGreater , idx-1);
    }


//by using Stack Optimized
    //helper
    public static void findNextGreater(int[] arr , int[] nextGreater , Stack<Integer> s , int idx){
        
        //BC
        if(idx == -1){
            return ;
        }


        // pop values from stack till get the greater value then number
        while(!s.isEmpty() ){
            if(arr[idx] < s.peek()) {
                // got value greater
                nextGreater[idx] = s.peek();
                break;
            }else 
              s.pop();
        }
        if(s.isEmpty()){
            nextGreater[idx] = -1;
        }
        
        // push current val in stack
         s.push(arr[idx]);

         //call for prev idx
         findNextGreater(arr , nextGreater , s , idx-1);
        

    }





    public static void main(String agrs[]){

    }


    /// leetcode
    // public int[] secondGreaterElement(int[] nums) {

    // int [] nextSecGreater = new int[ nums.length ];

    //     // base case 
    //     if(nums.length < 3){
    //         if(nums.length == 0){
    //             return nextSecGreater;
    //         }
    //         else if(nums.length ==1){
    //             nextSecGreater[0] =-1;
    //             return nextSecGreater;
    //         }
               
    //         else {
    //              nextSecGreater[0] =-1;
    //               nextSecGreater[1] =-1;
    //             return nextSecGreater;
    //         }
            
    //     }
        
    //     Stack<Integer> s = new Stack<>();
        

    //     int idx = nums.length-1;

    //     nextSecGreater[idx] = -1;
    //     s.push(nums[idx]);
    //     idx--;
    //     nextSecGreater[idx] = -1;
    //     s.push(nums[idx]);
    //     idx--;

    //     while(idx >=0){
    //         //function call
    //      nextSecGreater[idx] = findIterative(nums , idx);
    //      idx--;
    //     }
        

    //     return nextSecGreater;
    // }

// work in n^2 time
    // public static int findIterative(int [] nums ,  int idx){

    //     int n = nums.length ;
    //     
    //     int curr_idx = idx;
    //     int count =0;

    //     while(curr_idx < n ){
    //         if(nums[idx] < nums[curr_idx]){
    //             count++;
    //         }
    //         if(count == 2){
    //             return nums[curr_idx];
    //         }
    //          curr_idx++;
    //     }

    //     return -1;

    // }


//recursive by stack  donest work
    // public static void find(int[] nums , int[] nextSecGreater , Stack<Integer> s , int idx){
    //     // base case
    //     if(idx == -1){
    //         return ;
    //     }


    //     while(!s.isEmpty() && nums[idx] < s.peek()) {
    //         s.pop();
    //     }
    //     if(s.isEmpty()){
    //         nextSecGreater[idx] = -1;
    //     }
    //     else{
    //         int temp = s.pop();

    //         if(s.size() >=1)
    //         nextSecGreater[idx] = s.peek();
    //         else
    //         nextSecGreater[idx] = -1;

    //         s.push(temp);
    //     }

    //     s.push(nums[idx]);

    // find(nums , nextSecGreater , s , idx-1);

    // }
}