class first{

    public static class Node{
        int data;
        Node next;

       public Node(int data){
            this.data= data;
            this.next= null;

        }
    }
    public static Node head;
    public static Node tail;
    public static int count =0;


    // add first 
    public void addFirst(int data ){
        Node newNode = new Node(data);
        count++;
        if(head==null){
            head =newNode;
            tail = newNode;
        }else{
            newNode.next = head;
            head = newNode;
        }
    }


    // add last
    public void addLast(int data){
        
        Node newNode= new Node(data);
        count++;
        if(head == null){
            head = newNode;
            tail= newNode;
        }
        else{
            Node currNode= head;
            while(currNode.next != null){
            currNode = currNode.next;
            }
            currNode.next= newNode;
            tail = newNode;
            
        }

    }

    // add at i index
    public void addAt(int idx , int data){
        
        // edge cases
        if(idx == 0){
            addFirst(data);
            return;
        }

        Node newNode = new Node(data);
        Node temp = head;
        count++;
        if(head == null){
            head = newNode;
            return;
        }
        else if(idx > count){
            System.out.println(" index out of bound , insertion not possiblle");
            count--;
            return;
        }


        //main
        for(int i = 0; i< idx-1 ; i++){
            temp = temp.next;
        }
        Node nextNode = temp.next;

        //inserting new node
        temp.next=newNode;
        newNode.next = nextNode;

    }



    // remove first 
    public void removeFirst(){
        if(head == null){
            System.out.println("already empty LL");
            return ;
        }
        count--;
        if(head.next == null){
            head = null;
            return;
        }
        else{
            Node newHead = head.next;
            Node temp = head;
            temp.next = null;
            head = newHead;
        }
        
    }


    //remove last 
    public void removeLast(){

        if(head == null){
            System.out.println("already empty ll ");
            return;
        }
        count--;
        if(head.next == null){
            head = null;
            return;
        }
        else{
            Node prevNode = head;
            Node currNode = head.next;

            while(currNode.next != null){
            currNode= currNode.next;
            prevNode= prevNode.next;
                }

            tail = prevNode;
            prevNode.next = null;
        }
    }

    //print ll
    public void printList(){

        if(head == null){
            System.out.println("empty LL");
            return;
        }else if(head.next== null){
            System.out.println(head.data);
            return;
        }

        Node currNode = head;
        while(currNode.next!= null){
            System.out.print( currNode.data+" ->");

            currNode = currNode.next;
        }
        System.out.print( currNode.data+"-> null");
        System.out.println();
        System.out.println("count = "+ count);
    }


    // search for key in ll
    // /iterative 
    public int search(int key){
        Node temp = head;
        if(head == null){
            System.out.println("empty Linked lIst");
            return -1;
        }

        int i =0;
        while(temp!= null){
            if(temp.data == key){
                return i;
            }
            temp= temp.next;
            i++;
        }

        return -1;
    }

    // search for key - recursive
    public int search_recursive(int key){
        return helperSearch(head , key , 0);
    }
    //helper function
    public int helperSearch(Node currNode , int key , int idx){
        
        //base case
        if(currNode == null){
            return -1;
        }

        //work
        if(currNode.data == key ){
            return idx;
        }
        else
            return helperSearch(currNode.next , key , idx+1);
        
    }



    // reverse a ll iterative

    public void reverse(){
        if(head == null || head.next ==null){
            return ;
        }
        Node prev = null;
        Node curr = head;
        Node next = head.next;
        tail = head;

        while(next!= null){
            curr.next = prev;
            prev = curr ;
            curr = next;
            next = next.next ;
        }
            curr.next = prev;
       
        head = curr;
    
    }
    

