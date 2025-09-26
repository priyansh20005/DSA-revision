/// heap implementation 
/// insertion and deletion


import java.util.*;

public class heapify{

    public static class MinHeap{
        ArrayList<Integer> arr = new ArrayList<>() ;

        public void add(int data){ //O(log n )

            // add at last
            arr.add(data);
            int childIdx = arr.size()-1;
            

            //min heap case
            while(childIdx >0){
                int parentIdx = (childIdx-1)/2;

                if(arr.get(parentIdx) <= arr.get(childIdx))
                    break;

                else if(arr.get(parentIdx) > arr.get(childIdx)){
                    ///swaping
                    int temp = arr.get(parentIdx);
                    arr.set(parentIdx , arr.get(childIdx));
                    arr.set(childIdx, temp);

                    childIdx = parentIdx;
                }
            }

        }

        public boolean isEmpty(){
            return arr.size() ==0;
        }

        public int peek(){
            if (this.isEmpty())
                return -1;
            else 
                return arr.get(0);
        }

        public int remove(){
            if(isEmpty())
                return -1;
            else{
                 // swap first n last
                 int last = arr.size() -1;

                 int temp = arr.get(0);
                 arr.set(0 , arr.get(last));
                 arr.set(last , temp);

                 // remove the last i.e. prev root
                 int ans = arr.remove(last);

                 // heapify
                 Heapify(0);

                 // return deleted root ans
                 return ans;
            } 
               

            
        }

        public void Heapify(int parentIdx){
            if(isEmpty()) 
                 return;
            
            // left child n right child comapring

            int lastIdx = arr.size()-1;
            int left = 2*parentIdx +1;
            int right = 2* parentIdx +2;

            // comparing 
            if(right <= lastIdx){
                // compare both
                if(arr.get(left) <= arr.get(right)){
                    // swap left and parent
                    int temp = arr.get(parentIdx);
                    arr.set(parentIdx , arr.get(left));
                    arr.set(left , temp);
                    parentIdx = left;
                    Heapify(parentIdx);
                }else{
                    // swap root and right
                    int temp = arr.get(parentIdx);
                    arr.set(parentIdx , arr.get(right));
                    arr.set(right , temp);
                    parentIdx = right;
                    Heapify(parentIdx);
                }

            }
            else if(left == lastIdx){
                // no right child
                int temp = arr.get(parentIdx);
                arr.set(parentIdx , arr.get(left));
                arr.set(left , temp);

                parentIdx = left;
                return ;
            }
            else{
                // no left no right
                // leaf node 
                return;
            }

        }

        public void printHeap(){
            if(isEmpty())
                return ;
            else {
                for(int i=0 ; i< arr.size();i++)
                    System.out.print(arr.get(i)+" ");
            }
            System.out.println();
        }
    }


    public static void main(String args[]){

        MinHeap heap1 = new MinHeap();
        System.out.println(heap1.isEmpty());
        heap1.add(45);
        heap1.add(90);
        heap1.add(10);
        heap1.add(40);
        heap1.add(55);
        heap1.add(100);
        System.out.println(heap1.peek());
        heap1.printHeap();
        heap1.remove();
        heap1.printHeap();
    }
}