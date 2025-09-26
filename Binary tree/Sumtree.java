/// transform a tree into sum tree i.e. curr node is sum of left n right child
/// at each level 
/// leaf nodes are 0;


import java.util.*;


public class Sumtree{

    public static void transformTree(Node root){

        sumTree(root);
    }

    public static int sumTree(Node root){
        // base case 
        if(root ==null){
            return 0;
        }

        // if(root.left ==null && root.right ==null){
        //     int temp =root.data;
        //     root.data =0;
        //     return temp;
        // }

        int sumLeft = sumTree(root.left);
        int sumRight = sumTree(root.right);

        int temp = root.data;
        root.data = sumLeft + sumRight ;

        return sumLeft+sumRight+ temp;

    }

    public static void main(String args[]){

    }
}