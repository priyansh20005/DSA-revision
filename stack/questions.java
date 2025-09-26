import java.util.*;


public class questions{

// push at the bottom of the stack
    public static void pushAtBottom(int data , Stack<Integer> s){

            // 1.-> by using another stack 
            //2-> by recursion
            //3-> by array or arraylist

            pushBottom(s , data   );
    }

    //helper or recursive function
    public static void pushBottom(Stack<Integer> s, int data){
        //base case
        if(s.size() == 0){
            s.push(data);
            return;
        }
        int temp = s.pop();
        pushBottom(s , data);
        s.push(temp);
    }

// reverse a string using stack
    public static String reverseString(String str){

        Stack<Character> s = new Stack<>();

        for(int i =0 ; i<str.length() ; i++){
            Character ch = str.charAt(i);
            s.push(ch);
        }
        String newStr ="";
        while(s.size() > 0){
            char ch = s.pop();
            newStr+=ch;
        }
        return newStr;
    }


// reverse a whole stack
    public static void reverseStack(Stack<Integer> s){
        // by using 2 extra stacks
        // with recursion without extra stack

        if(s.size()== 0){
            return;
        }

        int top = s.pop();

        reverseStack(s);
        
        pushAtBottom(top , s);

    }
    

    public static void main(String args[]){


    }
}