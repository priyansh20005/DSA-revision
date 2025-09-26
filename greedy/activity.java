// calculate the maximum no of activities a user can do

// 1> sort accord to end time 
// 2> select the activi that end fast and also disjoint with prev activity;


public class activity{


    public static int countActivities(int[] start  , int end[]){
        // assuming already sorted end time

        //if not sorted then sort first with start aligned
        printArr(start);
        printArr(end);

            sortEndTime(start , end);
        printArr(start);
        printArr(end);

        // select the first one bcuz it ends first;
        int prevEndTime = end[0];
        int count = 1;
        int newStartTime;

        for(int i =1; i< start.length ;i++){

            newStartTime = start[i];
            if(newStartTime >= prevEndTime){
                // it is disjoint . selected

                prevEndTime = end[i];
                count++;
            }
        }
        return count;

    }


    public static void sortEndTime(int[] start , int end[]){

        // merge sort 
        mergeSort(start , end , 0 , end.length-1);
    }

    public static void mergeSort(int[] start , int[] end , int si , int ei){

        if(si == ei){
            return ;
        }

        int mid = si+(ei-si)/2;

        // sort left
        mergeSort(start , end , si , mid);
        //sort right
        mergeSort(start, end , mid+1 , ei);

        // merge(start , )
        merge(start, end , si , mid , ei);
    }

    public static void merge(int[] start , int[] end , int si , int mid  , int ei){

        // sort according to end but also corresponding start
        int[] newStart = new int[ei-si+1];
        int[] newEnd = new int[ei-si+1];

        int i = si;
        int j = mid+1;
        int k =0;

        while(i <= mid && j <= ei){

            if(end[i] <= end[j]){
                newEnd[k] = end[i];
                newStart[k] = start[i];
                i++;
            }
            else{
                newEnd[k] = end[j];
                newStart[k] = start[j];
                j++;
            }
            k++;
        }

        while(j<= ei){
            newEnd[k] = end[j];
            newStart[k]= start[j];
            j++;
            k++;
        }
        while(i <= mid){
            newEnd[k] = end[i];
            newStart[k] = start[i];
            i++;
            k++;
        }

        i = si ;
        k=0;

        while(i <= ei){
            end[i] = newEnd[k];
            start[i] = newStart[k];
            i++;
            k++;
        }

    }

public static void printArr(int[] arr){
    for(int i =0; i<arr.length ; i++){
        System.out.print(arr[i]+" ");
    }
    System.out.println();
}

    public static void main(String args[]){

        int [] start = {3 ,1 , 5 ,0 , 5 ,8};
        int [] end= {4 , 2 , 7 , 6,9 , 9};

        System.out.println(" total no of activities ="+countActivities(start , end));

    }
}