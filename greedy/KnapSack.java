import java.util.*;


public class KnapSack{

    // fractional kanpsack
    public static float knapsack_fra(float[] profit , float [] weight , int capacity){

        float [] PWR = new float[profit.length];

        for(int i = 00 ;i < profit.length ; i++){
            PWR[i] = (float)profit[i]/weight[i];
        }

        // sort in decreasing order 

        float [][] table = new float[profit.length] [4];
        // index , profit , wight , pwr 

        for(int i=0; i<profit.length ;i++){
            table[i][0] = i; // index
            table[i][1] = profit[i];
            table[i][2] = weight[i];
            table[i][3] = PWR[i];
        }

        // sort the table descending

        Arrays.sort(table , Comparator.comparingDouble(o -> o[3]));
                                // lamda function
        // but it gives Ascending sorting
        //  so we run loop from n-1 to 0 


        // now choiceing
        float currProfit =0 , remCapacity=capacity ;
        
    // for(int i =0; i<profit.length ;i++){
    //     if(remCapacity >= weight[i]) {
    //         // full object select
    //         remCapacity -= weight[i];
    //         currProfit += profit[i];
    //     }
    //     else{
    //         // fractional part
    //         float part = (float) remCapacity/ weight[i];
    //         currProfit += (float)profit[i] * part ;
    //         remCapacity = 0;
    //         break;
    //     }
    // }

    for(int i =profit.length-1 ; i>=0 ;i--){
        if(remCapacity >= table[i][2]) {
            // full object select
            remCapacity -= table[i][2];
            currProfit += table[i][1];
        }
        else{
            // fractional part
            float part = (float) remCapacity/ table[i][2] ;
            currProfit += (float)table[i][1] * part ;
            remCapacity = 0;
            break;
        }
    }

    return currProfit;


    }


    public static void main(String args[]){


    }
}