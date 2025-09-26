// bell men Ford algorithm for single source shortest path (negative weight)

import java.util.*;


public class bellmenFord {
   public static void main(String args[]) {

   }

   public static void BellMenFord(ArrayList<Edge>[] graph , int src){
    int n = graph.length;
    int [] distance = new int[n];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[src]=0;

    for(int i=0 ;i<n-1;i++){
        //edges-O(E)
        for(int j=0 ; j<graph.length;j++){
            for(Edge e: graph[j] ) {
            // u , v wt
            int u = e.src;
            int v = e.dest;
            int w = e.weight;

            //relaxation 
            if((distance[u] != Integer.MAX_VALUE) &&(distance[u]+w < distance[v])){
                distance[v]= distance[u]+w;
            }
             }
        }
    }

    // Check for negative weight cycles
    for (int u = 0; u < graph.length; u++) {
        for (Edge e : graph[u]) {
            int v = e.dest;
            int w = e.weight;

            if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                System.out.println("Graph contains a negative weight cycle!");
                return;
            }
        }
    }

    for(int i=0 ;i<n;i++){
        System.out.print(distance[i]+" ");
    }
   }
}
