// given coins = [1 , 2 , 5 , 10 , 20 , 50 , 100 , 500, 1000]
// and an amount  = 567
// find all the coins needs for make 567 amount


import java.util.*;


public class indianCoin{

    public static ArrayList<Integer> IndianCoin(int amount){
        int coins[] = {1 , 2, 5 , 10,20 , 50,100 , 500 , 1000};

        int remaining = amount;
        ArrayList<Integer> count = new ArrayList<>();

        // sort in decreasing ; 0 to n-1
        // or increasing . n-1 to 0


        for(int i =coins.length-1 ; i>=0 ; i++){
            if(remaining ==0) break;

            if(remaining >= coins[i]){
                while(remaining >= coins[i]){
                count.add(coins[i]);
                remaining-= coins[i];
                }
            }
        }
        return count;
    }

////////////////////////////// JOB SEQUENCING PROBLRM
/// /// maximum profit in deadline

    public static int maxProfit(int[][] jobs){

        // arrannge in decreasing order of profit
        Arrays.sort(jobs , Comparator.comparingDouble(o->o[1]));
            // gives in ascending

        int currtime =0;
        ArrayList<Integer> ans = new ArrayList<>();
        int maxProfit =0;

        for(int i = jobs.length-1 ; i>= 0 ; i++){
            if(currtime < jobs[i][0] ){
                // before deadline
                maxProfit+= jobs[i][1];
                ans.add(jobs[i][1]);
                currtime++;
            }
        }

        return maxProfit;

    }


    public static void main(String args[]){

    }
}


