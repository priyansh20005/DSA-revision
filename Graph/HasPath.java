/// has path , has cyclic present 

import java.util.ArrayList;

public class HasPath {
   
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

        boolean[] isVisited= new boolean[v];
        boolean ans = isPath(graph , 0 ,6 , isVisited);
        System.out.println(ans);
        System.out.println(isCycle(graph));
    }/////////////////////////////////////////////////////////////////////////////////////////

    public static boolean isPath(ArrayList<Edge>[] graph, int curr,int dest , boolean[] isVisited){

        if(curr ==dest)
            return true;
        
        isVisited[curr] = true;
        for(int i=0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(isVisited[e.dest]==false){
                if(isPath(graph ,e.dest , dest ,isVisited ))
                    return true;
            }
        }

        return false;
    }


    public static boolean isCycle(ArrayList<Edge>[ ] graph ){
        boolean [] isVisited= new boolean[graph.length];

        for(int i=0 ;i< graph.length;i++){

            //helper function 
            if(isVisited[i]==false){
                if(checkCycle(graph, i , -1, isVisited))
                    return true;
            }
            
        }

        return false;
    }

    public static boolean checkCycle(ArrayList<Edge> [] graph , int src ,int parent, boolean[] isVisited){

        isVisited[src]= true;

        for(int i=0 ;i<graph[src].size();i++){
            Edge e = graph[src].get(i);
            if(e.dest  == parent)
                continue;
            if(isVisited[e.dest] == true)
                return true;
            else{
                if(checkCycle(graph, e.dest,src , isVisited))
                    return true;
            }
                
        }

        return false;
    }
}
