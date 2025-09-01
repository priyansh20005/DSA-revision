// minimum cost to cut chocolate  into pieces 

import java.util.*;


public class chocola{

    public static int minCost(Integer[] costVer , Integer[] costHor){

        //sort in descending 
        Arrays.sort(costVer , Collections.reverseOrder());
        Arrays.sort(costHor , Collections.reverseOrder());

        int h = 0 , v=0;
        int hp =1 , vp =1; // pieces count
        int cost=0;

        while(h< costHor.length && v < costVer.length){
            
            if(costVer[v] <= costHor[h]){
                //horizontal cut
                cost += (costHor[h] *vp);
                h++;
                hp++;
            }
            else{
                // vertical cutt
                cost+= (costVer[v]* hp);
                v++;
                vp++;
            }
        }
//remaining
        while(h < costHor.length){
            //horizontal cut
                cost += (costHor[h] *vp);
                h++;
                hp++;
        }
        while(v < costVer.length){
                // vertical cutt
                cost+= (costVer[v]* hp);
                v++;
                vp++;
            }

        return cost;
    }


    public static void  main(String args[]){
        int n=4 , m=6;
        Integer[] costVer ={2 ,1 , 3 , 1 , 4}; //m-1
        Integer[] costHor ={4, 1 ,2}; // n-1

        int Cost = minCost(costVer , costHor);

        System.out.println(Cost);
        
        
    }
}