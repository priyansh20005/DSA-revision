// given an arr of flight (freom , to , cost) and N cities connecting
// given src , dest and k find minimum cost to reach dest from src with atmost k stops 


import java.util.*;




public class cheapestFlight {

    public static class Edge {
        int src,dest,wt;
        Edge(int s , int d , int w){
            src=s;
            dest=d;
            wt=w;
        }
    }

//////////////////////////////Node Info /////////////// class////

    public static class NodeInfo implements Comparable<NodeInfo>{
        int node, cost  , stops;
        NodeInfo(int n , int c, int s){
            node=n;
            cost=c;
            stops=s;
        }

        @Override
        public int compareTo(NodeInfo i2){
            return this.cost-i2.cost;
        }
    }

///////////////////////////////////////////////////MAIN//////////////////////////
    public static void main(String args[]){
        int[][] flights = {{0,1,100}, {1,2,100},{0,2,500}};
        int n = 3;
        int src = 0;
        int dest = 2;
        int k =1;
        
        System.out.println("the cheapest cost of flights is-> "+ minCost(flights , src , dest , k ,n));
    }
////////////////////////////////////////////////////////////////////////////////////

    public static int minCost(int[][] flights , int src , int dest , int stops , int n){

        //create the adjacency ist of grapgh
        ArrayList<Edge>[] graph = new ArrayList[n];
        createGraph(flights , graph);

        PriorityQueue<NodeInfo> pq = new PriorityQueue<>();
        pq.add(new NodeInfo(src,0 , 0));

        // boolean[] isVisited= new boolean[n];
        int[] distance = new int[n];
        Arrays.fill(distance , Integer.MAX_VALUE);
        distance[src]=0;

        while(!pq.isEmpty()){
            NodeInfo curr = pq.remove();

            if(curr.node ==dest)
                return curr.cost;

            if(curr.stops >stops)
                continue;
            
            if(curr.cost <= distance[curr.node] ){
                distance[curr.node]= curr.cost;

                for(Edge e: graph[curr.node]){
                    pq.add(new NodeInfo(e.dest , curr.cost+e.wt , curr.stops+1));
                }
            }
        }


        distance[dest]= (distance[dest]==Integer.MAX_VALUE) ?  -1:  distance[dest];
        return distance[dest];
    }


    // ///////////////// create graph by flights 
    public static void createGraph(int[][] flights  , ArrayList<Edge>[] graph){

        for(int i=0 ; i<graph.length ; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i=0 ;i<flights.length; i++){
            int src= flights[i][0];
            int dest = flights[i][1];
            int cost = flights[i][2];

            graph[src].add(new Edge(src, dest , cost));
        }
    }
 
}
