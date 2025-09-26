// implement the circular queue using array;

class circularQueue {

    static int[] queue;
   static  int front ;
    static int rear;
   static  int size ;

    public circularQueue(int k) {

        queue = new int[k];
        front = -1 ;
        rear = -1;
        size = k;
    }
    
    public boolean enQueue(int value) {

        if(isFull()){
            return false;
        }
        else if(isEmpty()){
            rear = front =0;
            queue[rear] = value;
            return true;
        }
        else{
            rear =( rear+1)%size ;
            queue[rear] = value;
            return true;
        }
        
    }
    
    public boolean deQueue() {
        
        if(isEmpty()){
            return false;
        }
        else if(front == rear){
            front = rear = -1;
            return true;
        }
        else{
            
            front= (front+1)%size;
            
            return true;
        }
    }
    
    public int Front() {
        if(isEmpty()){
            return -1;
        }
        else{
            int temp = queue[front];
            return temp;
        }
        
    }
    
    public int Rear() {
        if(isEmpty()){
            return -1;
        }else{
            return queue[rear];
        }
    }
    
    public boolean isEmpty() {

        if(front ==-1 && rear == -1){
            return true;
        }
        else 
        return false;
        
    }
    
    public boolean isFull() {

        if((rear+1)% size == front){
            return true;
        }else{
            return false;
        }   
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */