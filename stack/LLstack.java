
class LLstack{

    public static class Node{
        int data;
        Node next;
        Node(int data){
            this.data= data;
            this.next = null;
        }
    }

    public static class stack{

        static Node head = null;
        static int count=0;

        //push 
        public static void push(int data){
            Node newNode = new Node(data);
            count++;

            if(head == null){
                head = newNode;
                return;
            }

            newNode.next = head;
            head = newNode;
        }

        //pop
        public static int pop(){
            if(head == null){
                System.out.println("already empty stack");
                return -1;
            }

            count--;
            int temp =head.data;
            head = head.next;

            return temp;
        }


        //peek
        public static int peek(){
            if(head == null){
                System.out.println("already empty stack");
                return -1;
            }
            return head.data;
        }
    }

    public static void main(String args[]){

    }
}