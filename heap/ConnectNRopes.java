import java.util.*;

public class ConnectNRopes {


    public static void main(String args[]){

        PriorityQueue<Integer> pq = new PriorityQueue<>();
    pq.add(4);
    pq.add(2);
    pq.add(3);
    pq.add(6);
        
        int sum=0;

        while(pq.size()>1){
            int min1 = pq.remove();
            int min2 =  pq.remove();
            int connected = min1+min2;
            sum+= connected;
            pq.add(connected);
        }

        System.out.println(sum);
    }
}