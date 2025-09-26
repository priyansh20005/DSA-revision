import java.util.ArrayList;

public class first{

    public static class Stack{

        static ArrayList<Integer> list = new ArrayList<>();

        static int top =-1;

        // check empty or not 
        public static boolean isEmpty(){
            if(list.size() == 0)
            return true;
            else return false;
        }


        // push 
        public static void push(int data){
            top++;
            list.add(data);
        }

        // pop 
        public static int pop(){
            if(top == -1){
                System.out.println("already empty stack");
                return -1;
            }
            int temp = list.get(top);
            list.remove(top);
            top--;
            return temp;
        }


        //peek
        public static int peek(){
            if(list.size() == 0){
                System.out.println("empty stack ");
                return -1;
            }
            return list.get(list.size()-1);
        }

        public static void print(){
            if(list.size() == 0){
                System.out.println("empety stack");
                return;
            }

            int top = list.size()-1;
            while(top >= 0){
                System.out.print( list.get(top)+" ->");
                top--;
            }
            System.out.println();
        }
    }



    public static void main(String args[]){

        Stack s = new Stack();
        s.push(2);
        s.push(4);
        s.push(7) ;
        s.print();
        s.pop();
        s.print();

        
    }
}