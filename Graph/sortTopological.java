// topological sorting of the graph
// kahns algorith
// both dfs and bfs

import java.util.*;


public class sortTopological {
    
    // DFS O(v+e)
    // o(n) = space complexity

    public static void TopologicalSort(ArrayList<Edge>[] graph){
        int n = graph.length;
        boolean [] isVisited=new boolean[n];
        Stack<Integer> s = new Stack<>();

        for(int i=0;i<n;i++){
            if(isVisited[i]==false)
            topSort(graph , isVisited , s , i);
        }
        while(!s.isEmpty()){
            System.out.print(s.pop()+" ");
        }
    }

    public static void topSort(ArrayList<Edge>[] graph , boolean[] isVisited , Stack<Integer> s , int curr){

        isVisited[curr]=true;
        for(int i=0;i<graph[curr].size();i++){
            Edge e  = graph[curr].get(i);
            if(isVisited[e.dest]==false){
                topSort(graph,isVisited,s, e.dest);
            }
        }
        s.add(curr);
    }
    ////////////////////////////////////////////////////main//////////

    public static void main(String args[]){

    }
    ////////////////////////////////////////////////////////////////////
    ///
    /// bfs -> Kahns algo 
    /// topological sort
    public static void bfs_TopologicalSort(ArrayList<Edge>[] graph){
        int n = graph.length;
        Queue<Integer> q = new LinkedList<>();
        int[] indegree = new int[n];
        Arrays.fill(indegree ,0);

        //indegree cal 
        for(int i=0 ; i<n;i++){
            for(int j=0;j<graph[i].size(); j++){
                Edge e = graph[i].get(j);
                indegree[e.dest]++;
            }
        }
        //got indegree of all nodes 

        for(int i=0;i<n;i++){
            if(indegree[i]==0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int  curr = q.remove();
            System.out.print(curr+" ");
            indegree[curr]--;
            for(int j=0; j<graph[curr].size();j++){
                Edge e =graph[curr].get(j);
                indegree[e.dest]--;
                if(indegree[e.dest]==0)
                    q.add(e.dest);
            }
            // for(int i=0;i<n;i++){
            //     if(indegree[i]==0){
            //     q.add(i);
            //     }
            // }
        }
    }
}
