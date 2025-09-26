// given cities connecting by some distance path 
// find minium distancce connecting all cities (mst)

import java.util.*;


public class connectingCities {

    public static class Edge implements Comparable<Edge>{
        int src,dest , wt;
        Edge(int s , int d , int w){
            src=s;
            dest=d;
            wt=w;
        }

        @Override
        public int compareTo( Edge e2){
            return this.wt-e2.wt;
        }
    }
    
    //////////////////////////MAIN///////////////////
    public static void main(String args[]){
        int [][] cities = {{0,1,2,3,4},
                            {1,0,5,0,7},
                            {2,5,0,6,0},
                            {3,0,6,0,0},
                            {4,7,0,0,0}};
                        
        System.out.println("the min cost for connecting all cities is:-> "+giveMinCost(cities));
    }
    ////////////////////////////////////////////////////////////////////

    public static void createGraph(int[][] cities, ArrayList<Edge> [] graph){
        for(int i=0; i<graph.length ;i++){
            graph[i]=new ArrayList<>();
        }
        for(int i=0 ;i<cities.length; i++){
            for(int j=0;j<cities[0].length;j++){
                int dist = cities[i][j];
                if(dist !=0){
                    Edge e= new Edge(i, j , dist);
                    graph[i].add(e);
                }
            }
        }
    }//////////////////////////////////////////////////////////////////

    public static int giveMinCost(int[][] cities){
        int n =cities.length;
        ArrayList<Edge>[] graph = new ArrayList[n];
        createGraph(cities , graph);

        boolean isVisited[]= new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int src=0;

        int minCost =0;
        pq.add(new Edge(-1 , src , 0));

        while(!pq.isEmpty()){
            Edge e = pq.remove();
            if(isVisited[e.dest]==false){
               isVisited[e.dest]=true;
               minCost+=e.wt;

               //pushing all neighbours not visited
               for(Edge curr : graph[e.dest]){
                    if(isVisited[curr.dest]==false){
                        // pq.add(new Edge(curr.src , curr.dest , curr.wt));
                        pq.add(curr);
                    }
               }
            }
        }

        

        return minCost;
    }
}
