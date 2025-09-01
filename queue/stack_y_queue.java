import java.util.*;

class stack_y_queue {

    static Queue<Integer> q1 ;
    static Queue<Integer> q2 ;

    public stack_y_queue() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }
    
    public void push(int x) {

        if(!q1.isEmpty())
            q1.add(x);
        else
            q2.add(x);
    }
    
    public int pop() {
        
        if(empty())
         return -1;


        if( q1.isEmpty()){   // q2 is main 

            while(q2.size() > 1){
                int temp = q2.remove();
                q1.add(temp);
            }
            // last element = ans
            int ans = q2.remove();
            return ans;
        }
        else{  // q1 is main
           
            while(q1.size() >1){
                q2.add(q1.remove());
            }

            // got last element in q1 = ans
            int ans = q1.remove();
            return ans;
        }
       
            // insert all elements of q1 to q2 except last 
            // int temp = q1.peek();
        //     int ans =q1.peek();

        //     while( !q1.isEmpty()){
        //         int temp = q1.remove();

        //         if(q1.isEmpty()){
        //            ans = temp;
        //             break;
        //         }
        //         else
        //         q2.add(temp);
        //     }

        //     // reverse setting q1
        //     while(!q2.isEmpty()){
        //         q1.add(q2.remove());
        //     }

        // return ans;
        
    }
    
    public int top() {

        if(empty())
         return -1;
       
        

        if( q1.isEmpty()){   // q2 is main 

            while(q2.size() > 1){
                int temp = q2.remove();
                q1.add(temp);
            }
            // last element = ans
            int ans = q2.remove();
            q1.add(ans);
            return ans;
        }
        else{  // q1 is main
           
            while(q1.size() >1){
                q2.add(q1.remove());
            }

            // got last element in q1 = ans
            int ans = q1.remove();
            q2.add(ans);
            return ans;
        }


            
        
    }
    
    public boolean empty() {

        return q1.isEmpty() && q2.isEmpty() ;
        
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */