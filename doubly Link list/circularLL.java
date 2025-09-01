
public class circularLL{

    public class Node{
        int data;
        Node prev;
        Node next;
        Node(int data){
            this.data=data;
            this.next = null;
            this.prev =null;
        }
    }

    Node head= null;
    Node tail = null;
    static int count =0;


    public void addFirst(int data){

        count++;
        Node newNode = new Node(data);
        if(head == null){
            head = tail = newNode;
            head.next = head;
            head.prev= head;
            return ;
        }
        // //not needed
        // else if(head.next == null){
        //     newNode.next = head;
        //     head.prev = newNode;
        //     head = newNode;
        //     head.prev = tail;
        //     tail.next = head;
        //     return;
        // }

        newNode.next= head;
        newNode.prev = tail;
        head.prev = newNode;
        head  = newNode;
        tail.next = head;
    }

    // addLast
    public void addLast(int data){

        count++;
        Node newNode = new Node(data);

        if(head== null){
            head = tail = newNode;
            head.next = head;
            head.prev= head;
            return;
        }

        newNode.next = head;
        newNode.prev = tail;

        tail.next = newNode;
        // tail = newNode;
        // head.prev = tail;
        head.prev = newNode;
        tail = newNode;

    }


    //remove first
    public void removeFirst(){

        if(head == null){
            System.out.println("allready empty ");
            return ;
        }
        else if( head.next == head){
            head = tail = null;
            count--;
            return ;
        }

        count--;
        tail.next = head.next;
        head = head.next;
        head.prev = tail;
    }


    //remove last
    public void removeLast(){
        if(head == null){  // no nodes
            return ;
        }
        else if(head.next == head){  // one node only
            count--;
            head = tail = null;
            return ;
        }
        count--;
        // tail.prev.next = head;
        // tail = tail.prev;
        // head.prev = tail;
        tail = tail.prev;
        tail.next = head;
        head.prev = tail;
    }



    public static void main(String args[]){

    }
}