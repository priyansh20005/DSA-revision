import java.util.*;

// check wheather the BST is correct or not

// 1 > by checking inorder traversal is sorted
// 2>  by checking min and max for left n right subtree

public class validateBST{

    public static boolean isValid(Node root , int min , int max){
        if(root == null ) 
            return true;
        
        // out of bound 
        if(root.val >= max || root.val <= min)
            return false;
        
        // if leaf node
        if(root.left==null && root.right==null)
            return true;

        // not a leaf node
        boolean left= true;
        boolean right = true;

        if(root.left != null){
           left= isValid(root.left , min , root.val);
        }
        if(root.right != null){
            right = isValid(root.right , root.val , max);
        }

        return right&& left;
    }


    public static void main(String args[]){

    }
}