

class bubble_sort{

    public static void printArr(int[] nums){

        System.out.print("(");
    
        int n= nums.length;
        for(int i=0 ;i< n ; i++){
            System.out.print( nums[i] + " , ");
        }

        System.out.println(")");
    }


    public static void sortColors(int[] nums) {
        
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

    public static void main(String args[]){

        int nums[] = { 7 , 4, 8 , 3 , 0 , 3};
        printArr(nums);

        sortColors(nums);
        printArr(nums);


        
    }
}