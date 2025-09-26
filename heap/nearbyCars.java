// in a plot you are at origin given car coordinates from origin 
// find nearest k cars

import java.util.*;

public class nearbyCars{

    public static class carInfo implements Comparable<carInfo>{
        int x , y ;
        float hypo;
        carInfo(int x , int y, float h){
            this.x = x;
            this.y = y;
            this.hypo = h;
        }

        @Override
        public int compareTo(carInfo c2){
            return (int)(this.hypo - c2.hypo);
        }
    }

    public static void getKCars(int[][] cars , int k){

        PriorityQueue<carInfo> dist = new PriorityQueue<>();

        for(int i =0 ; i< cars.length ; i++){
            Float hypo =(float) Math.sqrt(cars[i][0]*cars[i][0]+ cars[i][1]*cars[i][1]);
            dist.add(new carInfo(cars[i][0] , cars[i][1] , hypo));
        }

        while(k >0 && k <= cars.length){
            carInfo temp = dist.remove();
            System.out.println(temp.x +" "+ temp.y);
            k--;
        }
    }

    public static void main(String args[]){

        int [][] cars = {{2 , 3} , {3 ,4 } , {5 ,6} ,{1,1}};
        int k =2 ;
    
        getKCars(cars , k);
    }
}