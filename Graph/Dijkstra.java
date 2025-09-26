// shortest path from src to path -Dijkstra Algo
// Weighted graph 

import java.util.*;

public class Dijkstra {

    public static class Pair implements Comparable<Pair>{
        int node ;
        int dist;
        Pair(int n , int d){
            node= n;
            dist=d;
        }
        @Override
        public int compareTo(Pair p2){
            return this.dist-p2.dist;
        }
    }
    //////////////////////////////////MIAN/////////////
    public static void main(String args[]){

    }

    /// dijkstra//////////////////////////////////////////
    public static int allShortestPaths(ArrayList<Edge>[] graph , int src , int target){

        boolean [] isVisited = new boolean[graph.length];
        // PriorityQueue<Pair> pq = new PriorityQueue<>();
        

        int[] distance = new int[graph.length];
        Arrays.fill(distance , Integer.MAX_VALUE);
        distance[src] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src , 0));
        
        while(pq.isEmpty()==false){
            Pair curr = pq.remove();
            int u = curr.node;
            if(!isVisited[u]){
                isVisited[u]=true;  
                for(Edge e: graph[u]){
                       
                        int direct_Dist = distance[e.dest];  //d(v)
                        int currDist = distance[u]+ e.weight; //d(u)+w(u,v)

                        if(currDist < direct_Dist){
                            distance[e.dest] = currDist;
                        }
                         pq.add(new Pair(e.dest ,distance[e.dest] )); 
            //    getDistances(graph , isVisited ,distance ,pq, u);
            }
        
        }

        return distance[target];
    }
    ///////////////////////////////////////////

    public static void getDistances(ArrayList<Edge> [] graph , boolean[] isVisited , int[] distance , PriorityQueue<Pair> pq , int curr){

        
        for(int i=0 ; i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(!isVisited[e.dest]){
                int direct_Dist = distance[e.dest];  //d(v)
                int currDist = distance[curr]+ e.weight; //d(u)+w(u,v)

                if(currDist < direct_Dist){
                    distance[e.dest] = currDist;
                }
                pq.add(new Pair(e.dest ,distance[e.dest] )); 
            }
        }

    }
}
