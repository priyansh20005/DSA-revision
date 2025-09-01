import java.util.*;

//print all the value in the range k1 and k2 
// also print sum of all


// print all paths from root to leafs

import java.util.*;
public class inRange{

    public static int printRange(TreeNode root , int low ,int high , int sum){
        if( root == null) 
            return sum ;
        else if(root.val >=low && root.val <= high){
            System.out.println(root.val+" ");
            sum+= root.val;

            sum = printRange(root.left , low , high , sum);
            sum = printRange(root.right , low , high , sum);
        }
        else if(root.val < low){
           sum= printRange(root.right , low ,high , sum);
        }
        else if( root.val >high){
          sum=  printRange(root.left , low , high , sum);
        }

        return sum;
    }

    ////// rrot to leaf paths
    public static ArrayList<ArrayList<Integer>> getPaths(Node root ){

        ArrayList<ArrayList<Integer>> paths= new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
       /// bc
        if(root == null)
             return paths;
        getAllPaths(root ,paths , path );

        return paths;
    }

    public static void getAllPaths(Node root , ArrayList<ArrayList<Integer>> paths , ArrayList<Integer> path){
        
        path.add(root.val);
        // leaf node
        if(root.left ==null && root.right == null) {
            paths.add(new ArrayList<>(path));
            path.remove(path.size()-1);
            return;
        }

        if(root.left != null){
            getAllPaths(root.left , paths , path);
        }
        if(root.right != null){
            getAllPaths(root.right , paths, path);
        }

        path.remove(path.size()-1);
    }

    public static void main(String args[]){

    }
}