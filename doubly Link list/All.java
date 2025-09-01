
public class All{

    class Node{
        int data;
        Node next , prev;
        Node(int data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    static int count=0;
    static Node head = null;
    static Node tail = null;


    //add first
    public void addFirst(int data){
        Node newNode = new Node(data);
        count++;
        if(head == null){
            head = tail = newNode;
            return;
        }

        newNode.next = head;
        head.prev = newNode;
        head = newNode;

    }


    //add Last
    public void addLast(int data){

        Node newNode = new Node(data);
        count++;

        if(head == null){
            head = tail = newNode;
            return;
        }

        newNode.prev = tail;
        tail.next = newNode;
        tail= newNode;
    }


    //remove first
    public void removeFirst(){
        if(head == null ){
            System.out.println("already empty ll");
            return ;
        }
        else if(head.next ==null){
            head = tail = null;
            count--;
            return;
        }
        count--;
        head = head.next;
        head.prev.next = null;
        head.prev = null;
    }


    //remove last
    public void removeLast(){
         if(head == null ){
            System.out.println("already empty ll");
            return ;
        }
        else if(head.next ==null){
            head = tail = null;
            count--;
            return;
        }

        count--;
        tail = tail.prev;
        tail.next.prev = null;
        tail.next  = null;
    }

    // remove ith
    public void remove(int idx){
        //edge cases
        if(idx > count  || idx < 0){
            System.out.println("Invalid index  ot not exists");
            return;
        }
        else if(idx == 0){
            removeFirst();
            return;
        }
        else if(idx == count -1){
            removeLast();
            return;
        }
        

        count--;
        int repitation = idx -1;
        Node temp = head;
        while(repitation > 0){
            repitation--;
            temp= temp.next;
        }

        temp.next = temp.next.next;
        temp.next.prev.next = null;
        temp.next.prev = temp;
    }


    // add at ith position

    public void addAt(int idx , int data){

        if(idx > count || idx < 0){
            System.out.println("not a valid index ");
            return;
        }

        if(idx == 0 ){
            addFirst(data);
            return;
        }
        else if(idx == count){
            addLast(data);
            return;
        }

        
        // in between
        count++;
        Node newNode = new Node(data);

        int repi= idx-1;
        Node temp = head;


        while(repi > 0){
            repi--;
            temp = temp.next;
        }

        //setting newnode attri
        newNode.next = temp.next;
        newNode.prev = temp;
        
        //setting new in ll position
        temp.next.prev = newNode;
        temp.next = newNode;
    }


    public static void main(String args[]){


    }
}