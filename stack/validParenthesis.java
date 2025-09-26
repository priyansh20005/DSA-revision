import java.util.Stack;

public class validParenthesis{


// check string has valid parenthese 
    public static boolean isValidParenthesis(String str){

        Stack<Character> s = new Stack<>();
        int idx =0;

        return isValid(str , s ,idx);

        
    }

    public static boolean isValid(String str , Stack<Character> s,  int idx  ){
        if(idx == str.length()){
            if(s.isEmpty()) return true;
            return false;
        }

        char curr= str.charAt(idx);

        if( s.isEmpty()){
             if( curr== '(' || curr =='[' || curr=='{' ){
            s.push(curr);
            }
            else 
            return false;
        }
        else{
             char prev = s.peek();

        if( curr== '(' || curr =='[' || curr=='{' ){
            s.push(curr);
        }
        else if( curr=='}' || curr ==']' || curr ==')'){
            if( prev == '(' && curr ==')'){
                s.pop();
            }
            else if(prev == '[' && curr ==']'){
                s.pop();
            }
            else if (prev == '{' && curr =='}'){
                s.pop();
            } 
            else{
                return false;
            }
        }

        }
         return isValid(str , s  , idx+1);

    }


// check for duplicate parenthese
    public static boolean isDuplicate(String str){
        Stack<Character> s2 = new Stack<>();

        char curr , prev ;
        for(int i=0 ; i< str.length() ; i++){
            
            curr = str.charAt(i);
            if(curr == ']' || curr =='}' || curr ==')'){
                prev = s2.peek();
                if( prev == '(' ) return true; // duplicate exists
                else{
                    while(s2.peek()=='('){
                        s2.pop();
                    }
                    s2.pop();
                }  
            }else{
                s2.push(curr);
            }
        }
        return true;
    }

    public static void main(String args[]){

        if(isValidParenthesis("({[[]()]}{}}")){
            System.out.println(" yes valid ");
        }else{
            System.out.println(" not a valid parenthesis");
        }

    }
}