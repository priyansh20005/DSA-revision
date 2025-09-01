// return or print a interleave of a queue

import java.util.*;

public class interleave{


    public static void interLeave( Queue<Integer> q){

        int size = q.size();
        int mid;
        if(size%2 == 0){
            //even
            mid = size/2;
        }
        else // odd
            mid = size/2 +1;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        for(int i=0 ; i<mid ;i++){ // first half in q1
            q1.add(q.remove());
        }

        while(!q.isEmpty()){   // q2 second half 
            q2.add(q.remove());
        }

        // now storeing interleave in main queue = q

        while(!q2.isEmpty() && !q1.isEmpty()){
            q.add(q1.remove());
            q.add(q2.remove());
        }

        while(!q1.isEmpty()){
            q.add(q1.remove());
        }

    };

// reverse a queue
    public static void reverse(Queue<Integer> q){

        Stack<Integer> s = new Stack<>();

        while(!q.isEmpty()){
            s.push(q.remove());
        }

        while(!s.isEmpty()){
            q.add(s.pop());
        }
    }

    public static void printQueue(Queue<Integer> q){
         Queue<Integer> q1 = new LinkedList<>();

        while(!q.isEmpty()){
            int temp = q.remove();
            System.out.print(temp+" ");
            q1.add(temp);
        }
        System.out.println();

        while(!q1.isEmpty()){
            q.add(q1.remove());
        }
    }

    public static void main (String args[]){

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        q.add(6);
        q.add(7);
        q.add(8);
        q.add(9);
        q.add(10);

        printQueue(q);
        reverse(q);
        // interLeave(q);
        printQueue(q);

    }
}

