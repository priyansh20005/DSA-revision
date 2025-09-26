//priority queue of objects
// mimp logic 

import java.util.*;

public class basic {

   public  static class Student implements Comparable<Student>{
        String name ;
        int rank;
       
        Student(String name , int rank){
            this.name = name;
            this.rank = rank;
        }

        // orveridding function of interface comparable
        @Override 
        public int compareTo(Student s2){
            return this.rank - s2.rank;
        }
    };

    public static void main(String args[]){
        
        PriorityQueue<Student> pq= new PriorityQueue<>();

        pq.add(new Student("Rohan " , 3 ));
        pq.add(new Student("Sami" , 6));
        pq.add(new Student ("Priyansh" , 2));
        pq.add(new Student("priyaaa.." , 1));

        while(!pq.isEmpty()){
            Student temp = pq.remove();
            System.out.println(temp.name +" rank= "+ temp.rank  );
        }

        System.out.println(" ----------now revering order------");
///////////////////////////////////////////////////
        PriorityQueue<Student> pq2= new PriorityQueue<>(Comparator.reverseOrder());

        pq2.add(new Student("Rohan " , 3 ));
        pq2.add(new Student("Sami" , 6));
        pq2.add(new Student ("Priyansh" , 2));
        pq2.add(new Student("priyaaa.." , 1));

        while(!pq2.isEmpty()){
            Student temp = pq2.remove();
            System.out.println(temp.name +" rank= "+ temp.rank  );
        }
    }
}