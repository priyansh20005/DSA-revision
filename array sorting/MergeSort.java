

public class MergeSort{

    public static void printArr(int arr[]){
        int n = arr.length;
        for (int i=0 ; i<n ; i++){
            System.out.print(arr[i] +" ");
        }
        System.out.println();
    }


    public static void mergeSort(int arr[] , int si ,  int ei){

        if(si >= ei ) return ;

        int mid = si + (ei-si)/2 ;
        mergeSort(arr , si , mid);
        mergeSort(arr , mid+1 , ei);

        merge(arr ,si , mid, ei);
    }

    public static void merge(int arr[] , int si , int mid ,  int ei){
       
        int temp[] = new int[ei-si+1] ;

        int i = si ; // points left array
        int j = mid+1 ; // points right array 
        int k= 0 ;// for temp arr 

        while (i<= mid && j<= ei){
            if(arr[i] < arr[j]) {
                temp[k] = arr[i];
                i++;
            }else{
                temp[k] = arr[j];
                j++;
            }
            k++;
        }

        while( i <= mid){
            temp[k] = arr[i];
            i++;
            k++;
        }
        while(j<= ei){
            temp[k] = arr[j];
            j++;
            k++;
        }


        for(int ii = si  , kk=0 ; ii <= ei ;ii++ , kk++){
            arr[ii] = temp[kk];
        }


    }


    public static void main(String args[]){

        int arr[]= {9, 55,-4 , 2, 6 , 89 , 7, 12};

        printArr(arr);
        mergeSort(arr , 2 , arr.length-1);
        printArr(arr);


    }
}