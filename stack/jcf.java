import java.util.LinkedList;
import java.util.Stack;

public class jcf{

    public static boolean isEven(int data){
        if(data%2 == 0)return true;
        return false;
    }

    public static void main(String args[]){

        Stack<Integer> s1  = new Stack<>();

    //    System.out.println(s1.pop()); 
        s1.push(7);
        s1.push(9);
        s1.peek();
        s1.push(11);
        s1.push(3);

        while(!s1.isEmpty()){
            System.out.print(s1.pop()+"-> ");
        }
         System.out.println();


        //Linked list 
        LinkedList<Integer> ll = new LinkedList<>();
        ll.addFirst(1);
        ll.addLast(2);
        ll.isEmpty();
        // ll.removeFirst();
        // ll.removeLast();

        ll.add(1, 333);
        ll.remove(1);//index 1
        


        System.out.println(ll);
    }
}