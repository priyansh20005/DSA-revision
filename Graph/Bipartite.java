import java.util.ArrayList;
import java.util.*;

public class Bipartite {
   
    public static class Edge{
        int src,dest,weight;
        Edge(int s , int d,int w){
            src=s;
            dest=d;
            weight=w;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]){
            
        for(int i=0;i<graph.length;i++){
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

    }


    ///////////////////////////////////main//////////////////////////////
    public static void main(String args[]){
        int v =5;
        ArrayList<Edge> [] graph = new ArrayList[v];

        createGraph(graph);

       
    }/////////////////////////////////////////////////////////////////////////////////////////

    ///bipartate check main function
    public static boolean isBipartite(ArrayList<Edge>[] graph){

        int [] color  = new int[graph.length];

        for(int i =0;i<graph.length;i++ ){
            color[i]=-1;
        }

        for(int i=0;i<graph.length ;i++){
            if(color[i]==-1){
                if(checkBipartite(graph ,color , i  , true)==false)
                    return false;
            }
        }

        return true;
    }
    // helper function
    public static boolean  checkBipartite(ArrayList<Edge>[] graph , int[] color , int curr ,boolean clr){

        int colour=-1;
        if(clr==true)
            colour=1; //yellow
        else
            colour=0; //blue
        
        color[curr]=colour;


        for(int i=0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(color[e.dest] == colour)
                return false;
           
            if(color[e.dest] == -1){
               if(checkBipartite(graph,color,e.dest  ,!clr ) ==false)
                return false;
            }
        }
        return true;
    }

    /////////////////////////////////////////////////////bfs approach//////////
     ///bipartate check main function
    public static boolean isBipartite_bfs(ArrayList<Edge>[] graph){

        int [] color  = new int[graph.length];

        for(int i =0;i<graph.length;i++ ){
            color[i]=-1;
        }

        for(int i=0;i<graph.length ;i++){
            if(color[i]==-1){
                if(checkBipartite_bfs(graph ,color , i )==false)
                    return false;
            }
        }

        return true;
    }

    // helper function
    public static boolean checkBipartite_bfs(ArrayList<Edge>[] graph , int[] color , int curr ){

        // int clr=1;
        color[curr]=1;
        Queue<Integer> q = new LinkedList<>();
        q.add(curr);

        while(!q.isEmpty()){
            int current = q.remove();

            for(int i=0;i<graph[current].size();i++){
            Edge e = graph[current].get(i);
            if(color[e.dest] == -1){
                color[e.dest]= 1-color[current]; //opposite colr
                q.add(e.dest);
            }

           
            if(color[e.dest] == color[current]){
                return false;
            }
        }

        }


        
        return true;
    }
}
