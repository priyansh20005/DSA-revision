// two string s and t 
// check anagram 
// same length and same occurence freq of letter but rearranged

import java.util.*;

public class Anagram{
     public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;

        HashMap<Character , Integer> map1 = new HashMap<>();
        // HashMap<Character, Integer> map2 = new HashMap<>();

        // for(int i =0 ; i<s.length();i++){
        //     Character ch1 = s.charAt(i);
        //     Character ch2 = t.charAt(i);
            // if(map1.containsKey(ch1))
            //     map1.put(ch1 , map1.get(ch1)+1);
            // else
            //     map1.put(ch1 , 1);
        //     if(map2.containsKey(ch2))
        //          map2.put(ch2 , map2.get(ch2)+1);
        //     else
        //         map2.put(ch2 , 1);
        // }

        // Set<Character> key1 = map1.keySet();
        // Set<Character> key2 = map2.keySet();


        // // for( Character ch: key1){
        // //     if(map1.get(ch) != map2.get(ch))
        // //         return false;
        // // }
        // for( Character ch: key2){
        //     if(!map1.get(ch).equals(map2.get(ch)))
        //         return false;
        // }

        // return true;

        for(int i=0 ; i <s.length();i++){
            Character ch = s.charAt(i);
            if(map1.containsKey(ch))
                map1.put(ch , map1.get(ch)+1);
            else
                map1.put(ch , 1);
        }
        /// s string mapping done

        for(int i=0;i<t.length(); i++){
            Character ch = t.charAt(i);
            if(!map1.containsKey(ch))
                return false;
            else if(map1.get(ch) ==1 )
                map1.remove(ch);
            else
                map1.put(ch , map1.get(ch)-1);  
        }

            Set<Character> keys = map1.keySet();
            // for(Character k : keys){
            //     if(map1.get(k) >0){
            //         return false;
            //     }
            // }
            if(keys.size() >0)
                return false;
        return true;
    }
}