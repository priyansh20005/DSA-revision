// delete a node from bst
// 3 cases----
/// 1> leaf node
/// 2> has one child
/// 3> has 2 childs


import java.util.*;

public class delete{

    public static Node deleteNode(Node root , int val){
        if(root.val < val){
            //right subtree
            root.right = deleteNode(root.right , val);
        }
        else if(root.data > val){
            //left subtree 
            root.left  = deleteNode(root.left , val);
        }
        else{
            // this is the node to delete
            // case1    leaf 
            if(root.left == null && root.right==null){
                return null;
            }
            // case2  . 1 child
            else if(root.left == null || root.right==null){
                if(root.right == null)
                    return root.left;
                else 
                    return root.right;
            }
            // case 3   2 childs
            else{
                Node inSuccessor = findInorderSuccessor(root.right);
                root.data = inSuccessor.data;
                root.right =delete(root.right , inSuccessor.data);
            }
        }
        return root;
    }

    public static Node findInorderSuccessor(Node root){
        // left most node 
        while(root.left !=null){
            root  = root.left;
        }
        return root;
    }


    public static void main(String args[]){

    }
}