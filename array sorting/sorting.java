

class sorting{

    public static void quickSort(int arr[] , int si , int ei){
        if(si >= ei) return ;

        int pivot = ei;
        int  i= si ;
        int j = si ;

        while (j <= ei){
            if(arr[j] < arr[pivot]){
                //swap 
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp ;
                i++;
            }
            j++;
        }
         // positioning pivot at correct place 
        // swap i and pivot 
        int temp = arr[pivot] ;
        arr[pivot] = arr[i];
        arr[i] = temp; 
        // i++;

        // i is pointing to pivot 
        quickSort(arr, si , i-1);
        quickSort(arr , i+1 , ei);


    }


    // public static void quickSort(int arr[]   , int si , int ei){
        
    //     if(si == ei) return;
        
    //     int pivot = si;
    //     int i = pivot+1;
    //     int j = ei;


    //     while(arr[i] < arr[pivot]){
    //         i++;
    //     }
    //     while(arr[j] > arr[pivot]){
    //         j--;
    //     }

    //     if(i < j ){
    //         //swap i and j 
    //         int temp = arr[i];
    //         arr[i] = arr[j];
    //         arr[j] = temp;
    //     }
    //     else{
    //         // swap j and pivot 
    //         int temp = arr[j];
    //         arr[j]= arr[pivot];
    //         arr[pivot] = temp;
    //         quickSort(arr ,si , j );
    //         quickSort(arr , j+1 , ei);
    //     }

        
    // }

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


    //insertion sort -> insert key into correct position before key-idx 
    // key start from index 1 to n (i++)
    public static void insertionSort(int[] nums){
        int n = nums.length;

        int key = 1;

        for(int i = 1 ; i<n ;i++){
            key = i;
            // int val = nums[i];  //key value 

            if (nums[key] < nums[key-1] ) {
                int k =key-1;
                int val = nums[key];  //key value

                 while( (k >= 0) && val < nums[k] ){                   
                    //shifting all values by index 1 before key
                    nums[k+1] = nums[k];
                    k--;
                 }

                 nums[k+1] = val ;
            }  
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
        // selectionSort(nums);
        // insertionSort(nums);
        quickSort(nums , 0 , nums.length-1);
        printArr(nums);


        
    }
}