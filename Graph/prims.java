//mst - prims algorithm 

import java.util.*;


public class prims {

    public static class Edge{
        int src,dest , weight;
        Edge(int s,int d,int w){
            src=s;
            dest=d ;
            weight=w;
        }
    }
    
    public static class Pair implements Comparable<Pair>{
        int par;
        int curr ;
        int cost;

        Pair(int parent ,int curr , int c){
            this.par  = parent;
            this.curr=curr;
            this.cost =c;
        }

        @Override
        public int compareTo(Pair p2){
            return this.cost-p2.cost;   // ascending order
        }
    }
    public static void main(String args[]){

    }

    public static ArrayList<Edge> MST(ArrayList<Edge>[] graph ){
        boolean [] isVisited  = new boolean[graph.length];
        PriorityQueue<Pair>pq = new PriorityQueue<>();
        pq.add(new Pair(-1 ,0,0));
        int finalCost =0;   // total minimum cost
        ArrayList<Edge> ans = new ArrayList<>();  // edges included in MST

        while(!pq.isEmpty()){
            Pair curr = pq.remove();
            if(!isVisited[curr.curr]){
                isVisited[curr.curr]=true;
                finalCost+= curr.cost;

                if(curr.par != -1)
                ans.add(new Edge(curr.par , curr.curr , curr.cost));

                for(Edge e: graph[curr.curr] ){
                    if(!isVisited[e.dest])
                    pq.add(new Pair(e.src,e.dest , e.weight));
                }
            }
        }
        System.out.println("the MST cost is "+finalCost+" ");
        return ans;
    }
}
