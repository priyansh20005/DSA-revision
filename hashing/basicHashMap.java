import java.util.*;

public class basicHashMap{

    public static void main(String args[]){

        HashMap<String , Integer> map1 = new HashMap<>();

        map1.put("Priyansh" , 2);
        map1.put("koshti" , 1);
        map1.put("Shivam" , 2);

        System.out.println(map1);

        System.out.println(map1.get("Priyansh") +"  ");
        System.out.println(map1.containsKey("Hydarr")+"  ");
        System.out.println(map1.get("Thakur"));

        map1.remove("koshti");
        System.out.println(map1.size());
        System.out.println(map1.isEmpty());

        Set<String> keys1 = map1.keySet();
        // Set<Integer> values1 = map1.entrySet();
        
        
        for(String key : keys1){
            System.out.println("key= "+ key+ "  value= "+ map1.get(key));
        }

    }
};