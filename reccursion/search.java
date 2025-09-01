// search in rortaed sorted array 


public class search{ 

    public static int search(int arr[] , int target , int si , int ei){

        if(si > ei) return -1;

        int mid = (si+ei)/2 ;

        if(arr[mid]== target){
            return mid;
        }

        // two cases - lies in line 1 or lies in line 2
        if(arr[si] <= arr[mid]){
            // lies in line1
            if(target >= arr[si] && target <= arr[mid]){
                //left side of line1
                return search(arr , target , si , mid-1);
            }else{
                //right part of mid
                return search(arr , target , mid+1 , ei);
            }

        }
        else{
            //lies in line2
            if(target >= arr[mid] && target <= arr[ei]){
                // lies in right part of L2
                return search(arr,target , mid+1 , ei);

            }else{
                //left of the mid
                return search(arr, target , si , mid-1);
            }


        }
    }

    public static void main(String args[]){

        int arr[] = {6 , 8 , 19  , 21 , -4 , 0 , 2 , 4 , 5};
        int target = 5  ;

        int idx = search(arr , target , 0 , arr.length-1);

        System.out.println(" the index of target is "+ idx);

    }
}