// kosaraju algorithm for strongly connected components
// O(V+E);

import java.util.*;

public class kusaraju {

    public static class Edge{
        int src , dest;
        Edge(int s ,int d){
            src=s;
            dest=d;
        }
    }
     public static void createGraph(ArrayList<Edge>[]graph){
        for(int i=0;i<graph.length;i++)
            graph[i]=new ArrayList<Edge>();

        graph[0].add(new Edge(0,2));
        graph[0].add(new Edge(0,3));

        graph[1].add(new Edge(1,0));

        graph[2].add(new Edge(2,1));

        graph[3].add(new Edge(3,4));
    }
    
    public static void main(String args[]){
        int v= 5;
        ArrayList<Edge>[] graph = new ArrayList[v];
        createGraph(graph);

        getSCC(graph);

    }

///////////////////find scc function/////////////////////
    public static void getSCC(ArrayList<Edge>[] graph){
        int v = graph.length;

        //step1->topological sort and store in stack
        Stack<Integer> s = new Stack<>();
        boolean vis[] =new boolean[v];
        for(int i=0;i<v;i++){
            if(!vis[i]){
                topSort(graph, i , vis,s);
            }
        }

        //step2 -> transpose of graph (dest->src)
        ArrayList<Edge>[] transpose = new ArrayList[v];
        // Arrays.fill(transpose, new ArrayList<Edge>());
        // Arrays.fill(vis,false); // if needed agin
        for(int i=0;i<v;i++){
            transpose[i]= new ArrayList<Edge>();
            vis[i]=false;
        }
        for(int i=0;i<v;i++){
            for(Edge e: graph[i]){
                int src = e.src;
                int dest  = e.dest;
                transpose[dest].add(new Edge(dest,src)); // reversal of edge 
            }
        }

        //step3-> dfs on transpose graph
        while(!s.isEmpty()){
            int curr = s.pop();
            if(!vis[curr]){
                System.out.print("SCC-> ");
                dfs(transpose ,curr , vis);
                System.out.println();
            }
        }
    }
   /////////////////////////////helper functions--------------------
   
   // topological sort
   public static void topSort(ArrayList<Edge>[] graph, int curr , boolean[] vis , Stack<Integer> s){
    vis[curr] = true;
    //neighbours
    for(Edge e:graph[curr]){
        if(!vis[e.dest]){
            topSort(graph ,e.dest , vis , s );
        }
    }
    s.push(curr);
   }

   //dfs 
   public static void dfs(ArrayList<Edge>[] graph , int curr ,boolean [] vis ){
        vis[curr] = true;
        System.out.print(curr+" ");

        for(Edge e: graph[curr]){
            if(!vis[e.dest]){
                dfs(graph , e.dest , vis);
            }
        }
   }
}