    // check palindrome 
    public boolean isPalindrome(){

        // edge case 
        if(head == null || head.next == null){
            return true;
        }



        //find mid
        Node mid = findMid(head);


        //reversing next half - mid to last
        Node prev = null ;
        Node curr = mid ;
        Node next = mid.next;

        while(next != null){
            curr.next =prev;
            prev = curr;
            curr = next;
            next= next.next;
        }
            curr.next=prev;
            Node head2 = curr;


        // checking palindrome
        Node temp1 = head;
        Node temp2 = head2;
        while(temp2 != null){
            if(temp1.data != temp2.data){
                return false;
            }
            temp1=temp1.next;
            temp2= temp2.next;
        }
             // true 
        boolean answer = true;


        // re reversing 
        prev = null;
        curr = head2;
        next = head2.next;
        while(next != null){
            curr.next = prev;
            prev = curr;
            curr = next;
            next= next.next;
        }
            curr.next = prev;
        
     return answer;


        // now reversing ll
        // Node prev = null;
        // Node curr = mid;
        // Node next = mid.next;

        // while(next!= null){
        //     curr.next = prev;
        //     prev = curr;
        //     curr = next;
        //     next = next.next;
        // }
        // curr.next= prev;

        // Node head2 = curr;
        // Node temp2 = head2;
        // Node temp1 = head;

        // while(temp1!= null){
        //     if(temp1.data != temp2.data){
        //         return false;
        //     }
        //     temp1 = temp1.next;
        //     temp2 = temp2.next;
        // }

        // // re- reversing 2nd half
        // temp2 = head2;
        // prev = null;
        // curr = head2;
        // next = head2.next;

        // while(next != null){
        //     curr.next= prev;
        //     prev =curr;
        //     curr = next;
        //     next =next.next;
        // }
        //     curr.next = prev;
        
        //     head2 = curr;

        // return true;
    }

    // fid return mid node
    public Node findMid(Node head){
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next; //+1
            fast = fast.next.next ; //+2
        }

        Node mid = slow;
        return mid;
    }


    // detect loop in LL
    public boolean isCycle(Node head1){

        //base case 
        if(head1 == null || head.next == null){
            return false;
        }

        Node slow = head1;
        Node fast = head1;

        while(fast != null && fast.next != null){
            slow = slow.next ;
            fast = fast.next.next;
            if(slow == fast){

                return true;
            }
        }
        return false;
    }


    public void removeCycle(Node head1){
        

        boolean isCycle= isCycle(head1);
        if(isCycle == false)
        return ;
        
        Node slow = head1 ;
        Node fast = head1;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast= fast.next.next;
            if(slow == fast){
                break;
            }
        }

        Node prev = null;
        slow = head1;
        while(slow != fast){
            slow=slow.next;
            prev = fast;
            fast= fast.next;
        }

        if(prev == null){
            prev = fast ;
            while(prev.next != fast){
                prev = prev.next;
            }
        }
        prev.next = null;

    }


    // zig zag 
    public void zigzag(Node head1){

        //base cases
        if( head1 == null ||  head1.next == null || head1.next.next == null){
            return ; // 0 , 1 , 2 nodes only 
        }

        // 2nd half of ll
        // Node head2 = findMid(head1);
          // Node prev = null;
        Node slow = head1;
        Node fast = head1.next;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast=fast.next.next;
        }
        Node head2 = slow.next;
        slow.next = null;

        // reversing 2nd half
        Node prev = null;
        Node curr = head2;
        Node next= head2;

        while( next != null){
            next= next.next;
            curr.next=  prev;
            prev = curr;
            curr = next;
        }

        head2 = prev;

        Node newHead = new Node(0);
        prev = newHead;

        Node temp1 = head1;
        Node temp2= head2;
        // Node next1 = head1.next;
        // Node next2 = head2.next;
        // Node MainHead = head1;

        while(temp1 != null && temp2 != null){
            // temp1.next = temp2;
            // temp1 = next1;
            // next1 = next1.next;

            // temp2.next = temp1;
            // temp2 = next2;
            // next2= next2.next;
            
            prev.next = temp1;
            temp1 = temp1.next;
            prev =prev.next;

            prev.next = temp2;
            temp2=temp2.next;
            prev = prev.next;
        }

        if(temp1 != null){
            prev.next = temp1;
            temp1 = temp1.next;
        }

        // removing extra string head;
        head = newHead.next;
        return ;
    }

    public static void main(String args[]){

        first ll = new first();
        ll.addFirst(9);
        ll.addLast(7);
        ll.addFirst(2);

        ll.printList();

        ll.addAt(3 , 90);
        ll.addAt(0 , 60);
        ll.addAt(9 , 89);
        ll.addAt(5, 666);
        ll.printList();

        int idx = ll.search_recursive(90);
        if(idx >= 0){
            System.out.println("index of the key is "+ idx);
        }else{
            System.out.println("Key is not present in the LL");
        }

        ll.reverse();
        ll.printList();

        // if(ll.isPalindrome()){
        //     System.out.println(" this LL is palindrome ");
        // }else{
        //     System.out.println("this LL is not a Palindrome");
        // }

        ll.zigzag(head);
        ll.printList();
    }
}