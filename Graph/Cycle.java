/// cycle in directed graph 
// here are graph is directed 

import java.util.*;



public class Cycle {
    

    public static void main(String args[]){
        
        System.out.println(isCycle(graph));
    }

    public static boolean isCycle(ArrayList<Edge>[] graph){

        int n = graph.length;
        boolean [] isVisited=new boolean[n]; // strores wheather the node is alredy checked or not.
        boolean[] stack = new boolean[n]; //stores the current node path.

        for(int i=0 ; i<n ;i++){
            if(isVisited[i]==false){
                if(checkCycle(graph , isVisited , stack , i))
                    return true;
            }
        }
        return false;
    }

    public static boolean checkCycle(ArrayList<Edge> [] graph, boolean[] isVisited , boolean [] stack , int curr){

        isVisited[curr]= true;
        stack[curr]=true;

        for(int i=0 ; i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(stack[e.dest]==true)
                return true;
            else if(isVisited[e.dest] ==false){
                if(checkCycle(graph , isVisited , stack , e.dest))
                    return true;
            }
            // else
                // isVisited[neigh] =true // already checked
                // stack[neigh]= false; // not in this cycle 
                // nothing to do ;
        }
        stack[curr]= false; // removing from this path
        return false;
    }
}
