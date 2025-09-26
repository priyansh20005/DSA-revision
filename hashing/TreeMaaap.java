import java.util.Set;
import java.util.TreeMap;

public class TreeMaaap{

    public static void main(String args[]){
        TreeMap<String,Integer> tmap1 = new TreeMap<>();
        tmap1.put("a" , 150);
        tmap1.put("f" , 140);
        tmap1.put("e" , 17);
        tmap1.put("d" , 5);

        Set<String> tmapKeys = tmap1.keySet();

        System.out.println("sorted keys-> "+ tmapKeys);
    }
}