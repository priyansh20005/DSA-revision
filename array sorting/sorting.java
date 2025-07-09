

class sorting{

    public static void bubbleSort(int[] nums) {
        
        int n = nums.length;

        for (int i=0 ; i<n-1 ; i++){
            int k = 0 ;
            
            while( k < n-i-1 ){
                 if( nums[k] > nums[k+1]){
                    //swap
                    nums[k] += nums[k+1];
                    nums[k+1] = nums[k] - nums[k+1] ;
                    nums[k] = nums[k] - nums[k+1];
                 }
                  k++;
            }
        }
    }

    /// selection sort 
    
    public static void selectionSort(int[] nums){
        int n = nums.length;
        int start_index = 0; // position to change with least number

        int temp , least_index =0;
        int least_number=  nums[0]; // change it to -infinity


        for (int i =0 ; i< n-1 ;i++){
            start_index = i;
            int k = i ;
            least_number = nums[i];
            least_index= i;

            while(k <= n-1){

                if(nums[k] < least_number){
                    // operation
                    least_number = nums[k];
                    least_index = k ;

                }
                k++;
                // System.out.println(".()" + least_index);
            }
            // we got minium value and index for this set 
            // now put the least value in starting by swapping 

            //swap 
            temp = nums[start_index];
            nums[start_index] = nums[least_index] ;
            nums[least_index] = temp ;
            // System.out.println(start_index +" "+ least_index +" "+ least_number);
            printArr(nums);
        }
    }


    //// print arrr element
      public static void printArr(int[] nums){

        int n= nums.length;
        
        System.out.print("(");
        for(int i=0 ;i< n ; i++){
            System.out.print( nums[i] + " , ");
        }

        System.out.println(")");
    }

    public static void main(String args[]){

        int nums[] = { 7 , 4, 8 , 3 , 0 , 3};
        printArr(nums);

        // bubbleSort(nums);
        selectionSort(nums);
        printArr(nums);


        
    }
}