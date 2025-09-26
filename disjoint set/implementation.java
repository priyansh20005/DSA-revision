// implementation of disjoint set union ,find

import java.util.*;



public class implementation {

    static int n = 7;
    static int par[]=new int[n];
    static int rank[] = new int[n];

    public static void init(){
        for(int i=0;i<n;i++){
            par[i]=i;
        }
        Arrays.fill(rank,0);
    }

    //////////////////////////////////main////////////////
    public static void main(String args[]){

        init();
        union( 1,  3);
        System.out.println(find( 3));
        union( 2,  4);
        union( 3,  6);
        union( 1,  4);
        System.out.println(find( 3));
        System.out.println(find( 4));
        union( 1,  5);

    }
/////////////////////////////////////////////////o(4k)
    public static int find(int x){
        // find final leader 
        if(par[x]==x)
            return x;
        else
            return par[x]= find(par[x]);
            //path compression 
    }

    // union by comparing rank of final leaders  O(4k)
    public static void union(int x , int y){
        int p1=find(x);
        int p2=find(y);

        int r1  = rank[p1];
        int r2 = rank[p2];

        if(r1 ==r2){
            par[p2] = p1;
            rank[p1]++;
        }
        else if(r1>r2){
            par[p2] =p1;
        }
        else  // r2>r1
            par[p1]=p2;
    }
}
