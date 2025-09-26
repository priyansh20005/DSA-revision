import java.util.*;

public class WeakestSoldiers{

    public static class RowInfo implements Comparable<RowInfo>{
        int sol;
        int idx;
        RowInfo(int s , int i){
            sol=s;
            idx=i;
        }

        @Override
        public int compareTo(RowInfo r2){
            if(this.sol - r2.sol == 0 )
                return this.idx - r2.idx;
            else
                return this.sol - r2.sol;    
        }
    }


    public static void main(String args[]){

        int soldiers[][] = {{1 ,0 ,0,0} ,
                            {1 , 1 , 1, 1},
                            {1,0,0,0},
                            {1,0,0,0}
                                     };
            
        PriorityQueue<RowInfo> pq = new PriorityQueue<>();

        for(int i=0 ; i< soldiers.length ; i++){
            int sol=0;
            for(int j=0; j< soldiers[i].length ;j++){
                if(soldiers[i][j]==1)
                    sol++;
            }
            pq.add(new RowInfo(sol , i));
        }

        int k = 2;
        for(int i=0 ; i<k ;i++){
            RowInfo temp = pq.remove();
            System.out.println(temp.idx);
        }
    }
}
