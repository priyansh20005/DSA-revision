import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class traversal {

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

///////////////////////////////////////////MAIN/////////////////////////
    public static void main(String args[]){

        int v= 5;
        ArrayList<Edge>[ ] graph =new ArrayList[v];

        createGraph(graph);
        bfs(graph);
        dfs(graph);
        DFS_recursion(graph);
    }

    ///// BFS  O(V+E)
    public static void bfs(ArrayList<Edge>[] graph){
        Queue<Integer> q = new LinkedList<>();
        // ArrayList<Boolean> isVisited = new ArrayList<>();
        boolean[] isVisited = new boolean[graph.length];

        System.out.print("BFS-> ");
        // takin src as 0
        q.add(0);

        while(!q.isEmpty()){
            int curr = q.remove();

            if(isVisited[curr] == false){
                isVisited[curr] = true;  //1
                System.out.print(curr+" "); //2
                for(int i=0 ;i<graph[curr].size(); i++){ //3
                    Edge e = graph[curr].get(i);
                    q.add(e.dest);
                }
            }
            else{
                if(q.isEmpty())
                    break;
            }
        }
        System.out.println();
    }

    ///// DFS - stack O(V+E);
    public static void dfs(ArrayList<Edge>[] graph){
        Stack<Integer> s = new Stack<>();
        boolean[] isVisited = new boolean[graph.length];

        System.out.print("DFS-> ");

        s.push(0);
        while(!s.isEmpty()){
            int curr = s.pop();
            if(isVisited[curr] ==false){
                System.out.print(curr+ " ");
                isVisited[curr]= true;
                for(int i=0;i<graph[curr].size();i++){
                    Edge e= graph[curr].get(i);
                    s.push(e.dest);
                }
            }
            else{
                if(s.isEmpty())
                    break;
            }
        }
        System.out.println();
    }
    
    //// DFS-recusively;  O(v+E)
    public static void DFS_recursion(ArrayList<Edge>[] graph ){
        boolean isVisited[]= new boolean[graph.length];
        System.out.print("DFS recusive-> ");
        DFS(graph , 0 , isVisited);
        System.out.println();
    }

    public static void DFS(ArrayList<Edge>[] graph, int curr ,boolean isVisited[] ){
        // if(isVisited[curr]==true)
        //     return ;
        
        System.out.print(curr+" ");
        isVisited[curr]= true;
        for(int i=0;i<graph[curr].size();i++){
            Edge e= graph[curr].get(i);
            int dest = e.dest;
            if(isVisited[dest]==false)
                DFS(graph,dest,isVisited);
        }
    }
}
