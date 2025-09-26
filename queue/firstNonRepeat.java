// given a string of charaters - all lower case 

// for every stage ( level) find the first non repeating ch 
// if not then -1 ;

import java.util.*;


public class firstNonRepeat{

    public static void firstNonRepeating(String str  , ArrayList<Character> ans){


         int freq[] =new int[26] ;

         Queue<Character> q= new LinkedList<>();

         for(int i =0 ; i<str.length() ; i++){

            char curr = str.charAt(i);
            int idx = curr -'a';
            freq[idx]++;

            if(freq[idx] ==1){
                // first occurence
                q.add(curr);
            }

            while(!q.isEmpty()){
                char temp = q.peek();
                if(freq[temp-'a'] ==1){
                    ans.add(temp);
                    break;
                }
                else{
                    q.remove();
                }
            }
            if(q.isEmpty()){
                ans.add('-');
            }

         }


    }



    public static void main(String args[]){

        String str = new String("abbaccdee");

        ArrayList<Character> ans = new ArrayList<>();

        firstNonRepeating(str, ans);
        
       for(int i=0 ; i< ans.size() ;i++){
            System.out.print(ans.get(i)+ " ");
        }

        
    }
}