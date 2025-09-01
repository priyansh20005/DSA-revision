

public class circularLL{
    class Node{
        int data ;
        Node next;
        Node(int data){
            this.data= data;
            this.next = null;
        }
    }

    static int count=0;
    Node head = null;
    Node tail = null;


    // addd first
    public void addFirst(int data){

        Node newNode = new Node (data);
        count++;

        if(head == null){
           tail= head = newNode;
            tail.next  = head;
            return;
        }
        newNode.next = head;
        head = newNode;
        tail.next = head;
    }


    //add last
    public void addLast(int data){

        Node newNode = new Node(data);
        count++;
        if(head == null){
            head = tail = newNode;
            tail.next = head;
            return;
        }
        
        tail.next = newNode;
        tail = newNode;
        tail.next = head;
    }



    //remove first
    public void removeFirst(){

        if(head== null){
            return;
        }
        else if( head.next == head){
            head = tail = null;
            count--;
            return;
        }
        count--;
        head = head.next;
        tail.next = head;
    }


    //remove last
    public void removeLast(){
        if(head == null){
            return ;
        }
        else if(head.next == head){
            tail = head= null;
            count--;
            return;
        }
        
        count--;
        Node temp = head;
        while(temp.next != tail){
            temp = temp.next;
        }
        temp.next = head;
        tail.next = null;
        tail = temp;
    }



    public static void main(String agrs[]){

    }
}