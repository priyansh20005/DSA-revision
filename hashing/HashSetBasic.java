import java.util.*;

public class HashSetBasic{


    public static void main(String args[]){
        HashSet<Integer> set = new HashSet<>();

        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(1);

        System.out.println(set);
        System.out.println(set.contains(3)+" ");
        System.out.println(set.remove(1)+" ");
        System.out.println(set.size());
        set.add(99);
        set.add(89);

        // iteration on hashSet using iterators
        Iterator i = set.iterator();
        while(i.hasNext()){
            System.out.print(i.next()+" ");
        }System.out.println();

        // iteration by advanced loop
        for(Integer k :set){
            System.out.print(k+" ");
        }
    }
}