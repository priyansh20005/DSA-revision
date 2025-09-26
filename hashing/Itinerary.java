// find itinerary from ticket
// by using to hash maps

import java.util.*;

public class Itinerary{

    public static  String getStart( HashMap<String , String> fromMap , HashMap<String ,String> toMap){

        for(String key :toMap.keySet() ){
            if(!fromMap.containsKey(key))
            return key;
        }

        return "";
    }

    public static void main(String args[]){

        String[][] journey = {{"chennai", "mumbai" , "goa", "delhi"},{"bengaluru","delhi" , "chennai", "goa"}};
        HashMap<String , String> fromMap = new HashMap<>();
        HashMap<String ,String> toMap= new HashMap<>();

        for(int i =0;i<journey[0].length;i++){
            fromMap.put(journey[1][i] , journey[0][i]);
            toMap.put(journey[0][i] , journey[1][i]);
        }

        System.out.println(getStart(fromMap, toMap)+" ");
    }
}