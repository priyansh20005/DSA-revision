// given a directed graph , source and target 
//find or print all possible paths of src to target


import java.util.*;


public class AllPaths {
    

    public static void main(String args[]){

    }

    // exponential time complexity;
    public static void allPaths(ArrayList<Edge>[] graph , int src , int target){
        ArrayList<Integer> path = new ArrayList<>();
        boolean isVisited[]= new boolean[graph.length];

        allPaths_util(graph , isVisited , path ,  src , target);

    }

    public static void allPaths_util(ArrayList<Edge> [] graph , boolean [] isVisited , ArrayList<Integer> path , int curr , int target){
        
        isVisited[curr] = true;
        path.add(curr);
        if(curr==target){
            System.out.println(path);
            path.remove(path.size()-1);
            return ;
        }

        for(int i=0; i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(isVisited[e.dest]==false)
            allPaths_util(graph, isVisited , path ,e.dest , target);
        }

        //backtracking
        path.remove(path.size()-1);
        isVisited[curr]=false;
    }
}
