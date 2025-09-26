import java.util.*;

public class priorityQ{

    public static void main(String args[]){

        // generally ascending order
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(4);
        pq.add(9);
        pq.add(1);
        pq.add(-2);

        while(!pq.isEmpty()){
            System.out.print(pq.remove()+" ");
        }
        System.out.println("");
    
        // for descending order
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Comparator.reverseOrder());

        pq2.add(4);
        pq2.add(9);
        pq2.add(1);
        pq2.add(-2);

        while(!pq2.isEmpty()){
            System.out.print(pq2.remove()+" ");
        }
        System.out.println("");

    }
}