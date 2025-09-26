

import java.util.*;

public class basic{
    
    // public static class Node{
    //         int val;
    //     }

    public static class Edge{
            int src , dest;
            int weight;
            Edge(int s,int d , int w){
                src=s;
                dest=d;
                weight=w;
            }
        }

    

    public static void main(String args[]){
         
       int v =5;
       ArrayList<Edge>[] graph = new ArrayList[v];  //null-> empty arrayList

       for(int i=0;i<v;i++){
            graph[i] = new ArrayList<>();
       }

    //    0-vertex
    graph[0].add(new Edge(0,1,5));

    //1-vertex
    graph[1].add(new Edge(1,0,5));
    graph[1].add(new Edge(1,3,3));
    graph[1].add(new Edge(1,2,1));

     //2-vertex
    graph[2].add(new Edge(2,1,1));
    graph[2].add(new Edge(2,3,1));
    graph[2].add(new Edge(2,4,2));

    //3-vertex
    graph[3].add(new Edge(3,2,1));
    graph[3].add(new Edge(3,1,3));

    //4-vertex
    graph[4].add(new Edge(4,2,2));


    //get neighbour - 2nd vertex
    for(int i=0; i<graph[2].size();i++){
        Edge e= graph[2].get(i);
        System.out.print(e.dest+" ");
    }

    /// 

    }

}