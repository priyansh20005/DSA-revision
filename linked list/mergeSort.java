class mergeSort{

    public static void main(String args[]){

        head = sortList(head);
    }


    public ListNode sortList(ListNode head) {

       return mergesort(head);
    }

    public ListNode mergesort(ListNode head1){

        if(head1 == null || head1.next ==null ){
            return head1;
        }

        // finding mid
        ListNode mid = findMid(head1);

        ListNode right_head = mid.next;
        mid.next = null;

        head1 =mergesort(head1);
        right_head= mergesort(right_head);

        return merge(head1 , right_head);

    }

    public ListNode merge(ListNode left_head , ListNode right_head){

        //base case
        if(left_head==null){
            return right_head;
        }
        if(right_head == null){
            return left_head;
        }

        ListNode templ = left_head;
        ListNode tempr = right_head;
        ListNode sorted_head = new ListNode(0);
        ListNode prev = sorted_head;

        while(templ != null && tempr != null){

            // if(sorted_head == null){
            //     if(templ.val < tempr.val){
            //     prev = templ;
            //     templ= templ.next;
            //     }else{
            //     prev = tempr;
            //     tempr=tempr.next;
            //       }
            //     sorted_head = prev;
            // }
           
                if(templ.val < tempr.val){
                     prev.next = templ;
                    templ= templ.next;
                }else{
                prev.next = tempr;
                tempr=tempr.next;
                 }
                  prev = prev.next;
            
        }

        // while(templ != null){
        //     prev.next = templ;
        //     templ= templ.next;
        //     prev = prev.next;
        // }

        if(templ == null){
            prev.next = tempr;
        }

        // while(tempr!= null){
        //     prev.next = tempr;
        //     tempr=tempr.next;
        //     prev=prev.next;
        // }
        if(tempr == null){
            prev.next = templ;
        }

        // becuz we created an extra node at starting 
         sorted_head = sorted_head.next;
         
        return sorted_head;
    }

    public ListNode findMid(ListNode head1){

        ListNode slow = head1;
        ListNode fast = head1.next;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast= fast.next.next;
        }

        return slow;
    }
}