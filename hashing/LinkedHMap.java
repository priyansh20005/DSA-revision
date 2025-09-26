/// linked hash map
///  insertion order is maintained

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

public class LinkedHMap{


    public static void main(String args[]){

        LinkedHashMap<String,Integer> map1 = new LinkedHashMap<>();
        map1.put("india" , 150);
        map1.put("china" , 140);
        map1.put("mp" , 17);
        map1.put("delhi" , 5);

        HashMap<String, Integer> hmap = new HashMap<>();
        hmap.put("india" , 150);
        hmap.put("china" , 140);
         hmap.put("mp" , 17);
        hmap.put("delhi" , 5);


        Set<String> linkedHashKeys = map1.keySet();
        Set<String> hashKeys = hmap.keySet();

        System.out.println("linked hash map keys->" + linkedHashKeys +"\n");
        System.out.println("normal hashMap keys-> "+ hashKeys);
    }
}