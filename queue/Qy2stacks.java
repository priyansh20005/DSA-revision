class Qy2stacks {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();
    // static int s1_size , s2_size;

    

    public Qy2stacks() {
       
    }
    
    public void push(int x) {   // O(n)

        //empty the s1 stack
        while(!s1.isEmpty()){
            int temp = s1.pop();
            s2.push(temp);
        }

        // insert push the new elemeent
        s2.push(x);

        //re insert all val os s1 from s2
        while(!s2.isEmpty()){
            s1.push(s2.pop());
        }
        
    }
    
    public int pop() { //O(1)
        //in s1 top
        if(empty()){
            return -1;
        }else{
            return s1.pop();
        }
    }
    
    public int peek() {

        if(empty()){
            return -1;
        }else{
            return s1.peek();
        }
        
    }
    
    public boolean empty() {
        
        if(s1.isEmpty() && s2.isEmpty()){
            return true;
        }
        else
        return false;
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */